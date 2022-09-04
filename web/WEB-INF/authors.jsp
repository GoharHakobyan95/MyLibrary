<%@ page import="model.Author" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 9/3/2022
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors page</title>
</head>
<body>
<%
    List<Author> authors = (List<Author>) request.getAttribute("authors");
%>

<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>
        <th>action</th>
            <% for (Author author : authors) { %>
    <tr>
        <td><%=author.getId()%>
        </td>
        <td><%=author.getName()%>
        </td>
        <td><%=author.getSurname()%>
        </td>
        <td><%=author.getEmail()%>
        </td>
        <td><%=author.getAge()%>
        </td>
        <td><a href="/authors/remove?authorId=<%=author.getId()%>"> Remove</a> |
            <a href="/authors/edit?authorId=<%=author.getId()%>">Edit</a>
        </td>
    </tr>

    <% }
    %>
    </tr>
</table>
</body>
</html>
