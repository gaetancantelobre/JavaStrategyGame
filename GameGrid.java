import java.util.ArrayList;

public class GameGrid {
    char[][] grid;
    private ArrayList<Building> buildings= new ArrayList<Building>();

    int grid_x;

    public char[][] getGrid() {
        return grid;
    }

    public int getGrid_x() {
        return grid_x;
    }

    public int getGrid_y() {
        return grid_y;
    }

    int grid_y;

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public GameGrid(int size_x , int size_y) {
        this.grid = new char[size_x ][size_y];
        grid_x = size_x;
        grid_y = size_y;
        for(int i = 0;i < size_x;i++) {
            for (int j = 0; j < size_y; j++) {
                grid[i][j] = '¤';
            }
        }
    }

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


    public ResourceList getUpKeepOnGrid(ResourceManager stash){
        ResourceList rList = new ResourceList(0,0,0,0,0,0,0,0,0,0);
        for(Building b : buildings){
            if(b.checkForMaxWorkers())
                if(stash.canAfford(b.getUpKeepList()))
                    rList.combineResourceList(b.getUpKeepList());
        }
        return rList;
    }



    public ResourceList getProductionOnGrid(ResourceManager rs)
    {
        ResourceList rList = new ResourceList(0,0,0,0,0,0,0,0,0,0);
        for(Building b : buildings){
            if(rs.canAfford(b.getUpKeepList()))
                rList.combineResourceList(b.getProductionList());
        }
        return rList;
    }

    public ResourceList getProductionOnGrid()
    {
        ResourceList rList = new ResourceList(0,0,0,0,0,0,0,0,0,0);
        for(Building b : buildings){
                rList.combineResourceList(b.getProductionList());
        }
        return rList;
    }

    public void harvestProductionOnGrid(ResourceList stash)
    {
        for(Building b : buildings){
            if(stash.canAffordRL(b.getUpKeepList()))
            {
                stash.diffResourceList(b.getUpKeepList());
                stash.combineResourceList(b.getProductionList());
            }
        }
    }


    public int getNbrOfInhabitants()
    {
        int total = 0;
        for(Building b : buildings){
            total += b.getMaxHabs();
        }
        return total;
    }

    public boolean putWorkerInBuilding(Habitant hab, Building wantedB)
    {
        if(wantedB == null)
            return false;
        if(!wantedB.checkForMaxWorkers())
        {
            wantedB.getWorkerList().add(hab);
            hab.setWorkPlace(wantedB);
            return true;
        }
        return false;
    }

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

    public void removeAllWorkersFromBuilding(Building b)
    {
        while(removeWorkerFromBuilding(b));
    }

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

    Building findBuildingWithCoordinates(int x, int y)
    {
        if( x >= grid_x || y >= grid_y)
        {
            return null;
        }
        else
        {
            for(Building b : getBuildings())
            {
                if(b.getPos_x() == x && b.pos_y == y)
                {
                    return b;
                }
            }
        }
        return null;
    }

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

    public ArrayList<Habitant> getListOfHabitants()
    {
        ArrayList<Habitant> habList = new ArrayList<Habitant>();
        for(Building b : buildings){
            habList.addAll(b.getHabitantList());
        }
        return habList;
    }

    public boolean removeBuildingFromGrid(Building wantedB)
    {
        if(wantedB == null)
        {
            return false;
        }
        else
        {
            removeWorkerFromBuilding(wantedB);
            getBuildings().remove(wantedB);
            return true;
        }
    }



    private boolean checkForSizeOnGrid(int x , int y ,Building wantedB)
    {
        return x + wantedB.getSize_x() < grid_x && y + wantedB.getSize_y() < grid_y && x >= 0 && y >= 0;
    }

    public boolean checkCoordsAvailable(int x,int y,Building wantedB)
    {
        if(!checkForSizeOnGrid(x,y,wantedB))
            return false;
        for(Building b : buildings)
        {
            if(b.getPos_x() == x && b.getPos_y() == y)
            {
                return false;
            }
            for(int i = 0;i < wantedB.getSize_x();i++)
            {
                for(int j = 0; j < wantedB.getSize_y();j++)
                {
                    if(grid[x+i][y+i] != '¤')
                        return false;
                }
            }
        }
        return true;
    }

    public boolean placeBuildingOnGrid(Building wantedB)
    {
        if(checkCoordsAvailable(wantedB.pos_x, wantedB.pos_y, wantedB))
        {
            for(int i = 0;i < wantedB.getSize_x();i++)
            {
                for(int j = 0; j < wantedB.getSize_y();j++)
                {
                    grid[wantedB.pos_x+i][wantedB.pos_y+j] = wantedB.getBuildingLogo();
                }
            }
            buildings.add(wantedB);
            return true;
        }
        else
            return false;
    }
}