import java.util.HashMap;
import java.util.Map;

public class Habitation extends Building{
    HashMap<Integer,Integer> stat;
    int hab_lvl;
    public Habitation(int size_x, int size_y, Map<String, Integer> buildingCost, Map<String, Integer> productionList) {
        super(size_x, size_y, buildingCost, productionList);
        stat =  new HashMap<Integer, Integer>();
        hab_lvl = 0;
        stat.put(1,5);
        stat.put(2,10);
        stat.put(3,15);
    }

    public void setHab_lvl(int hab_lvl) {
        this.hab_lvl = hab_lvl;
    }

    public int getHab_lvl() {
        return hab_lvl;
    }
}
