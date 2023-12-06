import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    GameGrid grid;
    int dayCounter;
    ResourceManager stash;

    public int getDayCounter() {
        return dayCounter;
    }

    public boolean overwriteGame(Game g)
    {
        if(g == null)
            return false;
        grid = g.getGrid();
        dayCounter = g.getDayCounter();
        stash = g.getStash();
        return true;
    }
    public ResourceList getFinal_goal() {
        return final_goal;
    }

    void printSUKP()
    {
        getGrid().printAndUpdateGrid();

        System.out.print("Current Stash :      " + getStash().getResourceList());
        System.out.print("Current Production : " + getGrid().getProductionOnGrid(getStash()).toStringSimple());
        System.out.print("Current UpKeep :     " + getGrid().getUpKeepOnGrid(getStash()).toStringSimple());
        System.out.println("Total population   : " + getGrid().getNbrOfInhabitants());
        if(getGrid().getNbrOfInhabitants() > 0)
        {
            System.out.print("Unemployment rate  : " + (getGrid().getListOfUnemployed().size()* 100)/ getGrid().getNbrOfInhabitants() + "%\n");

        }
    }

    public ResourceList getNetGainOnGrid()
    {
        ResourceList gain = getGrid().getProductionOnGrid();
        gain.diffResourceList(getGrid().getUpKeepOnGrid(getStash()));
        return gain;
    }
    ResourceList final_goal = new ResourceList(100,100,100,100,100,100,100,100,100,100);


    public Game(int size_x, int size_y) {
        this.grid = new GameGrid(size_x,size_y);
        stash = new ResourceManager();
        dayCounter = 0;
    }

    public boolean checkEndGame()
    {
        return final_goal.getProdList() == stash.getResourceList().getProdList();
    }

    public void increaseDayCounter() {
        this.dayCounter++;
    }

    public GameGrid getGrid() {
        return grid;
    }

    public ResourceManager getStash() {
        return stash;
    }



    public ArrayList<Habitant> getUnemployed()
    {
        return getGrid().getListOfUnemployed();
    }

    public ArrayList<Habitant> getHabitants()
    {
        return getGrid().getListOfHabitants();
    }

    public Game(GameGrid grid) {
        this.grid = grid;
    }


}
