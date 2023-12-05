import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException {
        printIntroAndRules();
        Game game = setupGame();
       while(!game.checkEndGame())
       {
           System.out.println("Day : " + game.getDayCounter());
           printMenuOptions(game);
           game.printSUKP();
       }
    }


    static void printIntroAndRules() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("intro.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
    static Game setupGame()
    {
        Game game = new Game(10,10);
        game.getGrid().printAndUpdateGrid();
        return game;
    }

    static void printMenuOptions(Game game)
    {
        System.out.println("1. Build a building.");
        System.out.println("2. Manage Population.");
        System.out.println("3. Show Current Stats and All buildings?");
        System.out.println("4. Go to next day");
        Scanner userInput = new Scanner(System.in);
        String answer = userInput.nextLine();
        switch (answer){
            case "1": printBuildingOptions(game);break;
            case "2": managePopulation(game);break;
            case "3": showTheStatsOfTheBuildings(game);break;
            case "4": collectPayGoNextDay(game);break;
            default:
                System.out.println("Enter a correct selection number from above");break;
        }
    }

    private static void collectPayGoNextDay(Game game) {
       game.getGrid().harvestProductionOnGrid(game.getStash().getResourceList());
        game.increaseDayCounter();
    }

    private static void showTheStatsOfTheBuildings(Game game) {
        chooseBuilding(game,false,false);
        printMenuOptions(game);
    }

    private static void managePopulation(Game game){
        if(!game.getHabitants().isEmpty())
        {
            if(!game.getUnemployed().isEmpty())
            {
                System.out.println("1. fill building with workers ?");
                System.out.println("2. add 1 worker to a building ?");
            }

            System.out.println("3. Remove a workers from a building ?");
            System.out.println("4. Remove all workers from a building ?");

            Scanner userInput = new Scanner(System.in);
            String answer = userInput.nextLine();
            switch (answer) {
                case "1":
                    game.getGrid().fillBuildingWithWorkers(chooseBuilding(game,true,true));break;
                case "2":
                    game.getGrid().putWorkerInBuilding(game.getUnemployed().get(0), Objects.requireNonNull(chooseBuilding(game,true,true)));break;
                case "3":
                    game.getGrid().removeWorkerFromBuilding(chooseBuilding(game,true,true));break;
                case "4":
                    game.getGrid().removeAllWorkersFromBuilding(chooseBuilding(game,true,true));break;
            }
        }else
        {
            System.out.println("Your city has a population of 0, pls consider building habitations as they are your source of workers");
        }

    }

    private static Building chooseBuilding(Game game, boolean choose,boolean filling) {
        int cpt = 0;
        if(!game.getGrid().getBuildings().isEmpty())
        {
            for(Building b : game.getGrid().getBuildings())
            {
                if(filling && !b.checkForMaxWorkers())
                    System.out.println(cpt + " : " + b);

                cpt++;
            }
            if(choose)
            {
                System.out.println("Enter the number of building you want to select, enter -1 to cancel");
                Scanner userInput = new Scanner(System.in);
                String answer = userInput.nextLine();
                int nbr = -1;
                try {
                    nbr = Integer.parseInt(answer);
                }
                catch(Exception e) {
                    System.out.println("Pls enter a valid interger value");
                }

                if(nbr == -1 || nbr > cpt-1 )
                {
                    return null;
                }
                else
                    return game.getGrid().getBuildings().get(nbr);
            }
        }
        else {
            System.out.println("\nYou have no buildings build some ! \n");
            return null;
        }

        return null;
    }

    private static void buildBuilding(Building wantedB,Game game)
    {
        System.out.println("Enter wanted coordinates of the building by entering the x coordinate pressing enter then entering the y coordinate.");
        Scanner userInput = new Scanner(System.in);
        String answer;
        int x = -1;
        int y = -1;
        try {
            answer = userInput.nextLine();
            x = Integer.parseInt(String.valueOf(answer));
            answer = userInput.nextLine();
            y = Integer.parseInt(String.valueOf(answer));
        }
        catch(Exception e) {
            System.out.println("pls enter correct interger values , enter -1 -1 as coordinates to cancel");
            buildBuilding(wantedB,game);
        }
        finally {
            if(!game.getGrid().checkCoordsAvailable(x,y,wantedB))
            {
                System.out.println("The coordinates are not available or will overlap on other building or out of bounds");
            }
            if(x == -1 || y == -1)
                System.out.println("Canceled the building of the structure");
            else
            {
                wantedB.setPos_x(x);
                wantedB.setPos_y(y);
                if(game.getStash().canAfford(wantedB.getBuildingCost()))
                {
                    game.getStash().getResourceList().diffResourceList(wantedB.getBuildingCost());
                    if(game.getGrid().placeBuildingOnGrid(wantedB))
                    {
                        System.out.println("Building : " + wantedB.getName() + " built correctly");
                        game.increaseDayCounter();
                    }else
                    {
                        System.out.println("broke");
                    }

                }
                else {
                    System.out.println("You can not afford this building !");
                }
        }
        }
    }

    static void printBuildingOptions(Game game)
    {
        ArrayList<Building> bList = new ArrayList<Building>();
        bList.add(new Habitation(-1,-1,1));
        bList.add(new LumberMill(-1,-1));
        bList.add(new Quarry(-1,-1));
        bList.add(new Forest(-1, -1));
        bList.add(new CementPlant(-1,-1));
        bList.add(new Forge(-1,-1));

        for(Building b : bList)
        {
            System.out.println(b.getName() + ": \n" + b.buyingOptions());
        }
        game.getGrid().printAndUpdateGrid();
        Scanner userInput = new Scanner(System.in);
        String answer = userInput.nextLine();
        switch (answer){
            case "Habitation": buildBuilding(new Habitation(-1,-1,1),game);break;
            case "LumberMill": buildBuilding(new LumberMill(-1,-1),game);break;
            case "Quarry": buildBuilding(new Quarry(-1,-1),game);break;
            case "Forest": buildBuilding(new Forest(-1,-1),game);break;
            case "Cement Plant": buildBuilding(new CementPlant(-1,-1),game);break;
            case "Forge": buildBuilding(new Forge(-1,-1),game);break;
            default:
                System.out.println("pls enter a correct choice, returning to main menu");printMenuOptions(game);
        }
    }
}
