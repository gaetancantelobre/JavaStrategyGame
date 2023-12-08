import java.util.ArrayList;
import java.util.Map;

public abstract  class Building {
    int size_x;
    int size_y;
    int pos_x;
    int pos_y;


    boolean built;

    int building_delay; // the number of days it takes to build a building


    String name;


    ArrayList<Habitant> habitantList; //list of people living in the building (only used for Habitation)
    int maxHabs;
    ArrayList<Habitant> workerList; //list of workers that are assigned to the building
    int maxWorkers;

    ResourceList productionList; // what the building will produce when the best conditions are met

    ResourceList constructionList; // construction cost of the building

    ResourceList upKeepList; // what the building needs to be able to produce every turn
    char buildingLogo; //the char that represents the building in the grid
    ResourceList buildingCost;


 // getter and setters
 public int getBuilding_delay() {
     return building_delay;
 }

 public void setBuilding_delay(int building_delay) {
     this.building_delay = building_delay;
 }

    public String getName() {
        return name;
    }


    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public ArrayList<Habitant> getHabitantList() {
        return habitantList;
    }

    public int getMaxHabs() {
        return maxHabs;
    }

    public ArrayList<Habitant> getWorkerList() {
        return workerList;
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }

    public ResourceList getConstructionList() {
        return constructionList;
    }

    public ResourceList getUpKeepList() {
        return upKeepList;
    }

    public ResourceList getBuildingCost() {
        return buildingCost;
    }


    public int getSize_x() {
        return size_x;
    }

    public int getSize_y() {
        return size_y;
    }

    public char getBuildingLogo() {
        return buildingLogo;
    }


    public boolean isBuilt() {
        return built;
    }

    int getNbrFedWorkers() // returns the list of workers that are fed
    {
        int cpt = 0;
        for(Habitant h : getWorkerList())
        {
            if(h.isFed())
                cpt++;

        }
        return cpt;
    }

    //returns the adjusted resource list according to the number of fed workers
    public ResourceList getProductionList() {
        if(maxWorkers == 0)
            return  productionList;
        if(built)
        {
            return productionList.multiplyResourceList((float) getNbrFedWorkers() /maxWorkers);
        }
        else
            return new ResourceList(0,0,0,0,0,0,0,0,0,0);
    }
    //checks to see if the building is full
    public boolean checkForMaxWorkers() {
        return workerList.size() == maxWorkers;
    }

    //Overrides toString to get a good string t  print to represent the building
    @Override
    public String toString() {
        return getClass() + " at (" + getPos_x() + " " + getPos_y() + ") CWF : " + getWorkerList().size() + " Maxed Out : " + checkForMaxWorkers();
    }
    //returns the string containing the building info for the save files
    public String saveBuildingString() {
        return buildingLogo + "\n" + getPos_x() + "\n" + getPos_y() + "\n" + getWorkerList().size() + "\n" + building_delay + "\n" + built;
    }

    //returns a string that represents all the buying info , size , etc
    public String buyingOptions()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Size : (").append(getSize_x()).append(",").append(getSize_y()).append(")\n");
        stringBuilder.append("Cost : ").append(getBuildingCost().toStringSimple());
        if(!upKeepList.checkIfEmpty())
        {
            stringBuilder.append("UpKeep : ").append(getUpKeepList().toStringSimple());
        }
        if(!productionList.checkIfEmpty())
        {
            stringBuilder.append("Prod : ").append(productionList.toStringSimple());
        }
        return stringBuilder.toString();
    }

    public Building(int pos_x, int pos_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.built = false;
        habitantList = new ArrayList<Habitant>();
        workerList = new ArrayList<Habitant>();
    }

}
