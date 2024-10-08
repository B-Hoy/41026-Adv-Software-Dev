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

			s.executeUpdate("CREATE TABLE Users(id INTEGER NOT NULL PRIMARY KEY, email TEXT NOT NULL UNIQUE, first_name TEXT NOT NULL, last_name TEXT NOT NULL, password TEXT NOT NULL, phone_number TEXT NOT NULL, register_date TEXT NOT NULL, card_num TEXT NOT NULL, card_expiry_date TEXT NOT NULL, card_cvc INTEGER NOT NULL, address_street_num TEXT NOT NULL, address_street TEXT NOT NULL, address_city TEXT NOT NULL, address_postcode INTEGER NOT NULL)");
			s.executeUpdate("INSERT INTO Users VALUES" + 
				"(1, 'testing@test.com', 'test', 'user', 'testpasswd', '+61400000000', DATETIME('now', '+10 hours'), 1234567890987654, '09/24', 123, 'PO Box 1', 'Test Street', 'Sydney', 2000)," +
				"(2, 'john.doe@mail.com', 'John', 'Doe', 'P@ssw0rd123', '+61412345678', DATETIME('now', '+10 hours'), '4111111111111111', '12/25', 123, '123', 'Main St', 'Springfield', 1234)," +
				"(3, 'jane.smith@example.net', 'Jane', 'Smith', 'W0wSecure!456', '+61423456789', DATETIME('now', '+10 hours'), '5500000000000004', '05/24', 456, '456', 'Elm St', 'Metropolis', 5678)," +
				"(4, 'alice.jones@demo.org', 'Alice', 'Jones', 'S3cureP@ss789', '+61434567890', DATETIME('now', '+10 hours'), '3400000000000009', '11/26', 789, '789', 'Oak St', 'Smalltown', 4321)," +
				"(5, 'bob.brown@webmail.com', 'Bob', 'Brown', 'B0bby!P@ss', '+61445678901', DATETIME('now', '+10 hours'), '6011000000000001', '09/23', 101, '101', 'Pine St', 'Big City', 8765)," +
				"(6, 'charlie.white@sample.com', 'Charlie', 'White', 'Ch@rlie1234', '+61456789012', DATETIME('now', '+10 hours'), '3530111111111111', '03/27', 202, '202', 'Maple St', 'Townsville', 3456)," +
				"(7, 'diana.green@mailbox.org', 'Diana', 'Green', 'D!anaSecure789', '+61467890123', DATETIME('now', '+10 hours'), '4111111111111111', '04/25', 303, '303', 'Cedar St', 'Villageburg', 7890)," +
				"(8, 'ethan.black@testing.com', 'Ethan', 'Black', 'Ethan!Password1', '+61478901234', DATETIME('now', '+10 hours'), '6011000000000003', '06/24', 404, '404', 'Birch St', 'Countyline', 2345)," +
				"(9, 'fiona.adams@service.net', 'Fiona', 'Adams', 'F!0naPassword2', '+61489012345', DATETIME('now', '+10 hours'), '5500000000000005', '10/23', 505, '505', 'Walnut St', 'Rivertown', 6789)," +
				"(10, 'george.miller@platform.com', 'George', 'Miller', 'Ge0rgeSecure3', '+61490123456', DATETIME('now', '+10 hours'), '3400000000000002', '07/26', 606, '606', 'Ash St', 'Capital City', 1357)," +
				"(11, 'hannah.jackson@provider.org', 'Hannah', 'Jackson', 'H@nnaH!456', '+61491234567', DATETIME('now', '+10 hours'), '3530111111111112', '02/25', 707, '707', 'Poplar St', 'Hilltop', 2468)," +
				"(12, 'ian.lee@myemail.com', 'Ian', 'Lee', '1A!nSecure456', '+61492234568', DATETIME('now', '+10 hours'), '4111111111111111', '01/27', 808, '808', 'Chestnut St', 'Lakeview', 3579)," +
				"(13, 'jessica.clark@virtual.net', 'Jessica', 'Clark', 'J3ss!C@rPassword', '+61493245679', DATETIME('now', '+10 hours'), '6011000000000006', '03/24', 909, '909', 'Spruce St', 'Baytown', 4680)," +
				"(14, 'kevin.wright@xyz.com', 'Kevin', 'Wright', 'K3vinPass!456', '+61494256780', DATETIME('now', '+10 hours'), '5500000000000007', '12/25', 111, '111', 'Fir St', 'Greenfield', 5791)," +
				"(15, 'laura.hall@mail.org', 'Laura', 'Hall', 'L@uraSecure234', '+61495267891', DATETIME('now', '+10 hours'), '3400000000000008', '05/26', 222, '222', 'Larch St', 'Oceanview', 6802)," +
				"(16, 'mark.harris@company.com', 'Mark', 'Harris', 'M@rkPassword789', '+61496278902', DATETIME('now', '+10 hours'), '3530111111111113', '09/25', 333, '333', 'Sequoia St', 'Plainsville', 7913)," +
				"(17, 'nora.martin@outlook.com', 'Nora', 'Martin', 'N0r@Secure123', '+61497289013', DATETIME('now', '+10 hours'), '4111111111111111', '11/24', 444, '444', 'Redwood St', 'Woodland', 8024)," +
				"(18, 'oscar.thompson@network.org', 'Oscar', 'Thompson', '0sc@rP@ss456', '+61498290124', DATETIME('now', '+10 hours'), '6011000000000000', '07/25', 555, '555', 'Cypress St', 'Forest City', 9135)," +
				"(19, 'paula.roberts@mailservice.com', 'Paula', 'Roberts', 'P@ul@Secure234', '+61499201235', DATETIME('now', '+10 hours'), '5500000000000001', '12/27', 666, '666', 'Hemlock St', 'Hill Valley', 0246)," +
				"(20, 'quinn.walker@anymail.com', 'Quinn', 'Walker', 'Q!innSecure567', '+61440012345', DATETIME('now', '+10 hours'), '3400000000000004', '04/26', 777, '777', 'Fir St', 'Summitville', 1358)," +
				"(21, 'rachel.young@internetmail.org', 'Rachel', 'Young', 'R@chelP@ss123', '+61440123456', DATETIME('now', '+10 hours'), '3530111111111115', '08/23', 888, '888', 'Willow St', 'Crestwood', 2466)"
			);

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
	public User get_user(String email, String password){
		try {
			PreparedStatement ps = db_con.prepareStatement("SELECT * FROM Users WHERE email = (?) AND password = (?)");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				return new User(rs.getInt("id"), rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("password"), rs.getString("phone_number"), rs.getString("register_date"), rs.getString("card_num"), rs.getString("card_expiry_date"), rs.getInt("card_cvc"), rs.getString("address_street_num"), rs.getString("address_street"), rs.getString("address_city"), rs.getInt("address_postcode"));
			}
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return null; // no such user
	}
	public void make_user(String email, String fname, String lname, String password, String pnum, String cardnum, String cardexp, String cardcvc, String addrnum, String addst, String addrcity, int addrpcode){
		try {
			if (email == null || email.trim().isEmpty() || fname == null || fname.trim().isEmpty() || lname == null || lname.trim().isEmpty() || password == null || password.trim().isEmpty() || pnum == null || pnum.trim().isEmpty() || cardnum == null || cardnum.trim().isEmpty() || cardexp == null || cardexp.trim().isEmpty() || cardcvc == null || cardcvc.trim().isEmpty() || addrnum == null || addrnum.trim().isEmpty() || addst == null || addst.trim().isEmpty() || addrcity == null || addrcity.trim().isEmpty()){
				throw new IllegalArgumentException("Found null or empty string");
			}
			PreparedStatement ps = db_con.prepareStatement("INSERT INTO Users VALUES((?), (?), (?), (?), (?), (?), DATETIME('now', '+10 hours'), (?), (?), (?), (?), (?), (?), (?))");
			ps.setInt(1, generate_id("Users"));
			ps.setString(2, email);
			ps.setString(3, fname);
			ps.setString(4, lname);
			ps.setString(5, password);
			ps.setString(6, pnum);
			ps.setString(7, cardnum);
			ps.setString(8, cardexp);
			ps.setString(9, cardcvc);
			ps.setString(10, addrnum);
			ps.setString(11, addst);
			ps.setString(12, addrcity);
			ps.setInt(13, addrpcode);
			ps.executeUpdate();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
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
	public Order[] get_all_orders_by_user(int user_id){
		ArrayList<Order> a = new ArrayList<Order>();
		try{
			PreparedStatement ps = db_con.prepareStatement("SELECT * FROM Orders WHERE owner_id = (?)");
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				a.add(new Order(rs.getInt("id"), rs.getInt("owner_id"), rs.getInt("driver_id"), rs.getString("menu_items"), rs.getString("delivery_method"), rs.getString("order_date"), rs.getBoolean("current_order"), rs.getString("status_level"), rs.getFloat("order_price")));
			}
			ps.close();
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
		/*try{
			PreparedStatement ps = db_con.prepareStatement("DELETE FROM Carts WHERE owner_id = (?)");
			ps.setInt(1, user_id);
			ps.executeUpdate();
			ps.close();
		}catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}*/
		try{
			PreparedStatement ps = db_con.prepareStatement("UPDATE Carts SET menu_items = '', price = 0.0 WHERE owner_id = (?)");
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
