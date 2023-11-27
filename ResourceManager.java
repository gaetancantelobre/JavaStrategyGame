import java.util.ArrayList;

public class ResourceManager {
    private Cement cementMana;
    private Coal coalMana;
    private Food foodMana;
    private Gold goldMana;
    private Lumber lumberMana;
    private Steel steelMana;
    private Stone stoneMana;
    private Tools toolsMana;
    private Wood woodMana;

    public Cement getCementMana() {
        return cementMana;
    }

    public Coal getCoalMana() {
        return coalMana;
    }

    public Food getFoodMana() {
        return foodMana;
    }

    public Gold getGoldMana() {
        return goldMana;
    }

    public Lumber getLumberMana() {
        return lumberMana;
    }

    public Steel getSteelMana() {
        return steelMana;
    }

    public Stone getStoneMana() {
        return stoneMana;
    }


    public Tools getToolsMana() {
        return toolsMana;
    }

    public Wood getWoodMana() {
        return woodMana;
    }

    public boolean checkForResources(ArrayList<Resource> resList)
    {
        boolean haveAccess = true;
        for(Resource r : resList)
        {
            switch(r.getType()){
                case "Cement":
                    getCementMana().addRemoveValue(r.getValue());
                case "Coal":
                    getCoalMana().addRemoveValue(r.getValue());
                case "Food":
                    getFoodMana().addRemoveValue(r.getValue());
                case "Gold":
                    getGoldMana().addRemoveValue(r.getValue());
                case "Lumber":
                    getLumberMana().addRemoveValue(r.getValue());
                case "Steel":
                    getSteelMana().addRemoveValue(r.getValue());
                case "Stone":
                    getSteelMana().addRemoveValue(r.getValue());
                case "Tools":
                    getToolsMana().addRemoveValue(r.getValue());
                case "Wood":
                    getWoodMana().addRemoveValue(r.getValue());
                default:
                    return false;
            }
        }
        return false;
    }

    public void addResources(ArrayList<Resource> resList)
    {
        for(Resource r : resList)
        {
            switch(r.getType()){
                case "Cement":
                    getCementMana().addRemoveValue(r.getValue());
                case "Coal":
                    getCoalMana().addRemoveValue(r.getValue());
                case "Food":
                    getFoodMana().addRemoveValue(r.getValue());
                case "Gold":
                    getGoldMana().addRemoveValue(r.getValue());
                case "Lumber":
                    getLumberMana().addRemoveValue(r.getValue());
                case "Steel":
                    getSteelMana().addRemoveValue(r.getValue());
                case "Stone":
                    getSteelMana().addRemoveValue(r.getValue());
                case "Tools":
                    getToolsMana().addRemoveValue(r.getValue());
                case "Wood":
                    getWoodMana().addRemoveValue(r.getValue());

            }
        }
    }

    public ResourceManager() {
        cementMana = new Cement(0);
        coalMana = new Coal(0);
        foodMana = new Food(0);
        goldMana = new Gold(0);
        lumberMana = new Lumber(0);
        steelMana = new Steel(0);
        stoneMana = new Stone(0);
        toolsMana = new Tools(0);
        woodMana = new Wood(0);
    }
}
