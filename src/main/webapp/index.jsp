<%
    // Ensure user is logged in
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>

<h2>Welcome, <%= session.getAttribute("user") %>!</h2>
<p>You are now logged in.</p>

<p>
    <a href="${pageContext.request.contextPath}/shoe-product">Order Sneakers</a>
</p>

<p>
    <a href="${pageContext.request.contextPath}/customers">View Customers</a>
</p>

<p>
    <a href="${pageContext.request.contextPath}/products">View Products</a>
</p>

<p>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</p>

</body>
</html>
