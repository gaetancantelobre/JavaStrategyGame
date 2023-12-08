import java.util.HashMap;
import java.util.Map;

public class Habitation extends Building{

    public Habitation(int pos_x, int pos_y) {
        super(pos_x, pos_y);
        size_x = 1;
        size_y = 1;
        name = "Habitation";
        this.maxHabs = 20;
        buildingLogo = 'H';
        buildingCost = new ResourceList(0,0,1,0,0,1,0,0,0,0);
        productionList = new ResourceList(0,0,0,0,0,1,1,0,0,0);
        upKeepList = new ResourceList(0,0,0,0,0,0,0,0,0,0);

        for(int i = 0; i < maxHabs; i++){ //this building creates a certain number of inhabitants
            habitantList.add(new Habitant());
        }
    }

}
