<%@ page import="model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %><%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 9/4/2022
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>
<% Book book = (Book) request.getAttribute("book");
    List<Author> authors = (List<Author>) request.getAttribute("authors");
%>
<center>Please input Books Data.
    <form action="/books/edit" method="post">
        <input type="hidden" name="bookId" value="<%=book.getId()%>">
        <h2><input type="text" name="title" value="<%=book.getTitle()%>"/><br>
            <input type="text" name="description" value="<%=book.getDescription()%>"/><br>
            <input type="number" name="price" value="<%=book.getPrice()%>"/><br>
            <select name="authorId">
                    <% for (Author author : authors) {
                        if(author.equals(book.getAuthor())) {
                     %>
                <option selected value="<%=author.getId()%>">
                    <%=author.getName()
                    %>
                    <%=author.getSurname()%>
                    <%=author.getEmail()%>
                    (<%=author.getAge()%>)
                </option>
                    <% } else { %>
                <option value="<%=author.getId()%>">
                        <%=author.getName()
                    %>
                        <%=author.getSurname()%>
                        <%=author.getEmail()%>
                    (<%=author.getAge()%>)
                    <br><input type="submit" value="Update"></h2>
            <% } %>
            <%} %>
</center>
</select>
</form>
</body>
</html>
</html>
