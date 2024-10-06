package uts.advsoft;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Database{
	static Connection db_con;
	public Database(){
		try{
			Class.forName("org.sqlite.JDBC");
			File db_file = File.createTempFile("temp", ".sqlite");
			db_file.deleteOnExit();
			db_con = DriverManager.getConnection("jdbc:sqlite:" + db_file.getAbsolutePath());
			Statement s = db_con.createStatement();

			s.executeUpdate("CREATE TABLE Users(id INTEGER NOT NULL PRIMARY KEY, email TEXT NOT NULL, first_name TEXT NOT NULL, last_name TEXT NOT NULL, password TEXT NOT NULL, phone_number TEXT NOT NULL, register_date TEXT NOT NULL, card_num TEXT NOT NULL, card_expiry_date TEXT NOT NULL, card_cvc INTEGER NOT NULL, address_street_num TEXT NOT NULL, address_street TEXT NOT NULL, address_city TEXT NOT NULL, address_postcode INTEGER NOT NULL)");
			s.executeUpdate("INSERT INTO Users VALUES(1, 'testing@test.com', 'test', 'user', 'testpasswd', '+61400000000', DATETIME('now', '+10 hours'), 1234567890987654, '09/24', 123, 'PO Box 1', 'Test Street', 'Sydney', 2000)");

			s.executeUpdate("CREATE TABLE Orders(id INTEGER NOT NULL PRIMARY KEY, owner_id INTEGER NOT NULL, driver_id INTEGER NOT NULL, menu_items TEXT NOT NULL, delivery_method TEXT NOT NULL, order_date TEXT NOT NULL, current_order BOOLEAN NOT NULL, status_level TEXT NOT NULL, order_price REAL NOT NULL, FOREIGN KEY(owner_id) REFERENCES Users(id))");
			s.executeUpdate("INSERT INTO ORDERS VALUES(1, 1, 0, '1:1,2:2,3:3', 'Walk', DATETIME('now', '+10 hours'), 1, 'Cooking', 93.94)");

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
			
			s.executeUpdate("CREATE TABLE Carts(id INTEGER NOT NULL PRIMARY KEY, owner_id INTEGER NOT NULL, menu_items TEXT NOT NULL, price REAL NOT NULL, FOREIGN KEY(owner_id) REFERENCES Users(id))");
			s.executeUpdate("INSERT INTO Carts VALUES(1, 1, '1:1,2:2,3:3', 93.94)");
			s.executeUpdate("CREATE TABLE Employees(id INTEGER NOT NULL PRIMARY KEY, first_name TEXT NOT NULL, last_name TEXT NOT NULL, password TEXT NOT NULL, hire_date TEXT NOT NULL, role TEXT NOT NULL)");
			s.executeUpdate("INSERT INTO Employees VALUES(1, 'Test', 'Employee', 'emppwd', '2020-09-01 18:16:12', 'Driver')");
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
				a.add(new Order(rs.getInt("id"), rs.getInt("owner_id"), rs.getInt("driver_id"), rs.getString("menu_items"), rs.getString("delivery_method"), rs.getString("order_date"), rs.getBoolean("current_order"), rs.getString("status_level"), rs.getFloat("order_price")));
			}
			s.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return a.toArray(new Order[]{});
	}
	public Order get_order(int id){
		try{
			PreparedStatement ps = db_con.prepareStatement("SELECT * FROM Orders WHERE id = (?)");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				return new Order(rs.getInt("id"), rs.getInt("owner_id"), rs.getInt("driver_id"), rs.getString("menu_items"), rs.getString("delivery_method"), rs.getString("order_date"), rs.getBoolean("current_order"), rs.getString("status_level"), rs.getFloat("order_price"));
			}
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		// Only get here if the ID isn't found
		return null;
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
				menu.add(new MenuItem(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getString("image")));
			}
			s.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return menu.toArray(new MenuItem[]{});
	}
	public static MenuItem get_menu_item(int id){
		try{
			PreparedStatement ps = db_con.prepareStatement("SELECT * FROM MenuItems WHERE id = (?)");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				return new MenuItem(id, rs.getString("name"), rs.getDouble("price"), rs.getString("image"));
			}
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		// Only get here if the ID isn't found
		return null;
	}
	public static MenuItem get_menu_item(String name){
		try{
			PreparedStatement ps = db_con.prepareStatement("SELECT * FROM MenuItems WHERE name LIKE (?) LIMIT 1");
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				return new MenuItem(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getString("image"));
			}
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		// Only get here if the name isn't found
		return null;
	}
	public Employee[] get_all_employees(){
		ArrayList<Employee> emps = new ArrayList<Employee>();
		try{
			Statement s = db_con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Employees");
			while (rs.next()){
				emps.add(new Employee(rs.getInt("id"), rs.getString("role"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("password"), rs.getString("hire_date").substring(0, 10)));
			}
			s.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return emps.toArray(new Employee[]{});	
	}
	public Employee get_employee(int id){
		try{
			PreparedStatement ps = db_con.prepareStatement("SELECT * FROM Employees WHERE id = (?)");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				return new Employee(rs.getInt("id"), rs.getString("role"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("password"), rs.getString("hire_date").substring(0, 10));
			}
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return null;
	}
	public void page_update_order(int order_id, String method, int driver, String status){
		try{
			PreparedStatement ps = db_con.prepareStatement("UPDATE Orders SET delivery_method = (?), driver_id = (?), status_level = (?), current_order = (?) WHERE id = (?)");
			ps.setString(1, method);
			ps.setInt(2, driver);
			ps.setString(3, status);
			ps.setBoolean(4, !status.equals("Finished"));
			ps.setInt(5, order_id);
			ps.executeUpdate();
			ps.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	public Cart[] get_all_carts(){
		ArrayList<Cart> cs = new ArrayList<Cart>();
		try{
			Statement s = db_con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Carts");
			while (rs.next()){
				cs.add(new Cart(rs.getInt("id"), rs.getInt("owner_id"), rs.getString("menu_items"), rs.getDouble("price")));
			}
			s.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return cs.toArray(new Cart[]{});	
	}
	public Cart get_cart(int id, String which){
		Cart c = null;
		try{
			PreparedStatement ps = db_con.prepareStatement("SELECT * FROM Carts WHERE " + which + "= (?)");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				c = new Cart(rs.getInt("id"), rs.getInt("owner_id"), rs.getString("menu_items"), rs.getDouble("price"));
			}
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return c;
	}
	public void delete_cart(int user_id){
		try{
			PreparedStatement ps = db_con.prepareStatement("DELETE FROM Carts WHERE owner_id = (?)");
			ps.setInt(1, user_id);
			ps.executeUpdate();
			ps.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	public int generate_id(String table){
		int id = 0; // 0 is invalid id
		try{
			Statement ps = db_con.createStatement();
			ResultSet rs = ps.executeQuery("SELECT id FROM " + table + " ORDER BY id DESC");
			id = rs.getInt("id") + 1;
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return id;
	}
	public void make_order(int user_id){
		try{
			PreparedStatement ps = db_con.prepareStatement("SELECT * FROM Orders WHERE owner_id = (?) AND current_order = 1");
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				// already have an order on it's way, no more food!
				return;
			}
			Cart user_cart = get_cart(user_id, "owner_id");
			ps = db_con.prepareStatement("INSERT INTO Orders VALUES((?), (?), 0, (?), 'Walk', DATETIME('now', '+10 hours'), 1, 'Preparing', (?))"); 
			ps.setInt(1, generate_id("Orders"));
			ps.setInt(2, user_id);
			ps.setString(3, user_cart.get_cart_item_string());
			ps.setDouble(4, user_cart.get_price());
			ps.executeUpdate();
			delete_cart(user_id);
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	public void add_to_cart(int user_id, int item_id, int amount){
		if (amount < 1){ // ez pz
			return;
		}
		Cart user_cart = get_cart(user_id, "owner_id");
		user_cart.add_item(item_id, amount);
		try{
			PreparedStatement ps;
			if (user_cart == null){
				ps = db_con.prepareStatement("INSERT INTO Carts VALUES((?), (?), (?), (?))");
				ps.setInt(1, generate_id("Carts"));
				ps.setInt(2, user_id);
				ps.setString(3, user_cart.get_cart_item_string());
				ps.setDouble(4, user_cart.get_price());
			}else{
				ps = db_con.prepareStatement("UPDATE Carts SET menu_items = (?), price = (?) WHERE owner_id = (?)");
				ps.setString(1, user_cart.get_cart_item_string());
				ps.setDouble(2, user_cart.get_price());
				ps.setInt(3, user_id);
			}
			ps.executeUpdate();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	public void remove_from_cart(int user_id, int item_id){
		Cart user_cart = get_cart(user_id, "owner_id");
		if (user_cart == null){
			return;
		}
		MenuItemEntry target_item = user_cart.get_menu_item_entry(item_id);
		if (target_item == null){
			return;
		}
		user_cart.remove_item(item_id);
		try{
			PreparedStatement ps = db_con.prepareStatement("UPDATE Carts SET menu_items = (?), price = (?) WHERE owner_id = (?)");
			ps.setString(1, user_cart.get_cart_item_string());
			ps.setDouble(2, user_cart.get_price());
			ps.setInt(3, user_id);
			ps.executeUpdate();
			ps.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	public void remove_from_cart(int user_id, int item_id, int amount){
		Cart user_cart = get_cart(user_id, "owner_id");
		if (amount < 1 || user_cart == null){
			return;
		}
		MenuItemEntry target_item = user_cart.get_menu_item_entry(item_id);
		if (target_item == null){
			return;
		}
		if (target_item.get_amount() <= amount){
			this.remove_from_cart(user_id, item_id);
		}
		user_cart.remove_item(item_id, amount);
		try{
			PreparedStatement ps = db_con.prepareStatement("UPDATE Carts SET menu_items = (?), price = (?) WHERE owner_id = (?)");
			ps.setString(1, user_cart.get_cart_item_string());
			ps.setDouble(2, user_cart.get_price());
			ps.setInt(3, user_id);
			ps.executeUpdate();
			ps.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}
