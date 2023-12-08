public  abstract class Resource {
    int amount;
    String type;


    //getters and setters
    public Resource(int amount) {
        this.amount = amount;
    }

    public void addRemoveValue(int value)
    {
        amount += value;
    }

    public void setValue(int value)
    {
        amount = value;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public int getValue()
    {
        return amount;
    }


}
