import java.util.ArrayList;

public class GameGrid {
    char[][] grid;
    private ArrayList<Building> buildings= new ArrayList<Building>();

    int grid_x;
    int grid_y;

    public GameGrid(int size_x ,int size_y) {
        this.grid = new char[size_x ][size_y];
        grid_x = size_x;
        grid_y = size_y;
        for(int i = 0;i < size_x;i++) {
            for (int j = 0; j < size_y; j++) {
                grid[i][j] = '¤';
            }
        }
    }

    public void printAndUpdateGrid()
    {
        for(int i = 0;i < grid_x;i++) {
            System.out.print("-");
        }
        System.out.print("\n");

        for(int i = 0;i < grid_x;i++) {
            for (int j = 0; j < grid_y; j++) {
                System.out.print(" ");
                System.out.print(grid[i][j]);            }
            System.out.print('\n');
        }

        for(int i = 0;i < grid_x;i++) {
            System.out.print("-");
        }
        System.out.print("\n");
    }

    public ResourceList getProductionOnGrid()
    {
        ResourceList rList = new ResourceList(0,0,0,0,0,0,0,0,0);
        for(Building b : buildings){
            rList.combineResourceList(b.getProductionList());
        }
        return rList;
    }

    private boolean checkForSizeOnGrid(int x , int y ,Building wantedB)
    {
        return x + wantedB.getSize_x() < grid_x && y + wantedB.getSize_y() < grid_y;
    }

    private boolean checkCoordsAvailable(int x,int y,Building wantedB)
    {
        if(!checkForSizeOnGrid(x,y,wantedB))
            return false;
        for(Building b : buildings)
        {
            if(b.getSize_x() == x || b.getSize_y() == y)
            {
                return false;
            }
            for(int i = 0;i < wantedB.getSize_x();i++)
            {
                for(int j = 0; j < wantedB.getSize_y();j++)
                {
                    if(grid[x+i][y+i] != '¤')
                        return false;
                }
            }
        }
        return true;
    }

    public boolean placeBuildingOnGrid(int x , int y,Building wantedB)
    {
        if(checkCoordsAvailable(x,y,wantedB))
        {
            for(int i = 0;i < wantedB.getSize_x();i++)
            {

                for(int j = 0; j < wantedB.getSize_y();j++)
                {
                    grid[x+i][y+j] = wantedB.getBuildingLogo();

                }
            }
            buildings.add(wantedB);
            return true;
        }
        else
            return false;

    }
}
