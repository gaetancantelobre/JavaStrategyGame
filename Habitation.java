import java.util.HashMap;
import java.util.Map;

public class Habitation extends Building{

    int hab_lvl;

    public Habitation(int pos_x, int pos_y, int hab_lvl) {
        super(pos_x, pos_y);
        size_x = 1;
        size_y = 1;
        name = "Habitation";
        this.maxHabs = 20;
        buildingLogo = 'H';
        buildingCost = new ResourceList(0,0,1,0,0,1,0,0,0,0);
        productionList = new ResourceList(0,0,0,0,0,1,1,0,0,0);
        upKeepList = new ResourceList(0,0,0,0,0,0,0,0,0,0);

        for(int i = 0; i < maxHabs; i++){
            habitantList.add(new Habitant());
        }
    }

    public void setHab_lvl(int hab_lvl) {
        this.hab_lvl = hab_lvl;
    }

    public int getHab_lvl() {
        return hab_lvl;
    }

    public int getNbrHabitants()
    {
        return habitantList.size();
    }
}
