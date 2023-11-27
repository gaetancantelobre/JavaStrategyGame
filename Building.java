import java.util.Map;

public abstract  class Building {
    int size_x;
    int size_y;
    Map<String, Integer> buildingCost;

    public Building(int size_x, int size_y, Map<String, Integer> buildingCost, Map<String, Integer> productionList) {
        this.size_x = size_x;
        this.size_y = size_y;
        this.buildingCost = buildingCost;
        this.productionList = productionList;
    }

    Map<String, Integer> productionList;
}
