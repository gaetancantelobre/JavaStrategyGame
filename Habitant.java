public class Habitant {
    Building workPlace = null;

    boolean fed = false;

    public boolean isFed() {
        return fed;
    }

    public void setWorkPlace(Building workPlace) {
        this.workPlace = workPlace;
    }

    public Building getWorkPlace() {
        return workPlace;
    }
    // tries to feed the habitant, if possible it is now fed

    public void feedHabitant(ResourceList rl)
    {
        ResourceList cost = new ResourceList(0,0,0,0,0,0,1,0,0,0);
        if(rl.canAffordRL(cost)){
            rl.diffResourceList(cost);
            fed = true;
        }
        else
            fed = false;
    }




}
