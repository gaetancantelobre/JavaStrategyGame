public class WheatFarm extends Building{
        public WheatFarm(int pos_x, int pos_y) {
            super(pos_x, pos_y);

            size_x = 1;
            size_y = 2;
            name = "Farm";
            maxWorkers = 3;

            buildingLogo = 'W';
            building_delay = 1;

            buildingCost = new ResourceList(0,0,0,10,0,6,0,0,0,0);
            productionList = new ResourceList(0,0,0,0,0,0,18,0,0,0);
            upKeepList = new ResourceList(0,0,0,0,0,0,0,0,0,0);
        }
    }

