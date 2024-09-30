package uts.advsoft;

import uts.advsoft.Database;

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
	public void add_amount(int amount){
		this.amount += amount;
	}
}
