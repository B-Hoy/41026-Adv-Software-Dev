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
        <div class="menu-grid-container">
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
    </body>
</html>