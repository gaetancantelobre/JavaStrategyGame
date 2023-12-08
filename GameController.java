import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class GameController {
    public GameController() {
    }


    //gameplay loop plays turns until the game is finished when the goal is reached
    void playGame() throws IOException {
        printIntroAndRules();
        Game game = setupGame();
        while(!game.checkEndGame())
        {
            System.out.println("Day : " + game.getDayCounter());
            printMenuOptions(game);
            game.printSUKP();
        }
        System.out.println("Congrats you have finished the challenges in : "+ game.getDayCounter() + "days");
    }


    //prints of the rules and the intro from the intro file
     private void printIntroAndRules() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("intro.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    //initial setup of the game
    private Game setupGame()
    {
        Game game = new Game(10,10);
        game.getGrid().printAndUpdateGrid();
        return game;
    }

    //prints out the menu and the selection options
    private void printMenuOptions(Game game)
    {
        System.out.println("1. Build a building.");
        System.out.println("2. Manage Population.");
        System.out.println("3. Show Current Stats and All buildings?");
        System.out.println("4. Go to next day");
        System.out.println("5. Save the game");
        System.out.println("6. load a save");
        System.out.println("7. remove building from grid (you will lose the resources)");


        Scanner userInput = new Scanner(System.in);
        String answer = userInput.nextLine();
        switch (answer){
            case "build":
            case "Build":
            case "1": printBuildingOptions(game);break;
            case "manage":
            case "pop":
            case "2": managePopulation(game);break;
            case "stats":
            case "3": showTheStatsOfTheBuildings(game);break;
            case "next":
            case "4": collectPayGoNextDay(game);break;
            case "save":
            case "5": saveGame(game);break;
            case "load":
            case "6":
                Game newGame = loadGameSave();
                if(newGame != null)
                {
                    game.overwriteGame(newGame);
                    System.out.println("Game loaded.");
                    collectPayGoNextDay(game);
                    printMenuOptions(game);
                }
                else
                    System.out.println("file could not be loaded.");
                break;
            case "7":
            case "destroy":
            case "remove":
            case "rm":
                removeSelectedBuilding(game);
                break;

            default:
                System.out.println("Enter a correct selection number from above");break;
        }
    }


    //function that calls remove building in the grid while opeing a UI to choose which building
    private  void removeSelectedBuilding(Game game) {
        game.getGrid().removeBuildingFromGrid(chooseBuilding(game,true,false));
    }


    //saves the current game state to the chosen save file.
    private  void saveGame(Game game)
    {
        //gets desired file name
        System.out.println("Enter the save name pls. (just a name , dont add a file extension)");
        Scanner userInput = new Scanner(System.in);
        String answer = userInput.nextLine();
        File file = new File("saves/"+answer+".txt");
        if (file.exists()) { //if the file exist to overwrite it safely we delete it then recreate it
            if(file.delete())
            {
                System.out.println("deleted file");
            }
        }
        try {
            FileWriter myWriter = new FileWriter("saves/"+answer + ".txt",false);

            // save the dimension of the grid
            myWriter.write(String.valueOf(game.grid.getGrid_x())+"\n");
            myWriter.write(String.valueOf(game.grid.getGrid_y())+"\n");
            myWriter.write("startb\n");


            ArrayList<Building> buildingArrayList =  game.getGrid().getBuildings();
            ArrayList<Building> newbuildingArrayList = new ArrayList<Building>(game.getGrid().getBuildings());
            // to save building we just store the save print method output to the file
            for(Building b : buildingArrayList) //copy only the habitation so that we can assign the workers easily
            {
                if(b.getClass() == Habitation.class)
                {
                    myWriter.write(b.saveBuildingString() + "\n");
                    newbuildingArrayList.remove(b); //remove the houses
                }
            }
            for(Building b : newbuildingArrayList) //save the other buildings
            {
                myWriter.write(b.saveBuildingString() + "\n");
            }
            myWriter.write("endb\n");
            //end of saving buildings
            myWriter.write("starts\n");
            //saving every resource in the stash
            for (HashMap.Entry<String, Resource> entry : game.getStash().getResourceList().getProdList().entrySet()) {
                String key = entry.getKey(); //get the type of resource
                int value = entry.getValue().getValue(); //get the number of the value of current resource
                System.out.println(key + value);
                myWriter.write(String.valueOf(value) + "\n");
            }
            myWriter.write("ends\n");

            //save the day counter
            myWriter.write(String.valueOf(game.getDayCounter()));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch ( IOException e) {
            System.out.println("Couldnt Save the file.");
        }
    }


    private  Game loadGameSave()
    {
        System.out.println("Pls enter the name of the file , as it was saved , without the extension : \"save\"");
        Scanner userInput = new Scanner(System.in);
        String filename = userInput.nextLine();
        try {
            //try to open the save file , if good continue else throws exception
            File save = new File("saves/"+filename+".txt");
            Scanner myReader = new Scanner(save);
            //looks if the save is empty
            if(save.length() == 0)
            {
                System.out.println("Save is empty.");
                return null;
            }
            //creates a new game while reading the dimesion of the grid
            Game game = new Game(Integer.parseInt(myReader.nextLine()),Integer.parseInt(myReader.nextLine()));

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //makes sure that it is in the right spot
                if(Objects.equals(data, "startb"))
                {
                    while(!Objects.equals(data, "endb")) // reads until no more buildings
                    {
                        Building f = null;
                        data = myReader.nextLine();

                        // we create a Building objects that we later cast to specific building type
                        //we determine the building type by the buiding logo in the save file
                        switch (data)
                        {
                            case "H":
                                Habitation h = new Habitation(Integer.parseInt(myReader.nextLine()),Integer.parseInt(myReader.nextLine()));
                                game.getGrid().placeBuildingOnGrid(h);
                                myReader.nextLine();
                                break;
                            case "F" :
                                f = new Forest(Integer.parseInt(myReader.nextLine()),Integer.parseInt(myReader.nextLine()));
                                break;
                            case "C":
                                f = new CementPlant(Integer.parseInt(myReader.nextLine()),Integer.parseInt(myReader.nextLine()));
                                break;
                            case "L":
                                f = new LumberMill(Integer.parseInt(myReader.nextLine()),Integer.parseInt(myReader.nextLine()));
                                break;
                            case "S":
                                f = new SteelMill(Integer.parseInt(myReader.nextLine()),Integer.parseInt(myReader.nextLine()));
                                break;
                            case "T":
                                f = new ToolFactory(Integer.parseInt(myReader.nextLine()),Integer.parseInt(myReader.nextLine()));
                                break;
                            case "Q":
                                f = new Quarry(Integer.parseInt(myReader.nextLine()),Integer.parseInt(myReader.nextLine()));
                                break;
                            case "W":
                                f = new WheatFarm(Integer.parseInt(myReader.nextLine()),Integer.parseInt(myReader.nextLine()));
                                break;
                        }
                        // saveProductionBuilding is where all the magic happens
                        if(f != null)
                            saveProductionBuilding(game,myReader,f);

                    }
                    data = myReader.nextLine();
                    if(Objects.equals(data, "starts")) // copying stash resources into the new stash
                    {
                        game.getStash().setResource("gold",Integer.parseInt(myReader.nextLine()));
                        game.getStash().setResource("steel",Integer.parseInt(myReader.nextLine()));
                        game.getStash().setResource("cement",Integer.parseInt(myReader.nextLine()));
                        game.getStash().setResource("lumber",Integer.parseInt(myReader.nextLine()));
                        game.getStash().setResource("coal",Integer.parseInt(myReader.nextLine()));
                        game.getStash().setResource("iron",Integer.parseInt(myReader.nextLine()));
                        game.getStash().setResource("wood",Integer.parseInt(myReader.nextLine()));
                        game.getStash().setResource("tools",Integer.parseInt(myReader.nextLine()));
                        game.getStash().setResource("food",Integer.parseInt(myReader.nextLine()));
                        game.getStash().setResource("stone",Integer.parseInt(myReader.nextLine()));

                    }
                    myReader.nextLine();
                    data = myReader.nextLine();
                    //getting the day counter
                    game.dayCounter = Integer.parseInt(data);
                }

            }
            myReader.close();
            return game;
        } catch (FileNotFoundException e) {
            System.out.println("File not find pls enter the full name of the file");
            return null;
        }
    }


    //function that loads all the building from a save file
    private void saveProductionBuilding(Game game, Scanner myReader,Building f)
    {
        //place the building on the grid
        game.getGrid().placeBuildingOnGrid(f);
        //read the number of workers
        int wf = Integer.parseInt(myReader.nextLine());
        //gets the list of unemployed and fills to correct amount
        for(Habitant hab : game.getUnemployed())
        {
            if(f.getWorkerList().size() < wf)
                game.getGrid().putWorkerInBuilding(hab,f);
            else
                break;
        }
        //read the building delay and if the building has been built
        f.building_delay = Integer.parseInt(myReader.nextLine());
        f.built = Objects.equals(myReader.nextLine(), "true");
        //if the building is built replace the ! with the correct logos
        if(f.built){
            game.getGrid().replaceConstructionWithLogo(f);
        }
    }

    //list of building that are producing , its for the stats menu
    private ArrayList<Building> producingBList = new ArrayList<>();

    //method that advances to next day while collecting and paying what is due
    private  void collectPayGoNextDay(Game game) {
        producingBList = game.getGrid().harvestProductionOnGrid(game.getStash().getResourceList());
        game.getGrid().updateBuildingDelay();
        game.increaseDayCounter();
    }


    //shows the stats of all of the buildings on the grid
    private void showTheStatsOfTheBuildings(Game game) {
        chooseBuilding(game,false,false);
        game.printSUKP();
        printMenuOptions(game);
    }


    //function that allows to move workers in and out of building
    private  void managePopulation(Game game){
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
                    game.getGrid().removeWorkerFromBuilding(chooseBuilding(game,true,false));break;
                case "4":
                    game.getGrid().removeAllWorkersFromBuilding(chooseBuilding(game,true,false));break;
            }
        }else
        {
            System.out.println("Your city has a population of 0, pls consider building habitations as they are your source of workers");
        }

    }


    // multi use building choice UI, can be used as a choice UI but also as a stats UI depending on its parameters
    private Building chooseBuilding(Game game, boolean choose,boolean filling) {
        int cpt = 0;
        if(!game.getGrid().getBuildings().isEmpty())
        {
            for(Building b : game.getGrid().getBuildings())
            {
                String strB = (cpt + " : " + b).replace("class"," ");
                if(filling && !b.checkForMaxWorkers() && b.isBuilt()) //for population management
                    System.out.println(strB);
                else if(!filling) { // for stats
                    System.out.println(strB);
                    if(!choose)
                    {
                        if (producingBList.remove(b)) {
                            System.out.println("Producing");
                            producingBList.add(b);
                        } else
                            System.out.println("Not producing");
                    }

                }


                cpt++;
            }
            if(choose) //if choosing a building ask for a building nbr
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

    //Do all function that allows to create building while managing conflits ,resources and space.
    private  void buildBuilding(Building wantedB,Game game)
    {
        System.out.println("Enter wanted coordinates of the building by entering the x coordinate pressing enter then entering the y coordinate.");
        Scanner userInput = new Scanner(System.in);
        String answer;
        //sets place holder for the pos
        // asks for coordinates
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
            //checks if the coordinates are available
            if(!game.getGrid().checkCoordsAvailable(x,y,wantedB))
            {
                System.out.println("The coordinates are not available or will overlap on other building or out of bounds");
            }
            if(x == -1 || y == -1)
                System.out.println("Canceled the building of the structure");
            else
            {
                //sets the building position
                wantedB.setPos_x(x);
                wantedB.setPos_y(y);
                //checks if the resources are available
                if(game.getStash().canAfford(wantedB.getBuildingCost()))
                {
                    //buys it
                    game.getStash().getResourceList().diffResourceList(wantedB.getBuildingCost());
                    //places it, its in a if statement but it should not fail with the other tests
                    if(game.getGrid().placeBuildingOnGrid(wantedB))
                    {
                        System.out.println("Building : " + wantedB.getName() + " built correctly");
                        game.increaseDayCounter();
                    }
                    else
                    {
                        //just in case, never happens . failsafe
                        System.out.println("Building : " + wantedB.getName() + " building failed");
                        game.getStash().getResourceList().combineResourceList(wantedB.getBuildingCost());
                    }
                }
                else {
                    System.out.println("You can not afford this building !");
                    System.out.println("Building cost :" + wantedB.getBuildingCost().toStringSimple());;
                }
            }
        }
    }


    //menu that shows you all the possible building that you can build
    private void printBuildingOptions(Game game)
    {
        ArrayList<Building> bList = new ArrayList<Building>();
        bList.add(new Habitation(-1,-1));
        bList.add(new LumberMill(-1,-1));
        bList.add(new Quarry(-1,-1));
        bList.add(new Forest(-1, -1));
        bList.add(new CementPlant(-1,-1));
        bList.add(new SteelMill(-1,-1));
        bList.add(new ToolFactory(-1,-1));
        bList.add(new WheatFarm(-1,-1));

        for(Building b : bList)
        {
            System.out.println(b.getName() + ": \n" + b.buyingOptions());
        }
        game.getGrid().printAndUpdateGrid();
        Scanner userInput = new Scanner(System.in);
        String answer = userInput.nextLine();
        switch (answer){
            case "H":
            case "Habitation": buildBuilding(new Habitation(-1,-1),game);break;
            case "L":

            case "LumberMill": buildBuilding(new LumberMill(-1,-1),game);break;
            case "Q":

            case "Quarry": buildBuilding(new Quarry(-1,-1),game);break;
            case "F":

            case "Forest": buildBuilding(new Forest(-1,-1),game);break;
            case "C":

            case "Cement Plant": buildBuilding(new CementPlant(-1,-1),game);break;
            case "S":

            case "SteelMill": buildBuilding(new SteelMill(-1,-1),game);break;
            case "T":

            case "ToolFactory": buildBuilding(new ToolFactory(-1,-1),game);break;
            case "W":
            case "Food":

            case "WheatFarm": buildBuilding(new WheatFarm(-1,-1),game);break;
            default:
                System.out.println("pls enter a correct choice, returning to main menu");printMenuOptions(game);
        }
    }
}
