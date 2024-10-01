<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="uts.advsoft.MenuItem"%>
<%@ page import="uts.advsoft.Database"%>

<%
    Database db = (Database)application.getAttribute("database");
    MenuItem[] menuItems = db.getMenuItems();
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

        <div class="searchBar">
            <input class="searchInput" type="text" id="searchInput" onkeyup="searchMenu()" placeholder="Search for products..">
        </div>
        
        <div class="menu-grid-container" id="menu">
        <%
            try {   
                for (MenuItem i : menuItems) {
        %>
            <div class="menu-grid-item">
                <%String imgLocation = "menuImages/" + i.getImg();%>
                <img src=<%out.println(imgLocation);%> alt="Pizza">
                <div class="menu-grid-item-name"><%out.println(i.getName());%></div>
                <div class="menu-grid-item-name"><%out.println("$" + i.getPrice());%></div>
            </div>
        <%  
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        %>
        </div>   
    <div style="margin-top: 20px;">
        <a href="checkout.jsp" class="button">Go to Checkout</a>
    </div>

    <script>
        function searchMenu() {
            var input, filter, menuItems;
            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            menuItems = document.querySelectorAll(".menu-grid-item");

            menuItems.forEach(function(menuItem) {
                var itemName = menuItem.querySelector(".menu-grid-item-name").textContent.toUpperCase();

                if (itemName.indexOf(filter) > -1) {
                    menuItem.style.display = "";  // Show item
                } else {
                    menuItem.style.display = "none";  // Hide item
                }
            });
        }
    </script>
    
    </body>
</html>