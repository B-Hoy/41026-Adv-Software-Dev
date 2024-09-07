<%@page import="uts.advsoft.Database"%>
<%@page import="uts.advsoft.Order"%>
<%@page import="uts.advsoft.User"%>

<%  Database db = (Database)application.getAttribute("database");
	String form_class = request.getParameter("form");
	if (form_class != null){
		switch (form_class){
			case "admin_check":
				if (db.is_admin_password(request.getParameter("password"))){
					session.setAttribute("is_admin", true);
				}
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
				<th colspan="8">
					<b>Orders</b>
				</th>
			</thead>
			<thead>
				<th>ID</th>
				<th>Owner ID</th>
				<th>Menu Items</th>
				<th>Delivery Method</th>
				<th>Order Date</th>
				<th>Is Current Order?</th>
				<th>Status Level</th>
				<th>Order Price</th>
			</thead>
			<% for (int i = 0; i < orders.length; i++){%>
			<tr>
				<td><%=orders[i].get_id()%></td>
				<td><%=orders[i].get_owner_id()%></td>
				<td><%=orders[i].get_menu_items()%></td>
				<td><%=orders[i].get_delivery_method()%></td>
				<td><%=orders[i].get_order_date()%></td>
				<td><%=orders[i].is_current_order()%></td>
				<td><%=orders[i].get_status_level()%></td>
				<td><%=orders[i].get_order_price()%></td>
			</tr>
			<%}%>
		</table>
	</body>
	<%}%>
</html>
