public class SteelMill extends Building{
    public SteelMill(int pos_x, int pos_y) {
        super(pos_x, pos_y);
        size_x = 5;
        size_y = 2;
        maxWorkers = 15;
        name = "SteelMill";
        buildingLogo = 'S';
        buildingCost = new ResourceList(0,0,100,50,0,6,0,0,0,0);
        productionList = new ResourceList(0,0,0,0,4,0,0,0,0,0);
        upKeepList = new ResourceList(0,4,0,0,0,0,0,0,0,4);
    }
}
