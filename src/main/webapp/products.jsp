<%@ page import="java.util.List" %>
<%@ page import="com.example.entity.Product" %>

<!-- Back to Home link -->
<p><a href="index.jsp">Back to Home</a></p>

<h2>Add Product</h2>

<form method="post" action="${pageContext.request.contextPath}/products">
    Name: <input name="name" required><br>
    Description: <input name="description" required><br>
    Color: <input name="color" required><br>
    Size: <input name="size" required><br>
    Price: <input name="price" type="number" step="0.01" required><br>
    <button type="submit">Save Product</button>
</form>

<hr>

<h2>Products</h2>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Color</th>
        <th>Size</th>
        <th>Price</th>
    </tr>

    <%
        List<Product> products = (List<Product>) request.getAttribute("products");
        if (products != null) {
            for (Product p : products) {
    %>
    <tr>
        <td><%= p.getName() %></td>
        <td><%= p.getDescription() %></td>
        <td><%= p.getColor() %></td>
        <td><%= p.getSize() %></td>
        <td>$<%= p.getPrice() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
