package uts.advsoft;
import uts.advsoft.MenuItem;
import uts.advsoft.MenuItemEntry;
import uts.advsoft.Database;
import java.util.ArrayList;

public class Cart{
	int id, owner_id;
	ArrayList<MenuItemEntry> item_list;
	double cart_price;
	
	public Cart(int id, int owner_id, String menu_items, double cart_price){
		this.id = id;
		this.owner_id = owner_id;
		this.cart_price = cart_price;
		this.item_list = new ArrayList<MenuItemEntry>();
		String[] items = menu_items.split("[,]");
		for (String i : items){
			String[] j = i.split("[:]");
			this.item_list.add(new MenuItemEntry(Integer.valueOf(j[0]), Integer.valueOf(j[1])));
		}
	}
	public Cart(int id, int owner_id){
		this.id = id;
		this.owner_id = owner_id;
		this.cart_price = 0.0;
	}
	public MenuItemEntry get_menu_item_entry(int item_id){
		MenuItemEntry[] items = this.get_cart_items();
		for (MenuItemEntry i : items){
			if (i.get_item().getID() == item_id){
				return i;
			}
		}
		return null;
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
	public String get_cart_item_string(){
		String ret = "";
		MenuItemEntry[] mie = this.get_cart_items();
		if (mie.length > 0){
			ret += mie[0].get_item().getID() + ":" + Integer.toString(mie[0].get_amount());
			for (int i = 1; i < mie.length; i++){
				ret += "," + mie[i].get_item().getID() + ":" + Integer.toString(mie[i].get_amount());
			}
		}
		return ret;
	}
	public double get_price(){
		return cart_price;
	}
	public void add_item(int item_id, int amount){
		MenuItemEntry curr;
		for (int i = 0; i < this.item_list.size(); i++){
			curr = this.item_list.get(i);
			if (curr.get_item().getID() == item_id){
				curr.add_amount(amount);
				this.item_list.set(i, curr);
				this.cart_price += (curr.get_item().getPrice() * amount);
				return;
			}
		}
		MenuItem newitem = Database.get_menu_item(item_id);
		this.item_list.add(new MenuItemEntry(item_id, amount));
		this.cart_price += (newitem.getPrice() * amount);
	}
	public void remove_item(int item_id){
		MenuItemEntry curr;
		for (int i = 0; i < this.item_list.size(); i++){
			curr = this.item_list.get(i);
			if (curr.get_item().getID() == item_id){
				this.item_list.remove(i);
				this.cart_price -= (curr.get_item().getPrice() * curr.get_amount());
				return;
			}
		}	
	}
	public void remove_item(int item_id, int amount){
		MenuItemEntry curr;
		for (int i = 0; i < this.item_list.size(); i++){
			curr = this.item_list.get(i);
			if (curr.get_item().getID() == item_id){
				if (curr.get_amount() > amount){
					curr.add_amount(0 - amount);
					this.item_list.set(i, curr);
					this.cart_price -= (curr.get_item().getPrice() * amount);
				}else{
					this.item_list.remove(i);
					this.cart_price -= (curr.get_item().getPrice() * curr.get_amount());
				}
				return;
			}
		}
	}
}
