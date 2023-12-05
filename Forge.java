public class Forge extends Building{
    public Forge(int pos_x, int pos_y) {
        super(pos_x, pos_y);
        size_x = 1;
        size_y = 3;
        name = "Forge";
        buildingLogo = 'F';
        buildingCost = new ResourceList(0,0,50,20,0,10,0,0,0,5);
        productionList = new ResourceList(0,4,0,0,2,0,0,0,10,0);
        upKeepList = new ResourceList(0,10,10,0,0,2,0,0,0,0);
    }
}


