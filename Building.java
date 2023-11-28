import java.util.Map;

public abstract  class Building {
    int size_x;
    int size_y;
    int pos_x;
    int pos_y;

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
        return productionList;
    }

    public Building(int pos_x, int pos_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;

    }

    ResourceList productionList;
}
