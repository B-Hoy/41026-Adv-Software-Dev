package uts.advsoft;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Database{
	Connection db_con;
	public Database(){
		try{
			Class.forName("org.sqlite.JDBC");
			File db_file = File.createTempFile("temp", ".sqlite");
			db_file.deleteOnExit();
			db_con = DriverManager.getConnection("jdbc:sqlite:" + db_file.getAbsolutePath());
			Statement s = db_con.createStatement();

			s.executeUpdate("CREATE TABLE Users(id INTEGER NOT NULL PRIMARY KEY, email TEXT NOT NULL, first_name TEXT NOT NULL, last_name TEXT NOT NULL, password TEXT NOT NULL, phone_number TEXT NOT NULL, register_date TEXT NOT NULL, card_num TEXT NOT NULL, card_expiry_date TEXT NOT NULL, card_cvc INTEGER NOT NULL, address_street_num TEXT NOT NULL, address_street TEXT NOT NULL, address_city TEXT NOT NULL, address_postcode INTEGER NOT NULL)");
			s.executeUpdate("INSERT INTO Users VALUES(1, 'testing@test.com', 'test', 'user', 'testpasswd', '+61400000000', DATETIME('now', '+10 hours'), 1234567890987654, '09/24', 123, 'PO Box 1', 'Test Street', 'Sydney', 2000)");

			s.executeUpdate("CREATE TABLE Orders(id INTEGER NOT NULL PRIMARY KEY, owner_id INTEGER NOT NULL, menu_items TEXT NOT NULL, delivery_method TEXT NOT NULL, order_date TEXT NOT NULL, current_order BOOLEAN NOT NULL, status_level TEXT NOT NULL, order_price REAL NOT NULL, FOREIGN KEY(owner_id) REFERENCES Users(id))");
			s.executeUpdate("INSERT INTO ORDERS VALUES(1, 1, 'Example Item 1:10, Example Item 2:20, Example Item 3:100', 'Walk', DATETIME('now', '+10 hours'), 1, 'Cooking', 99.98)");

			s.executeUpdate("CREATE TABLE MenuItems(id INTEGER NOT NULL PRIMARY KEY, name TEXT NOT NULL, price REAL NOT NULL, image TEXT NOT NULL, description TEXT NOT NULL)");
			s.executeUpdate("INSERT INTO MenuItems VALUES" + //
							"(1, 'Margherita', 12.99, 'margherita.jpg', 'A classic Margherita with fresh tomatoes, creamy mozzarella, and fragrant basil leaves, all on a crispy crust.')," + //
							"(2, 'Pepperoni', 14.99, 'pepperoni.jpg', 'Topped with generous slices of spicy pepperoni and melted mozzarella cheese for the perfect balance of flavor.')," + //
							"(3, 'Meat Lovers', 16.99, 'meatlovers.jpg', 'Loaded with ham, sausage, bacon, and pepperoni, this pizza is a hearty choice for meat enthusiasts.')," + //
							"(4, 'Hawaiian', 14.99, 'hawaiian.jpg', 'A delightful mix of savory ham and sweet pineapple, delivering a delicious contrast of flavors.')," + //
							"(5, 'BBQ Chicken', 15.99, 'bbqchicken.jpg', 'Grilled chicken with tangy BBQ sauce, onions, and gooey mozzarella for a smoky, satisfying bite.')," + //
							"(6, 'Vegetarian', 13.99, 'vegetarian.png', 'An assortment of fresh mushrooms, bell peppers, onions, and black olives for a veggie-packed meal.')," + //
							"(7, 'Supreme', 17.49, 'supreme.jpg', 'Loaded with pepperoni, sausage, bell peppers, onions, and olives; a truly supreme pizza experience.')," + //
							"(8, 'Buffalo Chicken', 16.49, 'buffalochicken.jpg', 'Spicy Buffalo sauce paired with grilled chicken and a touch of ranch dressing for a flavorful kick.')," + //
							"(9, 'Four Cheese', 15.49, 'fourcheese.jpg', 'A creamy blend of mozzarella, cheddar, parmesan, and gouda for an extra cheesy delight.')," + //
							"(10, 'Pesto Chicken', 16.49, 'pestochicken.jpg', 'Pesto sauce with tender grilled chicken, spinach, and mozzarella for a fresh, herbaceous taste.')," + //
							"(11, 'Ham & Cheese', 15.99, 'hamcheese.jpg', 'Simple yet delicious, this pizza combines savory ham with gooey mozzarella on a classic crust.')," + //
							"(12, 'Tandoori Chicken', 16.49, 'tandoorichicken.jpg', 'Tandoori-spiced chicken, red onions, and fresh cilantro for a flavorful fusion option.')," + //
							"(13, 'Capricciosa', 15.49, 'capricciosa.jpg', 'An Italian favorite with ham, mushrooms, artichokes, and olives on a crispy base.')," + //
							"(14, 'Seafood', 17.99, 'seafood.jpg', 'Loaded with succulent shrimp, calamari, and a blend of fresh seafood toppings for ocean lovers.')," + //
							"(15, 'Spinach & Ricotta', 14.99, 'spinachricotta.jpg', 'Creamy ricotta cheese, fresh spinach, garlic, and mozzarella on a light and crispy crust.')," + //
							"(16, 'Sausage & Mushroom', 15.99, 'sausagemushroom.jpg', 'Flavorful sausage and mushrooms paired with mozzarella on a savory garlic crust.')," + //
							"(17, 'Garlic Bread', 5.99, 'garlicbread.jpg', 'Crispy garlic bread topped with a buttery garlic spread; a classic side for any pizza.')," + //
							"(18, 'Cheesy Garlic', 8.99, 'cheesygarlic.jpg', 'Garlic bread with melted mozzarella cheese, perfect for garlic and cheese lovers alike.')," + //
							"(19, 'Calzone', 10.99, 'calzone.jpg', 'A folded pizza stuffed with ham, cheese, and marinara sauce, baked to perfection.')," + //
							"(20, 'Pizza Fries', 7.99, 'pizzafries.jpg', 'Crispy French fries topped with marinara sauce and melted mozzarella for a tasty twist.')");

			s.close();
		} catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public User[] get_all_users(){
		ArrayList<User> a = new ArrayList<User>();
		try{
			Statement s = db_con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Users");
			while (rs.next()){
				a.add(new User(rs.getInt("id"), rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("password"), rs.getString("phone_number"), rs.getString("register_date"), rs.getString("card_num"), rs.getString("card_expiry_date"), rs.getInt("card_cvc"), rs.getString("address_street_num"), rs.getString("address_street"), rs.getString("address_city"), rs.getInt("address_postcode")));
			}
			s.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return a.toArray(new User[]{});
	}

	public Order[] get_all_orders(){
		ArrayList<Order> a = new ArrayList<Order>();
		try{
			Statement s = db_con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Orders");
			while (rs.next()){
				a.add(new Order(rs.getInt("id"), rs.getInt("owner_id"), rs.getString("menu_items"), rs.getString("delivery_method"), rs.getString("order_date"), rs.getBoolean("current_order"), rs.getString("status_level"), rs.getFloat("order_price")));
			}
			s.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return a.toArray(new Order[]{});
	}

	public boolean is_admin_password(String s){
		return s.equals("admin");
	}

	public MenuItem[] getMenuItems(){	
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		try{
			Statement s = db_con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM MenuItems");
			while (rs.next()){
				menu.add(new MenuItem(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getString("image"), rs.getString("description")));
			}
			s.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return menu.toArray(new MenuItem[]{});
	}
}
