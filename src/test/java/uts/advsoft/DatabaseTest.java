package uts.advsoft;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import uts.advsoft.Database;
import uts.advsoft.User;
import uts.advsoft.Order;
import uts.advsoft.MenuItemEntry;
import uts.advsoft.MenuItem;

class DatabaseTest{

	private final Database db = new Database();

	@Test
	void test_is_admin_password(){
		assertTrue(db.is_admin_password("admin"), "Admin password is \"admin\"");
	}
	@Test
	void test_get_all_users(){
		User[] users = db.get_all_users();
		assertTrue(users.length > 0);
		assertEquals(users[0].get_id(), 1);
		assertEquals(users[0].get_email(), "testing@test.com");
		assertEquals(users[0].get_first_name(), "test");
		assertEquals(users[0].get_last_name(), "user");
		assertEquals(users[0].get_password(), "testpasswd");
		assertEquals(users[0].get_phone_num(), "+61400000000");
		assertEquals(users[0].get_card_num(), "1234567890987654");
		assertEquals(users[0].get_card_expiry_date(), "09/24");
		assertEquals(users[0].get_card_cvc(), 123);
		assertEquals(users[0].get_address_street_num(), "PO Box 1");
		assertEquals(users[0].get_address_street(), "Test Street");
		assertEquals(users[0].get_address_city(), "Sydney");
		assertEquals(users[0].get_address_postcode(), 2000);
	}
	@Test
	void test_get_all_orders(){
		Order[] orders = db.get_all_orders();
		assertTrue(orders.length > 0);
		assertEquals(orders[0].get_id(), 1);
		assertEquals(orders[0].get_owner_id(), 1);
		//assertEquals(orders[0].get_menu_items(), );
		MenuItemEntry[] mie = orders[0].get_menu_items();
		assertTrue(mie.length == 3);
		assertEquals(orders[0].get_delivery_method(), "Walk");
		assertTrue(orders[0].is_current_order());
		assertEquals(orders[0].get_status_level(), "Cooking");
		assertEquals(orders[0].get_order_price(), (float)93.94);
	}
	@Test
	void test_get_menu_items(){
		MenuItem[] mi = db.getMenuItems();
		assertEquals(mi.length, 20);
		assertEquals(mi[0].getName(), "Margherita");
		assertEquals(mi[1].getName(), "Pepperoni");
		assertEquals(mi[2].getName(), "Meat Lovers");
		assertEquals(mi[3].getName(), "Hawaiian");
		assertEquals(mi[4].getName(), "BBQ Chicken");
		assertEquals(mi[5].getName(), "Vegetarian");
		assertEquals(mi[6].getName(), "Supreme");
		assertEquals(mi[7].getName(), "Buffalo Chicken");
		assertEquals(mi[8].getName(), "Four Cheese");
		assertEquals(mi[9].getName(), "Pesto Chicken");
		assertEquals(mi[10].getName(), "Ham & Cheese");
		assertEquals(mi[11].getName(), "Tandoori Chicken");
		assertEquals(mi[12].getName(), "Capricciosa");
		assertEquals(mi[13].getName(), "Seafood");
		assertEquals(mi[14].getName(), "Spinach & Ricotta");
		assertEquals(mi[15].getName(), "Sausage & Mushroom");
		assertEquals(mi[16].getName(), "Garlic Bread");
		assertEquals(mi[17].getName(), "Cheesy Garlic");
		assertEquals(mi[18].getName(), "Calzone");
		assertEquals(mi[19].getName(), "Pizza Fries");
	}
	@Test
	void test_get_menu_item_int(){
		MenuItem m = db.get_menu_item(5);
		assertEquals(m.getName(), "BBQ Chicken");
	}
	@Test
	void test_get_menu_item_string(){
		MenuItem m = db.get_menu_item("Sausage & Mushroom");
		assertEquals(m.getID(), 16);
	}
	@Test
	void test_get_all_employees(){
		Employee[] emps = db.get_all_employees();
		assertEquals(emps.length, 1);
		assertEquals(emps[0].get_id(), 1);
		assertEquals(emps[0].get_role(), "Driver");
	}
	@Test
	void test_get_employee(){
		Employee emp = db.get_employee(1);
		assertEquals(emp.get_id(), 1);
		assertEquals(emp.get_role(), "Driver");
	}
	@Test
	void test_update_order(){
		Order o = db.get_order(1);
		db.page_update_order(1, "TestMethod", 1, "Cooking");
		Order o2 = db.get_order(1);
		assertTrue(o.get_delivery_method() != o2.get_delivery_method());
	}
	@Test
	void test_get_all_carts(){
		Cart[] cs = db.get_all_carts();
		assertEquals(cs.length, 1);
		assertEquals(cs[0].get_price(), 93.94);
	}
	@Test
	void test_get_cart(){
		Cart c = db.get_cart(1, "id");
		assertEquals(c.get_price(), 93.94);
	}
	@Test
	void test_delete_cart(){
		db.delete_cart(1);
		Cart[] cs = db.get_all_carts();
		assertEquals(cs.length, 0);
	}
	@Test
	void test_generate_table_id(){
		assertEquals(db.generate_id("Carts"), 2);
	}
	@Test
	void test_make_order(){
		db.make_order(1);
		Order[] os = db.get_all_orders();
		Cart[] cs = db.get_all_carts();
		assertEquals(os.length, 1);
		assertEquals(cs.length, 1);
		db.page_update_order(1, "Walk", 0, "Finished");
		db.make_order(1);
		os = db.get_all_orders();
		cs = db.get_all_carts();
		assertEquals(os.length, 2);
		assertEquals(cs.length, 0);
	}
	@Test
	void test_add_to_cart(){
		Cart c = db.get_cart(1, "id");
		db.add_to_cart(1, 8, 130);
		Cart c2 = db.get_cart(1, "id");
		db.add_to_cart(1, 3, 130);
		Cart c3 = db.get_cart(1, "id");
		assertEquals(c.get_cart_items().length, 3);
		assertEquals(c2.get_cart_items().length, 4);
		assertEquals(c3.get_cart_items().length, 4);
	}
	@Test
	void test_remove_from_cart(){
		Cart c = db.get_cart(1, "id");
		assertEquals(c.get_menu_item_entry(3).get_amount(), 3);
		db.remove_from_cart(1, 3, 1);
		Cart c2 = db.get_cart(1, "id");
		assertEquals(c2.get_menu_item_entry(3).get_amount(), 2);
		db.remove_from_cart(1, 2, 2);
		Cart c3 = db.get_cart(1, "id");
		assertEquals(c3.get_menu_item_entry(2), null);
		db.remove_from_cart(1, 1);
		Cart c4 = db.get_cart(1, "id");
		assertEquals(c4.get_menu_item_entry(1), null);
	}
	@Test
	void test_create_user(){
		db.make_user("a@a.com", "f", "l", "p", "0000000000", "0000000000000000", "00/00", "000", "0", "s", "c", 0);
		assertEquals(db.get_all_users().length, 2);
	}
	@Test
	void test_get_user(){
		User u = db.get_user("testing@test.com", "testpasswd");
		assertTrue(u != null);
	}

}
