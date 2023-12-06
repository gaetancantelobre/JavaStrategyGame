import java.util.HashMap;

public class ResourceList {
    HashMap<String,Resource> prodList;

    public ResourceList( int lumber, int coal , int wood, int stone , int steel, int gold, int food, int cement,int tools,int iron) {
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
        prodList.put("iron",new Iron(iron));

    }

    public boolean canAffordRL(ResourceList RL)
    {
        for (HashMap.Entry<String, Resource> entry : RL.getProdList().entrySet()) {
            String key = entry.getKey(); //get the type of resource
            int value = entry.getValue().getValue(); //get the number of the value of current resource
            if(value > prodList.get(key).amount)
                return false;
        }
        return true;
    }

    public HashMap<String, Resource> getProdList() {
        return prodList;
    }


    public boolean checkIfEmpty()
    {
            for (HashMap.Entry<String, Resource> entry : getProdList().entrySet()) {
                int value = entry.getValue().getValue(); //get the number of the value of current resource
                if(value != 0)
                {
                    return false;
                }
            }
            return true;
    }

    public void setResource(String key, int value) {
        getProdList().get(key).setAmount(value);
    }

    public void combineResourceList(ResourceList rList)
    {
        if(rList != null)
        {
            for (HashMap.Entry<String, Resource> entry : rList.getProdList().entrySet()) {
                String key = entry.getKey(); //get the type of resource
                int value = entry.getValue().getValue(); //get the number of the value of current resource
                prodList.get(key).addRemoveValue(value);
            }
        }

    }


    public void diffResourceList(ResourceList rList)
    {
        if(rList != null)
        {
            for (HashMap.Entry<String, Resource> entry : rList.getProdList().entrySet()) {
                String key = entry.getKey(); //get the type of resource
                int value = entry.getValue().getValue(); //get the number of the value of current resource
                prodList.get(key).addRemoveValue(value*-1);
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder cat = new StringBuilder();
        for (HashMap.Entry<String, Resource> entry : getProdList().entrySet()) {
            String key = entry.getKey(); //get the type of resource
            int value = entry.getValue().getValue(); //get the number of the value of current resource
             cat.append(key).append(" : ").append(value).append("|");
        }
        cat.append("\n");
        return cat.toString();
    }

    public String toStringSimple() {
        StringBuilder cat = new StringBuilder();
        int cpt = 0;
        for (HashMap.Entry<String, Resource> entry : getProdList().entrySet()) {
            String key = entry.getKey(); //get the type of resource
            int value = entry.getValue().getValue(); //get the number of the value of current resource
            if(value != 0)
            {
                cat.append(key).append(" : ").append(value).append("|");
                cpt++;

            }
        }
        if(cpt == 0)
        {
            cat.append("None");
        }
        cat.append("\n");
        return cat.toString();
    }

}
