public class main {

    public static void main(String[] args)
    {
        Game game = new Game(10,10);
        game.getGrid().placeBuildingOnGrid(0,0,new Habitation(0,0,1));
        game.getGrid().printAndUpdateGrid();
        System.out.println("------------------------------");
        game.getGrid().placeBuildingOnGrid(2,2,new Habitation(0,0,1));
        game.getGrid().printAndUpdateGrid();

    }
}
