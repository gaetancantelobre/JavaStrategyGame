import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    GameGrid grid; //visual representation of the buildings
    int dayCounter; // the number of days have passed in the game, allows to track the time it took to win.
    ResourceManager stash; // class containing a resourcelist that is your stash of resources in the game

    ResourceList final_goal = new ResourceList(100,100,100,100,100,100,100,100,100,100);


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



    //print functon showing all the stats of the game, printed every turn
    void printSUKP()
    {
        getGrid().printAndUpdateGrid();

        System.out.print("Current Stash :      " + getStash().getResourceList());
        System.out.print("Current Production : " + getGrid().getProductionOnGrid(getStash()).toStringSimple());
        System.out.print("Current UpKeep :     " + getGrid().getUpKeepOnGrid(getStash()).toStringSimple());
        System.out.println("Total population   : " + getGrid().getNbrOfInhabitants());
        System.out.println("Highest daily food cost :" + (getGrid().getWorkers().size()));
        System.out.println("Nbr of workers: " + getGrid().getWorkers().size());
        System.out.println("Nbr of fed workers   : " + getGrid().getFedWorkers().size());
        System.out.println("Nbr of unemployed   : " + getGrid().getListOfUnemployed().size());
        if(getGrid().getNbrOfInhabitants() > 0)
        {
            System.out.print("Unemployment rate  : " + (getGrid().getListOfUnemployed().size()* 100)/ getGrid().getNbrOfInhabitants() + "%\n");

        }
    }

    public Game(int size_x, int size_y) {
        this.grid = new GameGrid(size_x,size_y);
        stash = new ResourceManager();
        dayCounter = 0;
    }

    //getters and setters
    //returns the target goal that when reached means you beat the game
    public ResourceList getFinal_goal() {
        return final_goal;
    }

    public boolean checkEndGame()
    {
        return stash.getResourceList().isGreaterThan(final_goal);
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
