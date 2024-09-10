<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="uts.advsoft.Pizza"%>

<%
    List<Pizza> menuItems = new ArrayList<Pizza>();
    menuItems.add(new Pizza(1, "Meatlovers", "meatlovers.jpg"));
    menuItems.add(new Pizza(2, "Pepperoni", "meatlovers.jpg"));
    menuItems.add(new Pizza(3, "Ham and Cheese", "meatlovers.jpg"));
    menuItems.add(new Pizza(4, "Cheese", "meatlovers.jpg"));
    menuItems.add(new Pizza(5, "Garlic Cheese", "meatlovers.jpg"));
    menuItems.add(new Pizza(6, "Vegeterian", "meatlovers.jpg"));
    menuItems.add(new Pizza(7, "Hawaiian", "meatlovers.jpg"));
    menuItems.add(new Pizza(8, "Garlic Prawn", "meatlovers.jpg"));
    menuItems.add(new Pizza(9, "Supreme", "meatlovers.jpg"));
    menuItems.add(new Pizza(10, "Chicken and Bacon", "meatlovers.jpg"));
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css">
        
        <title>Menu</title>
    </head>
    <body>
        <div class="menu">
        <h1>Menu</h1>
        <div class="menu-grid-container">
        <%
            try {   
                for (Pizza p : menuItems) {
        %>
            <div class="menu-grid-item">
                <img src=<%out.println(p.getImg());%> alt="Pizza">
                <div class="menu-grid-item-name"><%out.println(p.getName());%></div>
            </div>
        <%  
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        %>
        </div>   
    </div>
    </body>
</html>