<%@page import="uts.advsoft.Database"%>
<%@page import="uts.advsoft.Order"%>
<%@page import="uts.advsoft.User"%>
<%@page import="uts.advsoft.MenuItem"%>
<%@page import="uts.advsoft.MenuItemEntry"%>
<%@page import="uts.advsoft.Employee"%>

<%  Database db = (Database)application.getAttribute("database");
	String form_class = request.getParameter("form");
	if (form_class != null){
		switch (form_class){
		case "admin_check":
			if (db.is_admin_password(request.getParameter("password"))){
				session.setAttribute("is_admin", true);
			}
			break;
		case "update_order":
			db.page_update_order(Integer.valueOf(request.getParameter("order_id")), request.getParameter("dev_method"), Integer.valueOf(request.getParameter("dev_driver")), request.getParameter("order_status"));
			break;
		}
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="style.css" type="text/css">
		<title>Admin View</title>
	</head>
	<body>
	<% if (session.getAttribute("is_admin") == null){%>
		<form method="post">
			<label for="password"><b>Password</b></label>
			<br>
			<input type="text" id="password" name="password">
			<input type="hidden" id="form" name="form" value="admin_check">
		</form>
	<%}else{%>
		<% 
			User[] users = db.get_all_users();
			Order[] orders = db.get_all_orders();
		%>
		<table class="admin-table">
			<thead>
				<th colspan="14">
					<b>Users</b>
				</th>
			</thead>
			<thead>
				<th>ID</th>
				<th>Email</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Password</th>
				<th>Phone Number</th>
				<th>Register Date</th>
				<th>Card Number</th>
				<th>Card Expiry</th>
				<th>Card CVC</th>
				<th>Address Street Number</th>
				<th>Address Street</th>
				<th>Address City</th>
				<th>Address Postcode</th>
			</thead>
			<% for (int i = 0; i < users.length; i++){%>
			<tr>
				<td><%=users[i].get_id()%></td>
				<td><%=users[i].get_email()%></td>
				<td><%=users[i].get_first_name()%></td>
				<td><%=users[i].get_last_name()%></td>
				<td><%=users[i].get_password()%></td>
				<td><%=users[i].get_phone_num()%></td>
				<td><%=users[i].get_register_date()%></td>
				<td><%=users[i].get_card_num()%></td>
				<td><%=users[i].get_card_expiry_date()%></td>
				<td><%=users[i].get_card_cvc()%></td>
				<td><%=users[i].get_address_street_num()%></td>
				<td><%=users[i].get_address_street()%></td>
				<td><%=users[i].get_address_city()%></td>
				<td><%=users[i].get_address_postcode()%></td>
			</tr>
			<%}%>
		</table>
		<br>
		<table class="admin-table">
			<thead>
				<th colspan="99">
					<b>Orders</b>
				</th>
			</thead>
			<thead>
				<th>ID</th>
				<th>Owner ID</th>
				<th>Menu Items</th>
				<th>Delivery Method</th>
				<th>Current Driver</th>
				<th>Order Date</th>
				<th>Is Current Order?</th>
				<th>Status Level</th>
				<th>Order Price</th>
			</thead>
			<%  String[] delivery_methods = {"Pickup", "Walk", "Cycle", "Drive"};
				String[] statuses = {"Preparing", "Cooking", "Packing", "Delivering", "Finished"};
				Employee[] drivers = db.get_all_employees();
				for (int i = 0; i < orders.length; i++){
					Employee cur_driver = db.get_employee(orders[i].get_driver_id());
					MenuItemEntry[] items = orders[i].get_menu_items();
			%>
			<form method="post">
			<input type="hidden" id="form" name="form" value="update_order">
			<input type="hidden" id="order_id" name="order_id" value="<%=orders[i].get_id()%>">
				<tr>
					<td><%=orders[i].get_id()%></td>
					<td><%=orders[i].get_owner_id()%></td>
					<td><table class = "admin-table">
						<thead>
						<th>Item Name</th>
							<th>Amount</th>
						</thead>
						<% for (int j = 0; j < items.length; j++){ %>
						<tr>
							<td><%=items[j].get_item().getName()%></td>
							<td><%=items[j].get_amount()%></td>
						</tr>
						<%}%>
					</table></td>
					<td>
						<% if (orders[i].get_status_level().equals("Finished")){ %>
						<%=orders[i].get_delivery_method()%>
						<%}else{%>
						<select name="dev_method" id="dev_method">
							<option value="<%=orders[i].get_delivery_method()%>"><%=orders[i].get_delivery_method()%></option>
							<% for (String s : delivery_methods){
								if (!s.equals(orders[i].get_delivery_method())){ %>
								 <option value="<%=s%>"><%=s%></option>
							<%	}
							}%>
						</select>
						<%}%>
					</td>
					<td>
						<% if (orders[i].get_status_level().equals("Finished")){ %>
						<%=cur_driver.toString()%>
						<%}else{%>
						<select name="dev_driver" id="dev_driver">
							<option value="<%=cur_driver != null ? cur_driver.get_id() : 0%>"><%=cur_driver != null ? cur_driver.toString() : ""%></option>
							<% for (Employee e : drivers){
								if (e != cur_driver){ %>
								<option value="<%=e.get_id()%>"><%=e.toString()%></option>
							<%	}
							}%>
						</select>
						<%}%>
					</td>
					<td><%=orders[i].get_order_date()%></td>
					<td><%=orders[i].is_current_order()%></td>
					<td>
						<% if (orders[i].get_status_level().equals("Finished")){ %>
						<%=orders[i].get_status_level()%>
						<input type="hidden" id="order_status" name="order_status" value="Finished">
						<%}else{%>
						<select name="order_status" id="order_status">
							<option value="<%=orders[i].get_status_level()%>"><%=orders[i].get_status_level()%></option>
							<% for (String s : statuses){
								if (!s.equals(orders[i].get_status_level())){ %>
								 <option value="<%=s%>"><%=s%></option>
							<%	}
							}%>
						</select>
						<%}%>
					</td>
					<td><%=orders[i].get_order_price()%></td>
					<td><input type="submit" value="Submit"></td>
					</form>
				</tr>
				<%}%>
			</table>
	</body>
	<%}%>
</html>
