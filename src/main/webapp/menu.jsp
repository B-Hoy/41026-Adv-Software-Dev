<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css">
        
        <title>Menu</title>
    </head>
    <body>
        <div class="menu-grid-container">
        <%
            try {   
                List<String> menuItems = Arrays.asList("Meatlovers Pizza", "Cheese Pizza", "Pepperoni Pizza");
                for (String s : menuItems) {
        %>
            <div class="menu-grid-item"><%out.println(s);%></div>
        <%  
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        %>
        </div>   
    </body>
</html>