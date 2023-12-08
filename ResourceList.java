import java.util.HashMap;

public class ResourceList {
    HashMap<String,Resource> prodList;


    // Resources lists are hashmaps containing a key for each type of resource
    //they are use for storage and transaction
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

    //checks if this RL can afford to substract the other RL
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


    //multiplies the RL by a factor
    public ResourceList multiplyResourceList(float multiplier)
    {
        ResourceList rl = new ResourceList(0,0,0,0,0,0,0,0,0,0);
        for (HashMap.Entry<String, Resource> entry : prodList.entrySet()) {
            String key = entry.getKey(); //get the type of resource
            int value = entry.getValue().getValue(); //get the number of the value of current resource
            rl.getProdList().get(key).setValue((int) (value*multiplier));
        }
        return rl;
    }

    //checks if the RL has no resources stocked
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

    // sets  a specific resource in th RL to a value
    public void setResource(String key, int value) {
        getProdList().get(key).setAmount(value);
    }


    // combines to RL into 1
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

    // checks if this RL is greater of resources for each resource
    public boolean isGreaterThan(ResourceList rList)
    {
        if(rList != null)
        {
            for (HashMap.Entry<String, Resource> entry : rList.getProdList().entrySet()) {
                String key = entry.getKey(); //get the type of resource
                int value = entry.getValue().getValue(); //get the number of the value of current resource
                if(prodList.get(key).getValue() < value)
                    return false;
            }
        }
        return true;
    }


    //Substracts this RL by rList
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


    //returns a string with all the resources asn their quantities
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


    // same thing as toString() but does print resources that are at 0
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
