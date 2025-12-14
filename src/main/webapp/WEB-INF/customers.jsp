<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.entity.Customer" %>

<%
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Customer List</title>
</head>
<body>

<h1>Customer List</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <%
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        if (customers != null) {
            for (Customer c : customers) {
    %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getName() %></td>
        <td><%= c.getEmail() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="3">No customers found.</td>
    </tr>
    <%
        }
    %>
</table>

<h2>Add New Customer</h2>

<form method="post" action="customers">
    Name: <input type="text" name="name" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    <button type="submit">Save Customer</button>
</form>

<p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>

</body>
</html>
