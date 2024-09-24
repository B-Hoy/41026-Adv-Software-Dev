package uts.advsoft;

public class MenuItem {
    private int id;
    private String name;
    private double price;
    private String img;

    public MenuItem(int id, String name, double price, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
