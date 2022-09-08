<%@ page import="model.Author" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 9/3/2022
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<%
    List<Author> authors = (List<Author>) request.getAttribute("authors"); %>
<body>
<center>Please input Books Data.
    <form action="/books/add" method="post" enctype="multipart/form-data">
        <h2><input type="text" name="title" placeholder="Input  book's title"><br>
            <input type="text" name="description" placeholder="Input book's description"><br>
            <input type="number" name="price" placeholder="Input book's price"><br>
            <select name="authorId" title="select book's author">
                    <%for (Author author : authors) { %>
                <option value="<%=author.getId()%>">
                    <%=author.getName()
                    %>
                    <%=author.getSurname()%>
                    <%=author.getEmail()%>
                    (<%=author.getAge()%>)
                </option>
                    <% } %><br>
                <br><input type="file" name="profilePic"><br>
                <br><input type="submit" value="Add book"></h2>
        </select>
    </form>
</center>
</body>
</html>
