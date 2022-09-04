<%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 9/3/2022
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Author</title>
</head>
<body>
<center>Please input Authors Data.
    <form action="/authors/add" method="post">
        <input type="text" name="name" placeholder="Input  author's name"><br>
        <input type="text" name="surname" placeholder="Input author's surname"><br>
        <input type="email" name="email" placeholder="Input author's email"><br>
        <label for="tentacles">Please input author's age:</label>
        <input type="number" id="tentacles" name="age"
               min="0" max="100"><br>
        <input type="submit" value="Add">
</center>
</form>

</body>
</html>
