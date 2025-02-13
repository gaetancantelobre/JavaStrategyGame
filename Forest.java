import java.util.ArrayList;

public class Forest extends Building{

    public Forest(int pos_x, int pos_y) {
        super(pos_x, pos_y);

        size_x = 2;
        size_y = 3;
        name = "Forest";
        maxWorkers = 10;

        buildingLogo = 'F';
        building_delay = 5;

        buildingCost = new ResourceList(0,0,0,10,0,6,0,0,4,0);
        productionList = new ResourceList(20,0,0,0,0,0,0,0,0,0);
        upKeepList = new ResourceList(0,0,0,0,0,0,0,0,0,0);
    }
}
