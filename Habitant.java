public class Habitant {
    Building workPlace = null;

    boolean fed = false;

    public boolean isFed() {
        return fed;
    }

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

    public Building getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(Building workPlace) {
        this.workPlace = workPlace;
    }
}
