    public class CementPlant extends Building{
        public CementPlant(int pos_x, int pos_y) {
            super(pos_x, pos_y);
            size_x = 2;
            size_y = 2;
            name = "Cement Plant";
            buildingLogo = 'C';
            buildingCost = new ResourceList(0,0,50,20,0,10,0,0,0,10);
            productionList = new ResourceList(0,0,0,0,0,2,1,10,0,4);
            upKeepList = new ResourceList(0,0,0,20,0,0,0,0,2,0 );
        }
    }
