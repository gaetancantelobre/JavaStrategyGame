import java.util.ArrayList;
import java.util.HashMap;

public class ResourceManager {

    ResourceList resourceList = new ResourceList(0,0,0,0,0,0,0,0,0);


    public Resource getResource(String resourceType) {
        return resourceList.getProdList().get(resourceType);
    }

    public ResourceList getResourceList() {
        return resourceList;
    }

    public boolean checkForResources(ResourceList rList) //checks to see if the resources needed in the RS are present
    {
        for (HashMap.Entry<String, Resource> entry : rList.getProdList().entrySet()) {
            String key = entry.getKey(); //get the type of resource
            int value = entry.getValue().getValue(); //get the number of the value of current resource
            if(value > getResource(key).getValue())
            {
                return false;
            }
        }
        return true;
    }

    public void addResources(ResourceList resList)
    {
        resourceList.combineResourceList(resList);
    }

    public ResourceManager() {
        resourceList = new ResourceList(0,0,0,0,0,0,0,0,0);
    }

    public ResourceManager(ResourceList resourceList) {
        this.resourceList = resourceList;
    }
}
