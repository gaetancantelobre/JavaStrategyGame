public class ToolFactory extends Building{
    public ToolFactory(int pos_x, int pos_y) {
        super(pos_x, pos_y);
        size_x = 4;
        size_y = 4;
        name = "ToolFactory";
        buildingLogo = 'T';
        buildingCost = new ResourceList(0,0,50,50,0,6,0,0,0,0);
        productionList = new ResourceList(0,0,0,0,4,0,0,0,0,0);
        upKeepList = new ResourceList(0,2,0,0,0,0,0,0,0,4);
    }
}
