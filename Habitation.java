import java.util.HashMap;
import java.util.Map;

public class Habitation extends Building{
    HashMap<Integer,Integer> habitants;
    int hab_lvl;

    public Habitation(int pos_x, int pos_y, int hab_lvl) {
        super(pos_x, pos_y);
        size_x = 2;
        size_y = 2;
        this.hab_lvl = hab_lvl;
        buildingLogo = 'H';
    }

    public void setHab_lvl(int hab_lvl) {
        this.hab_lvl = hab_lvl;
    }

    public int getHab_lvl() {
        return hab_lvl;
    }

    public int getNbrHabitants()
    {
        return habitants.get(getHab_lvl());
    }
}
