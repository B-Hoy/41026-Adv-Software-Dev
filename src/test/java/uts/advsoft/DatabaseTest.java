import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import uts.advsoft.Database;
import uts.advsoft.User;
import uts.advsoft.Order;

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
		assertEquals(orders[0].get_menu_items(), "Example Item 1:10, Example Item 2:20, Example Item 3:100");
		assertEquals(orders[0].get_delivery_method(), "Walk");
		assertTrue(orders[0].is_current_order());
		assertEquals(orders[0].get_status_level(), "Cooking");
		assertEquals(orders[0].get_order_price(), (float)99.98);
	}
}
