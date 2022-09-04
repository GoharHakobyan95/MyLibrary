<%@ page import="model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Author" %>
<%@ page import="manager.AuthorManager" %><%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 9/3/2022
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books page</title>
</head>
<body>
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    AuthorManager authorManager = new AuthorManager();
%>

<table border="1">
    <tr>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>author's data</th>
        <th>action</th>
            <% for (Book book : books) {
                 %>
    <tr>
        <td><%=book.getId()%>
        </td>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getDescription()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td>
            <% if (book.getAuthor() != null) { %>
            <%=book.getAuthor().getName()%>
            <%=book.getAuthor().getSurname()%>
            <%=book.getAuthor().getEmail()%>
            <%} else {%>
            <span style="color: red"> There is no Author.</span>
            <%}%>
        </td>
        <td>
            <a href="/books/remove?bookId=<%=book.getId()%>">Remove</a> |
            <a href="/books/edit?bookId=<%=book.getId()%>">Edit</a>
        </td>
    </tr>
    <% }
    %>
    </tr>
</table>
</body>
</html>
