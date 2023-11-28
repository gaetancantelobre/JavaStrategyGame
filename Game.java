import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    GameGrid grid;

    ResourceManager stash;


    public Game(int size_x, int size_y) {
        this.grid = new GameGrid(size_x,size_y);
        stash = new ResourceManager();
    }

    public GameGrid getGrid() {
        return grid;
    }

    public ResourceManager getStash() {
        return stash;
    }

    public void printResources()
    {
        System.out.println("STASH :");
        for (HashMap.Entry<String, Resource> entry : stash.getResourceList().getProdList().entrySet()) {
            String key = entry.getKey(); //get the type of resource
            int value = entry.getValue().getValue(); //get the number of the value of current resource
            System.out.print( key + " : " + value + "|");
        }
        System.out.print("\n");
    }

    public Game(GameGrid grid) {
        this.grid = grid;
    }


}
