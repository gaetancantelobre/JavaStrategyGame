import java.util.ArrayList;

public class GameGrid {
    char[][] grid;
    private final int grid_x;
    private final int grid_y;

    private ArrayList<Building> buildings= new ArrayList<Building>();


    public char[][] getGrid() {
        return grid;
    }

    public int getGrid_x() {
        return grid_x;
    }

    public int getGrid_y() {
        return grid_y;
    }

    //gets the list of building present on the grid
    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public GameGrid(int size_x , int size_y) {
        this.grid = new char[size_x ][size_y];
        grid_x = size_x;
        grid_y = size_y;
        for(int i = 0;i < size_x;i++) {
            for (int j = 0; j < size_y; j++) {
                grid[i][j] = '¤'; //initalizes all the slot on the grid to the empty char
            }
        }
    }


    //prints out the grid
    public void printAndUpdateGrid()
    {
        for(int i = 0;i < grid_x;i++) {
            System.out.print("- ");
        }
        System.out.println("\n");
        System.out.print(" ");

        for(int i = 0;i < grid_x;i++) {
            System.out.print(i+ " ");
        }
        System.out.print("\n");

        for(int i = 0;i < grid_x;i++) {
            for (int j = 0; j < grid_y; j++) {
                System.out.print(" ");
                System.out.print(grid[i][j]);
            }
            System.out.print(" " +i);
            System.out.print('\n');
        }

        for(int i = 0;i < grid_x;i++) {
            System.out.print("- ");
        }
        System.out.print("\n");
    }


    //calculates and returns the upkeep cost of the whole grid
    public ResourceList getUpKeepOnGrid(ResourceManager stash){
        ResourceList rList = new ResourceList(0,0,0,0,0,0,0,0,0,0);
        for(Building b : buildings){
            if(b.getNbrFedWorkers() > 0) //looks if there are any fed workers : if yes activate the build
                if(stash.canAfford(b.getUpKeepList())) //check if you can afford the cost
                    rList.combineResourceList(b.getUpKeepList());
        }
        return rList;
    }


    //gets current production on the grid
    public ResourceList getProductionOnGrid(ResourceManager rs)
    {
        ResourceList rList = new ResourceList(0,0,0,0,0,0,0,0,0,0);
        for(Building b : buildings){
            if(rs.canAfford(b.getUpKeepList()))
                rList.combineResourceList(b.getProductionList());
        }
        return rList;
    }

    //gets all teh fed workers on the grid
    public ArrayList<Habitant> getFedWorkers()
    {
        ArrayList<Habitant> fedWorkers = new ArrayList<Habitant>();
        for(Habitant h : getListOfHabitants())
        {
            if(h.isFed() && h.getWorkPlace()!= null)
                fedWorkers.add(h);
        }
        return fedWorkers;
    }

    //gets all the workers regardless of if they are fed
    public ArrayList<Habitant> getWorkers()
    {
        ArrayList<Habitant> workers = new ArrayList<Habitant>();
        for(Habitant h : getListOfHabitants())
        {
            if(h.getWorkPlace()!= null)
                workers.add(h);
        }
        return workers;
    }

    //"harvests" all the production on grid , paying the upkeep and collecting the production
    public ArrayList<Building> harvestProductionOnGrid(ResourceList stash)
    {
        ArrayList<Building> prodBList = new ArrayList<Building>();
        feedWorkers(stash);
        for(Building b : buildings){
            if(stash.canAffordRL(b.getUpKeepList()) && (b.getNbrFedWorkers()>0 || b.checkForMaxWorkers()))
            {
                prodBList.add(b);
                stash.diffResourceList(b.getUpKeepList());
                stash.combineResourceList(b.getProductionList());
            }
        }
        return prodBList;
    }

    // tries to feed all the workers
    private void feedWorkers(ResourceList stash)
    {
        for(Habitant h : getWorkers())
        {
            h.feedHabitant(stash);
        }
    }

    //returns the number of Inhabitants

    public int getNbrOfInhabitants()
    {
        int total = 0;
        for(Building b : buildings){
            total += b.getMaxHabs();
        }
        return total;
    }


    // tries to assign a worker to a building, true if possible , false otherwise.
    public boolean putWorkerInBuilding(Habitant hab, Building wantedB)
    {
        if(wantedB == null)
        {
            return false;

        }
        if(!wantedB.checkForMaxWorkers())
        {
            wantedB.getWorkerList().add(hab);
            hab.setWorkPlace(wantedB);
            return true;
        }
        return false;
    }


    // tries to remove a worker to a building, true if possible , false otherwise.

    public boolean removeWorkerFromBuilding( Building wantedB)
    {
        if(wantedB == null)
            return false;
        if(!wantedB.getWorkerList().isEmpty())
        {
            Habitant hab = wantedB.getWorkerList().remove(0);
            hab.workPlace = null;
            return true;
        }
        else
            return false;
    }

    //removes all the workers from a building

    public void removeAllWorkersFromBuilding(Building b)
    {
        while(removeWorkerFromBuilding(b));
    }


    // tries to fill a building with workers, true if possible , false otherwise.
    public boolean fillBuildingWithWorkers(Building b)
    {
        for(Habitant h : getListOfUnemployed())
        {
            if(!putWorkerInBuilding(h,b))
            {
                return false;
            }
        }
        return true;
    }


    //returns the list of the unemployed workers
    public ArrayList<Habitant> getListOfUnemployed()
    {
        ArrayList<Habitant> habList = new ArrayList<Habitant>();
        for(Building b : buildings){
            for(Habitant h : b.getHabitantList())
            {
                if(h.getWorkPlace() == null)
                {
                    habList.add(h);
                }
            }
        }
        return habList;
    }

    // returns the list of all the inhabitants
    public ArrayList<Habitant> getListOfHabitants()
    {
        ArrayList<Habitant> habList = new ArrayList<Habitant>();
        for(Building b : buildings){
            habList.addAll(b.getHabitantList());
        }
        return habList;
    }


    // tries to remove a building from the grid, true if possible , false otherwise.

    public boolean removeBuildingFromGrid(Building wantedB)
    {
        if(wantedB == null)
        {
            return false;
        }
        else
        {
            removeAllWorkersFromBuilding(wantedB); //frees the workers
            clearBuildingLogos(wantedB); //cleans up the grid
            getBuildings().remove(wantedB); //removes the building from the grid
            return true;
        }
    }

    //placement checking tool verifies if the building if placed at x y would fit on the grid
    private boolean checkForSizeOnGrid(int x , int y ,Building wantedB)
    {
        return x + wantedB.getSize_x()-1 <= grid_x && y + wantedB.getSize_y()-1 <= grid_y && x >= 0 && y >= 0;
    }


    // checks if the coords aren't taken by another building and if it doesn't overlap another building
    public boolean checkCoordsAvailable(int x,int y,Building wantedB)
    {
        if(!checkForSizeOnGrid(x,y,wantedB))
        {
            System.out.println("Out of bounds");
            return false;

        }
        for(Building b : buildings)
        {
            if(b.getPos_x() == x && b.getPos_y() == y)
            {
                System.out.println("overlap other building base");
                return false;
            }
            for(int i = 0;i < wantedB.getSize_x();i++)
            {
                for(int j = 0; j < wantedB.getSize_y();j++)
                {
                    if(grid[y+j][x+i] != '¤')
                    {
                        System.out.println("edges of building overlap");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // goes through all the building on the grid and decrements the build delay timer
    public void updateBuildingDelay(){
        for(Building b : getBuildings())
        {
            if(b.getBuilding_delay()  > 0 && !b.built)
            {
                b.setBuilding_delay(b.getBuilding_delay()-1);
            }else if(b.getBuilding_delay() <= 0 && !b.built) //if the building will be finished this turn
            {
                replaceConstructionWithLogo(b); //replace the construction logo with thte building's logo
                b.built = true; // and set the building to built
            }
        }
    }

    //replaces the construction logos to the building's logo
    public void replaceConstructionWithLogo(Building wantedB)
    {
        for(int j = 0; j < wantedB.getSize_y();j++) {
            for (int i = 0; i < wantedB.getSize_x(); i++) {
                grid[wantedB.pos_y + j][wantedB.pos_x + i] = wantedB.getBuildingLogo();
            }

        }
    }


    //used to reset the part of the grid where there used to be a building
    private void clearBuildingLogos(Building wantedB)
    {
        for(int j = 0; j < wantedB.getSize_y();j++) {
            for (int i = 0; i < wantedB.getSize_x(); i++) {
                grid[wantedB.pos_y + j][wantedB.pos_x + i] = '¤';
            }
        }
    }


    // tries to place a building on the grid, true if possible , false otherwise. On success we add the building to the list

    public boolean placeBuildingOnGrid(Building wantedB)
    {
        if(checkCoordsAvailable(wantedB.pos_x, wantedB.pos_y, wantedB))
        {
            for(int j = 0; j < wantedB.getSize_y();j++)
            {
                for(int i = 0;i < wantedB.getSize_x();i++)
                {
                    if(wantedB.built) //the only case where a building can be built here is if we are loading from a save
                    {
                        grid[wantedB.pos_y+j][wantedB.pos_x+i] = wantedB.getBuildingLogo();
                    }
                    else
                    {
                        grid[wantedB.pos_y+j][wantedB.pos_x+i] = '!';
                    }

                }
            }
            buildings.add(wantedB);
            return true;
        }
        else
            return false;
    }
}
