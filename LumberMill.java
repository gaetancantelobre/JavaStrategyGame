import java.util.ArrayList;

public class LumberMill extends Building{

    public LumberMill(int pos_x, int pos_y) {
        super(pos_x, pos_y);

        size_x = 3;
        size_y = 3;
        name = "LumberMill";
        maxWorkers = 10;

        buildingLogo = 'L';


        buildingCost = new ResourceList(0,0,50,50,0,6,0,0,0,0);
        productionList = new ResourceList(0,0,4,0,0,0,0,0,0,0);
        upKeepList = new ResourceList(0,0,4,0,0,0,0,0,0,0);
    }
}
