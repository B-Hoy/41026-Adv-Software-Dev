<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.Comparator"%>
<%@ page import="uts.advsoft.MenuItem"%>
<%@ page import="uts.advsoft.Database"%>
<%@ page import="uts.advsoft.User"%>
<%@page import="uts.advsoft.MenuItemEntry"%>
<%@page import="uts.advsoft.Cart"%>

<%
    User user = (User)session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Database db = (Database)application.getAttribute("database");
    MenuItem[] menuItems = db.getMenuItems(); 

    String userID = request.getParameter("userID");
    String itemID = request.getParameter("itemID");
    String quantityStr = request.getParameter("quantity");

    if (userID != null && itemID != null && quantityStr != null) {
        int userIDInt = Integer.parseInt(userID);
        int itemIDInt = Integer.parseInt(itemID);
        int quantity = Integer.parseInt(quantityStr);

        db.add_to_cart(userIDInt, itemIDInt, quantity);

        System.out.println(itemID + " added to cart");
    } 

    String sortType = (request.getParameter("sortType")==null) ? "az" : request.getParameter("sortType");
    System.out.print(sortType);
    if (sortType.equals("az")){
        //sort by name a-z
        Arrays.sort(menuItems, new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem item1, MenuItem item2) {
                return item1.getName().compareTo(item2.getName());
            }
        });
    } else if (sortType.equals("za")){
        //sort by name z-a
        Arrays.sort(menuItems, new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem item1, MenuItem item2) {
                return item2.getName().compareTo(item1.getName());
            }
        });
    } else if (sortType.equals("hl")){
        //sort by price high-low
        Arrays.sort(menuItems, new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem item1, MenuItem item2) {
                return Double.compare(item2.getPrice(), item1.getPrice());
            }
        });
    } else if (sortType.equals("lh")){
        //sort by price low-high
        Arrays.sort(menuItems, new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem item1, MenuItem item2) {
                return Double.compare(item1.getPrice(), item2.getPrice());
            }
        });
    }
    
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
            <input class="searchInput" type="text" id="searchInput" onkeyup="searchMenu()" placeholder="Search for products...">
            <div class="sortDropdown">
                <button class="sortButton">Sort...</button>
                <div class="sortDropdown-content">
                    <a onclick="sortMenu('az')">Name (A-Z)</a>
                    <a onclick="sortMenu('za')">Name (Z-A)</a>
                    <a onclick="sortMenu('hl')">Price (High-Low)</a>
                    <a onclick="sortMenu('lh')">Price (Low-High)</a>
                </div>
            </div>
        </div>
        
        <div class="menu-grid-container" id="menu">
        <%
            try {   
                for (MenuItem i : menuItems) {
        %>

        <% String priceString = "$" + i.getPrice(); %>
        <div class="menu-grid-item" onclick="openModal('<%= i.getName() %>', '<%= priceString %>', '<%= i.getDescription() %>', 'menuImages/<%= i.getImg() %>', '<%= i.getID() %>',)">
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

    <a href="checkout.jsp" class="button">Go to Checkout</a>

    <!-- Modal: A popup that will appear when clicking on an item which displays details about that item -->
    <!-- A single modal is used for all items, the details are changed via the openModal() function in the script below -->
    <div id="itemModal" class="modal" data-itemid=0 onclick="closeModal(event)">
        <div class="modal-content">
            <span class="modal-close-button" onclick="closeModal(event)">&times;</span>
            <img id="modalImage" src="" alt="Item Image" class="modal-image">
            <div class="modal-details">
                <h2 id="modalName">Item Name</h2>
                <p id="modalPrice">Item Price</p>
                <p id="modalDescription">Item Description</p>

                <button id="modalButton" onclick="addToCart('<%=user.get_id()%>')">Add to Cart</button>
            </div>
        </div>
    </div>

    <script>
        function searchMenu() {
            var input, searchTerm, menuItems;
            input = document.getElementById("searchInput");
            searchTerm = input.value.toUpperCase();
            //changed variable name from 'filter' to 'searchTerm' as it's a more accurate name
            menuItems = document.querySelectorAll(".menu-grid-item");

            menuItems.forEach(function(menuItem) {
                var itemName = menuItem.querySelector(".menu-grid-item-name").textContent.toUpperCase();

                if (itemName.indexOf(searchTerm) > -1) {
                    menuItem.style.display = "block";
                } else {
                    menuItem.style.display = "none"
                }
            });
        }

        // Gets the details for the item that was clicked on, and updates the modal to display the details
        function openModal(name, price, description, image, id) {
            document.getElementById("modalName").textContent = name;
            document.getElementById("modalPrice").textContent = price;
            document.getElementById("modalDescription").textContent = description;
            document.getElementById("modalImage").src = image;
            document.getElementById("itemModal").dataset.itemid = id;

            document.getElementById("itemModal").style.display = "flex";
        }

        // Hides the modal when the user presses outside the window or on the close button 
        function closeModal(event) {
            if (event.target.className === "modal" || event.target.className === "modal-close-button") {
                document.getElementById("itemModal").style.display = "none";
            }
        }

        function addToCart(user){
            var userID = user;
            var quantity = 1;
            var itemID = document.getElementById("itemModal").dataset.itemid;

            if (!itemID || !userID) {
                alert("Error: No item selected or user not logged in.");
                return;
            }
            
            var form = document.createElement('addToCartForm');
            form.method = 'POST';
            form.action = '';  // This form submits to the current page

            var itemIDInput = document.createElement('input');
            itemIDInput.type = 'hidden';
            itemIDInput.name = 'itemID';
            itemIDInput.value = itemID;
            form.appendChild(itemIDInput);

            var userIDInput = document.createElement('input');
            userIDInput.type = 'hidden';
            userIDInput.name = 'userID';
            userIDInput.value = userID;
            form.appendChild(userIDInput);

            var quantityInput = document.createElement('input');
            quantityInput.type = 'hidden';
            quantityInput.name = 'quantity';
            quantityInput.value = quantity;
            form.appendChild(quantityInput);

            document.body.appendChild(form);
            form.submit();  // Submit the form
        }

        function sortMenu(sort){
            var sortType = sort;

            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '';  // This form submits to the current page

            var sortTypeInput = document.createElement('input');
            sortTypeInput.type = 'hidden';
            sortTypeInput.name = 'sortType';
            sortTypeInput.value = sortType;
            form.appendChild(sortTypeInput);

            document.body.appendChild(form);
            form.submit();  // Submit the form
        }

    </script>
    
    </body>
</html>