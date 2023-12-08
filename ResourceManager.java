import java.util.ArrayList;
import java.util.HashMap;

public class ResourceManager {

    ResourceList resourceList = new ResourceList(0,0,0,0,0,0,0,0,0,0);


    public Resource getResource(String resourceType) {
        return resourceList.getProdList().get(resourceType);
    }
    public void setResource(String key, int value){ getResourceList().setResource(key,value);
    }
    public ResourceList getResourceList() {
        return resourceList;
    }

    //checks if its RL can afford to substract the cost
    public boolean canAfford(ResourceList cost)
    {
        for (HashMap.Entry<String, Resource> entry : cost.getProdList().entrySet()) {
            String key = entry.getKey(); //get the type of resource
            int value = entry.getValue().getValue(); //get the number of the value of current resource
            if(( getResource(key).getValue()-value) < 0)
                return false;
        }
        return true;
    }



    public ResourceManager() {
        resourceList = new ResourceList(5,15,20,30,0,10,10,0,20,0);
    }

}
