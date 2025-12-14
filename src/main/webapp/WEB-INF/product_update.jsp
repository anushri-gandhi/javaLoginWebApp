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

<form method="post" action="product_update">
    <input type="hidden" name="id" value="<%= p.getId() %>">
    Name: <input type="text" name="name" value="<%= p.getName() %>" required><br><br>
    Description: <input type="text" name="description" value="<%= p.getDescription() %>"><br><br>
    Color: <input type="text" name="color" value="<%= p.getColor() %>"><br><br>
    Size: <input type="text" name="size" value="<%= p.getSize() %>"><br><br>
    Price: <input type="number" step="0.01" name="price" value="<%= p.getPrice() %>" required><br><br>
    <button type="submit">Update Product</button>
</form>

<p><a href="${pageContext.request.contextPath}/product_read">Back to Product List</a></p>
</body>
</html>
