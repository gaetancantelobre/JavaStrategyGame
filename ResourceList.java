import java.util.HashMap;

public class ResourceList {
    HashMap<String,Resource> prodList;

    public ResourceList( int lumber, int coal , int wood, int stone , int steel, int gold, int food, int cement,int tools) {
        prodList = new HashMap<String,Resource>();
        prodList.put("lumber",new Lumber(lumber));
        prodList.put("coal",new Coal(coal));
        prodList.put("wood",new Wood(wood));
        prodList.put("stone",new Stone(stone)  );
        prodList.put("steel",new Steel(steel));
        prodList.put("gold",new Gold(gold));
        prodList.put("food",new Food(food));
        prodList.put("cement",new Cement(cement));
        prodList.put("tools",new Tools(tools));
    }

    public HashMap<String, Resource> getProdList() {
        return prodList;
    }

    public void combineResourceList(ResourceList rList)
    {
        for (HashMap.Entry<String, Resource> entry : rList.getProdList().entrySet()) {
            String key = entry.getKey(); //get the type of resource
            int value = entry.getValue().getValue(); //get the number of the value of current resource
            prodList.get(key).addRemoveValue(value);
        }
    }


}
