public class Quarry extends Building{
    public Quarry(int pos_x, int pos_y) {
        super(pos_x, pos_y);
        size_x = 2;
        size_y = 2;
        name = "Quarry";
        buildingLogo = 'Q';
        buildingCost = new ResourceList(0,0,50,0,0,4,0,0,0,0);
        productionList = new ResourceList(0,4,0,4,0,2,1,0,0,4);
        upKeepList = new ResourceList(0,2,10,0,0,0,0,0,0,0);
    }
}
