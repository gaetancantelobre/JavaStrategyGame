import java.util.HashMap;

public class ProductionList {
    HashMap<String,Integer> prodList;

    public ProductionList(HashMap<String, Integer> prodList,int lumber, int coal , int wood, int stone , int steel, int gold, int food, int cement) {
        this.prodList = prodList;
        prodList.put("lumber",lumber);
        prodList.put("coal",coal);
        prodList.put("wood",wood);
        prodList.put("lumber",lumber);
        prodList.put("stone",stone  );
        prodList.put("steel",steel);
        prodList.put("gold",gold);
        prodList.put("food",food);
        prodList.put("cement",cement);
    }
}
