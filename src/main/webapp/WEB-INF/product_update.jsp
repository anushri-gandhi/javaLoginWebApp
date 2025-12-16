<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.entity.Product" %>
<%
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Product p = (Product) request.getAttribute("product");
    if (p == null) {
        response.sendRedirect("product_read");
        return;
    }
%>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
<h1>Update Product</h1>

<form method="post" action="updateProduct">
    <input type="hidden" name="id" value="<%= p.getProductId() %>">
    Name: <input type="text" name="name" value="<%= p.getName() %>" required><br><br>
    Description: <input type="text" name="description" value="<%= p.getDescription() %>" required><br><br>
    Color: <input type="text" name="color" value="<%= p.getColor() %>" required><br><br>
    Size: <input type="text" name="size" value="<%= p.getSize() %>" required><br><br>
    Price: <input type="number" step="0.01" name="price" value="<%= p.getPrice() %>" required><br><br>
    <button type="submit">Update Product</button>

<p><a href="${pageContext.request.contextPath}/product_read">Back to Product List</a></p>
</body>
</html>
