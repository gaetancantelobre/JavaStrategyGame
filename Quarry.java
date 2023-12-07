public class Quarry extends Building{
    public Quarry(int pos_x, int pos_y) {
        super(pos_x, pos_y);
        size_x = 2;
        size_y = 2;
        name = "Quarry";
        buildingLogo = 'Q';
        maxWorkers =  10;
        buildingCost = new ResourceList(0,0,50,0,0,4,0,0,0,0);
        productionList = new ResourceList(0,10,0,12,0,2,0,0,0,9);
        upKeepList = new ResourceList(0,0,10,0,0,0,0,0,0,0);
    }
}
