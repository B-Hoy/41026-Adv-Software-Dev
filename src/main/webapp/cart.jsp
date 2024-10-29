<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.advsoft.MenuItem"%>
<%@page import="uts.advsoft.Database"%>
<%@page import="uts.advsoft.User"%>
<%@page import="uts.advsoft.MenuItemEntry"%>
<%@page import="uts.advsoft.Cart"%>

<%
    User user = (User)session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Database db = (Database)application.getAttribute("database");

    String userID = request.getParameter("userID");
    String itemID = request.getParameter("itemID");
    String quantityStr = request.getParameter("quantity");

    if (userID != null && itemID != null && quantityStr != null) {
        int userIDInt = Integer.parseInt(userID);
        int itemIDInt = Integer.parseInt(itemID);
        int quantity = Integer.parseInt(quantityStr);

		if (quantity == 0){
			db.remove_from_cart(userIDInt, itemIDInt);
			System.out.println("Removed " + itemID);
		}else{
			db.remove_from_cart(userIDInt, itemIDInt, quantity);
			System.out.println("Deleted " + quantityStr + " of " + itemID);
		}
        //System.out.println(itemID + " " + rem_or_delete ? "deleted" : ("removed " + quantityStr) + " from cart");
    }
	Cart user_cart = db.get_cart(user.get_id(), "owner_id");
	if (user_cart == null){
		db.create_cart(user.get_id());
		user_cart = db.get_cart(user.get_id(), "owner_id");
	}
    MenuItemEntry[] menuItems = user_cart.get_cart_items();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css">
        
        <title>Cart</title>
    </head>
    <body>
        <div class="menu">
		<a href="menu.jsp"><h1>Menu</h1></a>
        <h1><b>Cart<b></h1>
        <%
			if (menuItems.length > 0){
		%>
        <div class="menu-grid-container" id="menu">
		<%
	            try {   
    	            for (MenuItemEntry i : menuItems) {
        %>

        <% String priceString = "Amount: " + String.valueOf(i.get_amount()) + " Total: " + i.get_value(); %>
        <div class="menu-grid-item" onclick="openModal('<%= i.get_item().getName() %>', '<%= priceString %>', '<%= i.get_item().getDescription() %>', 'menuImages/<%= i.get_item().getImg() %>', '<%= i.get_item().getID() %>',)">
            <img src="menuImages/<%= i.get_item().getImg() %>" alt="Pizza">
            <div class="menu-grid-item-name"><%= i.get_item().getName() %></div>
            <div class="menu-grid-item-price"><%= priceString %></div>
        </div>

        <%  		}
                }
            	catch(Exception e){
                	e.printStackTrace();
	            }
        %>
        </div>   
            <%}else{%>
				<h1>Your cart is empty!</h1>
			<%}%>

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

                <input type="number" id="item_amount" min="1" placeholder="1"></input>
				<button id="modalButtonRemove" onclick="removeFromCart('<%=user.get_id()%>', document.getElementById('item_amount').value)">Remove Amount From Cart</button>
                <button id="modalButtonDelete" onclick="removeFromCart('<%=user.get_id()%>', 0)">Remove All From Cart</button>
            </div>
        </div>
    </div>

    <script>
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

        function removeFromCart(user, quan){
            var userID = user;
			var quantity = quan;
            var itemID = document.getElementById("itemModal").dataset.itemid;

            if (!itemID || !userID) {
                alert("Error: No item selected or user not logged in.");
                return;
            }
            
            var form = document.createElement('form');
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
    </script>
    
    </body>
</html>
