package uts.advsoft;
import uts.advsoft.MenuItem;
import uts.advsoft.MenuItemEntry;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Order {
    int id, owner_id, driver_id, address_postcode;
    String delivery_method, order_date, status_level, address_street_num, address_street, address_city;
    boolean current_order;
    float order_price, delivery_fee;
    ArrayList<MenuItemEntry> menu_items;

    // Constructor including delivery fee parameter
    public Order(int id, int owner_id, int driver_id, String menu_items, String delivery_method, String order_date, boolean current_order, String status_level, float order_price, float delivery_fee, String address_street_num, String address_street, String address_city, int address_postcode) {
        this.id = id;
        this.owner_id = owner_id;
        this.driver_id = driver_id;
        this.delivery_method = delivery_method;
        this.order_date = order_date;
        this.current_order = current_order;
        this.status_level = status_level;
        this.order_price = order_price;
        this.delivery_fee = delivery_fee;
        this.menu_items = new ArrayList<MenuItemEntry>();
		this.address_street_num = address_street_num;
		this.address_street = address_street;
		this.address_city = address_city;
		this.address_postcode = address_postcode;
        String[] items = menu_items.split("[,]");
        for (String i : items) {
            String[] j = i.split("[:]");
            this.menu_items.add(new MenuItemEntry(Integer.valueOf(j[0]), Integer.valueOf(j[1])));
        }
    }

    // Getter methods
    public int get_id() {
        return id;
    }

    public int get_owner_id() {
        return owner_id;
    }

    public MenuItemEntry[] get_menu_items() {
        return menu_items.toArray(new MenuItemEntry[]{});
    }

    public String get_delivery_method() {
        return delivery_method;
    }

    public String get_order_date() {
        return order_date;
    }

    public boolean is_current_order() {
        return current_order;
    }

    public String get_status_level() {
        return status_level;
    }

    public float get_order_price() {
        return order_price;
    }

    public String get_order_price_formatted() {
        if (order_price > 0.0) {
            DecimalFormat df = new DecimalFormat("#.00");
            return "$" + df.format(order_price);
        } else {
            return "$0.00";
        }
    }

    public void setDeliveryFee(String deliveryMethod) {
        if ("delivery".equals(deliveryMethod)) {
            this.delivery_fee = 5.99f;
        } else {
            this.delivery_fee = 0.00f;
        }
    }

    public float getDeliveryFee() {
        return delivery_fee;
    }

    public float getTotalPrice() {
        return this.order_price + this.delivery_fee;
    }

    public String getTotalPriceFormatted() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "$" + df.format(getTotalPrice());
    }

    public void setTotalPriceWithDelivery(float deliveryFee) {
        this.order_price += deliveryFee;
    }

    // Setter methods
    public void set_delivery_method(String delivery_method) {
        this.delivery_method = delivery_method;
    }

    public int get_driver_id() {
        return driver_id;
    }

    public void set_driver_id(int driver_id) {
        this.driver_id = driver_id;
    }
}
