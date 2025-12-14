<%
    // Session protection
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    // Retrieve previous values if available
    String shoeName = request.getAttribute("shoeName") != null ? (String) request.getAttribute("shoeName") : "Hoka";
    String unitPrice = request.getAttribute("unitPrice") != null ? request.getAttribute("unitPrice").toString() : "100";
    String quantity = request.getAttribute("quantity") != null ? request.getAttribute("quantity").toString() : "1";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Sneaker Order</title>

    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        .product-box { display: flex; gap: 20px; margin-bottom: 20px; }
        .product-box img { width: 250px; border: 1px solid #ccc; border-radius: 8px; }
        .product-info { max-width: 400px; }
        .result { margin-top: 15px; padding: 10px; border: 1px solid #ddd; background: #f9f9f9; }
        .error { color: #a00; font-weight: bold; }
    </style>
</head>

<body>

<h1>Sneaker Order</h1>

<!-- PRODUCT DISPLAY -->
<div class="product-box">
    <img src="${pageContext.request.contextPath}/images/SneakerImage.png" alt="Sneaker">
    <div class="product-info">
        <h2>Running Sneaker</h2>
        <p>Lightweight, cushioned running shoe designed for comfort and long-distance support.</p>
        <p><strong>Base Price:</strong> $100.00</p>
    </div>
</div>

<!-- ORDER FORM -->
<form action="${pageContext.request.contextPath}/shoe-product" method="post">

    <label for="shoeName">Shoe Name:</label>
    <input type="text" id="shoeName" name="shoeName" value="<%= shoeName %>"><br><br>

    <label for="unitPrice">Unit Price ($):</label>
    <input type="number" id="unitPrice" name="unitPrice" step="0.01" value="<%= unitPrice %>"><br><br>

    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" min="1" value="<%= quantity %>"><br><br>

    <button type="submit">Calculate Total</button>
</form>

<!-- ERROR OR RESULT DISPLAY -->
<%
    String error = (String) request.getAttribute("error");

    if (error != null) {
%>
<div class="result error">
    <%= error %>
</div>
<%
} else if (request.getAttribute("total") != null) {
%>
<div class="result">
    <p><strong>Shoe Name:</strong> <%= shoeName %></p>
    <p><strong>Subtotal:</strong> $<%= request.getAttribute("subtotal") %></p>
    <p>
        <strong>Discount applied:</strong>
        <%= request.getAttribute("discountPercent") %>%
        ($<%= request.getAttribute("discountAmount") %>)
    </p>
    <p><strong>Total:</strong> $<%= request.getAttribute("total") %></p>
</div>
<%
    }
%>

<br>
<a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a>

</body>
</html>
