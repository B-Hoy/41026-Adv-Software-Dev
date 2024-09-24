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

			s.executeUpdate("CREATE TABLE MenuItems(id INTEGER NOT NULL PRIMARY KEY, name TEXT NOT NULL, price REAL NOT NULL, image TEXT NOT NULL)");
			s.executeUpdate("INSERT INTO MENUITEMS VALUES" + //
								"(1, 'Margherita', 12.99, 'margherita.jpg')," + //
								"(2, 'Pepperoni', 14.99, 'pepperoni.jpg')," + //
								"(3, 'Meat Lovers', 16.99, 'meatlovers.jpg')," + //
								"(4, 'Hawaiian', 14.99, 'hawaiian.jpg')," + //
								"(5, 'BBQ Chicken', 15.99, 'bbqchicken.jpg')," + //
								"(6, 'Vegetarian', 13.99, 'vegetarian.png')," + //
								"(7, 'Supreme', 17.49, 'supreme.jpg')," + //
								"(8, 'Buffalo Chicken', 16.49, 'buffalochicken.jpg')," + //
								"(9, 'Four Cheese', 15.49, 'fourcheese.jpg')," + //
								"(10, 'Pesto Chicken', 16.49, 'pestochicken.jpg')," + //
								"(11, 'Ham & Cheese', 15.99, 'hamcheese.jpg')," + //
								"(12, 'Tandoori Chicken', 16.49, 'tandoorichicken.jpg')," + //
								"(13, 'Capricciosa', 15.49, 'capricciosa.jpg')," + //
								"(14, 'Seafood', 17.99, 'seafood.jpg')," + //
								"(15, 'Spinach & Ricotta', 14.99, 'spinachricotta.jpg')," + //
								"(16, 'Sausage & Mushroom', 15.99, 'sausagemushroom.jpg')," + //
								"(17, 'Garlic Bread', 5.99, 'garlicbread.jpg')," + //
								"(18, 'Cheesy Garlic', 8.99, 'cheesygarlic.jpg')," + //
								"(19, 'Calzone', 10.99, 'calzone.jpg')," + //
								"(20, 'Pizza Fries', 7.99, 'pizzafries.jpg')");

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
		System.out.println("admin " + s);
		return s.equals("admin");
	}

	public MenuItem[] getMenuItems(){	
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		try{
			Statement s = db_con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM MenuItems");
			while (rs.next()){
				menu.add(new MenuItem(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getString("image")));
			}
			s.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return menu.toArray(new MenuItem[]{});
	}
}
