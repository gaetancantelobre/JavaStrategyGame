import java.util.ArrayList;
import java.util.Map;

public abstract  class Building {
    int size_x;
    int size_y;
    int pos_x;
    int pos_y;

    String name;

    public String getName() {
        return name;
    }

    ArrayList<Habitant> habitantList;
    int maxHabs;
    ArrayList<Habitant> workerList;
    int maxWorkers;

    ResourceList productionList;

    ResourceList constructionList;

    ResourceList upKeepList;



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

    char buildingLogo;
   ResourceList buildingCost;

    public ResourceList getProductionList() {
        if(checkForMaxWorkers())
            return  productionList;
        else
            return new ResourceList(0,0,0,0,0,0,0,0,0,0);
    }

    public boolean checkForMaxWorkers()
    {
        return workerList.size() == maxWorkers;
    }

    @Override
    public String toString() {
        return getClass() + " at (" + getPos_x() + " " + getPos_y() + ") CWF : " + getWorkerList().size();
    }

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
        habitantList = new ArrayList<Habitant>();
        workerList = new ArrayList<Habitant>();
    }

}
