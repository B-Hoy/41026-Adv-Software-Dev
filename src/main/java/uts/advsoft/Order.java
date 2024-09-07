package uts.advsoft;

public class Order{
	int id, owner_id;
	String menu_items, delivery_method, order_date, status_level;
	boolean current_order;
	float order_price;
	public Order(int id, int owner_id, String menu_items, String delivery_method, String order_date, boolean current_order, String status_level, float order_price){
		this.id = id;
		this.owner_id = owner_id;
		this.menu_items = menu_items;
		this.delivery_method = delivery_method;
		this.order_date = order_date;
		this.current_order = current_order;
		this.status_level = status_level;
		this.order_price = order_price;
	}
	public int get_id(){
		return id;
	}
	public int get_owner_id(){
		return owner_id;
	}
	public String get_menu_items(){
		return menu_items;
	}
	public String get_delivery_method(){
		return delivery_method;
	}
	public String get_order_date(){
		return order_date;
	}
	public boolean is_current_order(){
		return current_order;
	}
	public String get_status_level(){
		return status_level;
	}
	public float get_order_price(){
		return order_price;
	}
}
