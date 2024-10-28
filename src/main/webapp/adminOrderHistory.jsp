<%@page import="uts.advsoft.Database"%>
<%@page import="uts.advsoft.Order"%>
<%@page import="uts.advsoft.User"%>
<%@page import="uts.advsoft.MenuItem"%>
<%@page import="uts.advsoft.MenuItemEntry"%>
<%@page import="uts.advsoft.Employee"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<!-- do something here to stop non admins-->
<%  
    Database db = (Database)application.getAttribute("database");
	User customer = db.get_user(request.getParameter("username"), request.getParameter("password"));

    if (customer==null){
        response.sendRedirect("admin.jsp");
        return;
    }

    Order[] orders = db.get_all_orders_by_user(customer.get_id());
    Map<String, Integer> itemCount = new HashMap<>();
    Map<String, Double> itemTotalPrices = new HashMap<>();

    for (Order order : orders) {
        for (MenuItemEntry entry : order.get_menu_items()) {
            MenuItem item = entry.get_item();
            String itemName = item.getName();
            int quantity = entry.get_amount();
            double price = item.getPrice() * quantity;

            itemCount.put(itemName, itemCount.getOrDefault(itemName, 0) + quantity);
            itemTotalPrices.put(itemName, itemTotalPrices.getOrDefault(itemName, 0.0) + price);
        }
    }
%>

<!DOCTYPE html>
<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="style.css" type="text/css">
		<title>Customer Order History</title>
	</head>
    <body>
        <div class="adminOrderHistory">
            <h1><%= customer.get_first_name() %> <%= customer.get_last_name() %>'s Order History</h1>
            <h3>Item Overview</h3>
            <table class="adminOrderHistoryTable">
                <thead>
                    <th>Menu Item</th>
                    <th>Quantity Ordered</th>
                    <th>Total Spend</th>
                </thead>
                <%  
                for (String itemName : itemCount.keySet()) {
                    int totalCount = itemCount.get(itemName);
                    double totalPrice = itemTotalPrices.get(itemName);
                %>
                <tr>
                    <td><%= itemName %></td>
                    <td><%= totalCount %></td>
                    <td><%= String.format("%.2f", totalPrice) %></td>
                </tr>
                <%}%>
            </table>
            <br>
            <h3>All Orders</h3>
            <table class="adminOrderHistoryTable">
                <thead>
                    <th>ID</th>
                    <th>Items</th>
                    <th>Delivery Method</th>
                    <th>Delivery Driver</th>
                    <th>Order Date</th>
                    <th>Total Price</th>
                </thead>
                <%
                    try {   
                        for (Order o : orders) {
                            String items = "";
                            for (MenuItemEntry i : o.get_menu_items()){
                                items = items + i.get_amount() + " " + i.get_item().getName() + "\n";
                            }

                %>  
                <tr>
                    <td><%= o.get_id() %></td>
                    <td class="itemsAdminOrderHistory"><%= items %></td>
                    <td><%= o.get_delivery_method() %></td>
                    <td><%= o.get_driver_id() %></td>
                    <td><%= o.get_order_date() %></td>
                    <td><%= o.getTotalPriceFormatted() %></td>
                </tr>
                <%  
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                %>
            </table>
            <br>
            <br>
            <a href="admin.jsp" class="backButton">Back</a>
            <br>
        </div>
    </body>
</html>