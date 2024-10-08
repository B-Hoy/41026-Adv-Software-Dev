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

        <% String priceString = "$" + i.getPrice(); %>
        <div class="menu-grid-item" onclick="openModal('<%= i.getName() %>', '<%= priceString %>', '<%= i.getDescription() %>', 'menuImages/<%= i.getImg() %>')">
            <img src="menuImages/<%= i.getImg() %>" alt="Pizza">
            <div class="menu-grid-item-name"><%= i.getName() %></div>
            <div class="menu-grid-item-price"><%= "$" + i.getPrice() %></div>
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

    <div id="itemModal" class="modal" onclick="closeModal(event)">
        <div class="modal-content">
            <span class="modal-close-button" onclick="closeModal(event)">&times;</span>
            <img id="modalImage" src="" alt="Item Image" class="modal-image">
            <div class="modal-details">
                <h2 id="modalName">Item Name</h2>
                <p id="modalPrice">Item Price</p>
                <p id="modalDescription">Item Description</p>
                <button id="modalButton">Add to Cart</button>
            </div>
        </div>
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
                    menuItem.style.display = "block";
                } else {
                    menuItem.style.display = "none"
                }
            });
        }

        function openModal(name, price, description, image) {
                document.getElementById("modalName").textContent = name;
                document.getElementById("modalPrice").textContent = price;
                document.getElementById("modalDescription").textContent = description;
                document.getElementById("modalImage").src = image;

                document.getElementById("itemModal").style.display = "flex";
            }

        function closeModal(event) {
            if (event.target.className === "modal" || event.target.className === "modal-close-button") {
                document.getElementById("itemModal").style.display = "none";
            }
        }
    </script>
    
    </body>
</html>