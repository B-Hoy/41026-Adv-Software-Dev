package uts.advsoft;

public class Pizza {
    private int pizzaID;
    private String name;
    private String img;

    public Pizza(int pizzaID, String name, String img) {
        this.pizzaID = pizzaID;
        this.name = name;
        this.img = img;
    }

    public int getPizzaID() {
        return pizzaID;
    }

    public void setPizzaID(int pizzaID) {
        this.pizzaID = pizzaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
}
