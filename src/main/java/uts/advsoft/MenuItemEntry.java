package uts.advsoft;

import uts.advsoft.Database;
import java.text.DecimalFormat;

public class MenuItemEntry{
	MenuItem item;
	int amount;
	public MenuItemEntry(int id, int amount){
		this.item = Database.get_menu_item(id);
		this.amount = amount;
	}
	public MenuItem get_item(){
		return item;
	}
	public int get_amount(){
		return amount;
	}
	public void add_amount(int amount){
		this.amount += amount;
	}
	public String get_value(){
		if (amount > 0){
			DecimalFormat df = new DecimalFormat("#.00");
			return "$" + df.format(item.getPrice() * amount);
		}else{
			return "$0.00";
		}
	}
}
