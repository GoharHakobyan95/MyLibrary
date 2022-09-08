<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 9/3/2022
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Library</title>
</head>
<body>
<% User user = (User) session.getAttribute("user"); %>
<div style="width: 1000px; margin: 0 auto; ">
    <div>
        <img src="/image/library.jpg" width="1000px" ; height="250px">
    </div>
    <div>
        <br>
        <center style="color: #ae34ea">
            <h3> Hello from Library!!! </h3>
            <% if (user != null) {
            %>
            <h3><a href="/authors/add">Add author</a><br></h3>
            <h3><a href="/books/add">Add book</a><br></h3>
            <a href="/books">Show All books</a><br>
            <a href="/authors">Show All authors</a><br>

            <a href="/logout">Logout</a><br></h3>
            <% } else { %>
            <a href="/books">Show All books</a><br>
            <a href="/users/register">Register</a><br>
            <a href="/login">Login</a>
            <%}%>
        </center>
        </br>
    </div>
</div>
</body>
</html>
