<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Sneaker Order</title>
    <style>
        .result { margin-top:15px; padding:10px; border:1px solid #ddd; background:#f9f9f9; }
        .error { color:#a00; }
    </style>
</head>
<body>
<h1>Sneaker Order</h1>

<form action="shoe-product" method="post">
    <label for="shoeName">Shoe Name:</label>
    <input type="text" id="shoeName" name="shoeName" value="Hoka"/><br><br>

    <label for="unitPrice">Unit Price ($):</label>
    <input type="number" id="unitPrice" name="unitPrice" step="0.01" value="100"/><br><br>

    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" min="1" value="1"/><br><br>

    <button type="submit">Calculate Total</button>
</form>

<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
<div class="result error"><%= error %></div>
<% } else if (request.getAttribute("total") != null) { %>
<div class="result">
    <p>Subtotal: $<%= request.getAttribute("subtotal") %></p>
    <p>Discount applied: <%= request.getAttribute("discountPercent") %>%
        ($<%= request.getAttribute("discountAmount") %>)</p>
    <p><strong>Total: $<%= request.getAttribute("total") %></strong></p>
</div>
<% } %>

<p><a href="index.jsp">Back to home</a></p>
</body>
</html>
