<%@page import="uts.advsoft.Database"%>
<%@page import="uts.advsoft.Order"%>
<%@page import="uts.advsoft.User"%>
<%@page import="uts.advsoft.MenuItem"%>
<%@page import="uts.advsoft.MenuItemEntry"%>
<%@page import="uts.advsoft.Employee"%>
<%@page import="uts.advsoft.Cart"%>


<%
    User currentUser = (User)session.getAttribute("user");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Database db = (Database)application.getAttribute("database");
    Order[] orders = (currentUser != null) ? db.get_all_orders_by_user(currentUser.get_id()) : new Order[0];

    
    
    %>

<html>
    <head>
        <h1><strong>Past Order History</strong></h1>
    </head>

    <body>
        <div class="topnav">
            <a href="myProfile.jsp">My Profile</a>
            <a href="main.jsp">Home</a>
            <a href="orderHistory.jsp">Order History</a>
            <a href="logout.jsp" style="float:right;">Logout</a>
        </div>

        <p><strong>Below are previously made orders</strong></p>

        <table class="order-table">
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
        
            <%
            String[] delivery_methods = {"Pickup", "Walk", "Cycle", "Drive"};
            String[] statuses = {"Preparing", "Cooking", "Packing", "Delivering", "Finished"};
            Employee[] drivers = db.get_all_employees();
        
            // Only display orders that belong to the logged-in user
            for (int i = 0; i < orders.length; i++) {
                // Check if the order belongs to the current user
                if (orders[i].get_owner_id() == currentUser.get_id()) {
                    Employee cur_driver = db.get_employee(orders[i].get_driver_id());
                    MenuItemEntry[] items = orders[i].get_menu_items();
            %>
        
            <form method="post">
                <input type="hidden" id="form" name="form" value="update_order">
                <input type="hidden" id="order_id" name="order_id" value="<%= orders[i].get_id() %>">
                <tr>
                    <td><%= orders[i].get_id() %></td>
                    <td><%= orders[i].get_owner_id() %></td>
                    <td>
                        <table class="admin-table">
                            <thead>
                                <th>Item Name</th>
                                <th>Amount</th>
                            </thead>
                            <% for (int j = 0; j < items.length; j++) { %>
                            <tr>
                                <td><%= items[j].get_item().getName() %></td>
                                <td><%= items[j].get_amount() %></td>
                            </tr>
                            <% } %>
                        </table>
                    </td>
                    <td>
                        <% if (orders[i].get_status_level().equals("Finished")) { %>
                        <%= orders[i].get_delivery_method() %>
                        <% } else { %>
                        <select name="dev_method" id="dev_method">
                            <option value="<%= orders[i].get_delivery_method() %>"><%= orders[i].get_delivery_method() %></option>
                            <% for (String s : delivery_methods) {
                                if (!s.equals(orders[i].get_delivery_method())) { %>
                                <option value="<%= s %>"><%= s %></option>
                            <% }
                            } %>
                        </select>
                        <% } %>
                    </td>
                    <td>
                        <% if (orders[i].get_status_level().equals("Finished")) { %>
                        <%= cur_driver != null ? cur_driver.toString() : "" %>
                        <% } else { %>
                        <select name="dev_driver" id="dev_driver">
                            <option value="<%= cur_driver != null ? cur_driver.get_id() : 0 %>"><%= cur_driver != null ? cur_driver.toString() : "" %></option>
                            <% for (Employee e : drivers) {
                                if (e != cur_driver) { %>
                                <option value="<%= e.get_id() %>"><%= e.toString() %></option>
                            <% }
                            } %>
                        </select>
                        <% } %>
                    </td>
                    <td><%= orders[i].get_order_date() %></td>
                    <td><%= orders[i].is_current_order() %></td>
                    <td>
                        <% if (orders[i].get_status_level().equals("Finished")) { %>
                        <%= orders[i].get_status_level() %>
                        <input type="hidden" id="order_status" name="order_status" value="Finished">
                        <% } else { %>
                        <select name="order_status" id="order_status">
                            <option value="<%= orders[i].get_status_level() %>"><%= orders[i].get_status_level() %></option>
                            <% for (String s : statuses) {
                                if (!s.equals(orders[i].get_status_level())) { %>
                                <option value="<%= s %>"><%= s %></option>
                            <% }
                            } %>
                        </select>
                        <% } %>
                    </td>
                    <td><%= orders[i].get_order_price() %></td>
                    <td><input type="submit" value="Submit"></td>
                </tr>
            </form>
        
            <%
                }
            }
            %>
        </table>
        
    </body>
</html>
