package uts.advsoft;
import uts.advsoft.MenuItem;
import uts.advsoft.MenuItemEntry;

import java.util.ArrayList;

public class Cart{
	int id, owner_id;
	ArrayList<MenuItemEntry> item_list;
	double cart_price;
	
	public Cart(int id, int owner_id){
		this.id = id;
		this.owner_id = owner_id;
		this.cart_price = 0.0;
	}

	public int get_id(){
		return id;
	}
	public int get_owner_id(){
		return owner_id;
	}
	public MenuItemEntry[] get_cart_items(){
		return item_list.toArray(new MenuItemEntry[]{}); 
	}
	/*
	public void add_item(MenuItem item, int amount){
		for (MenuItemEntry i : item_list){
			if (i.get_item() == item){
				i.add_amount(amount);
				return;
			}
		}
		item_list.add(new MenuItemEntry(item, amount));
		cart_price += item.getPrice();
	}
	public void remove_item(int item_id){
		for (MenuItemEntry i : item_list){
			if (i.get_item().getID() == item_id){
				item_list.remove(i);
				return;
			}
		}
	}
	*/
}
