<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Валерий
  Date: 16.03.2017
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News line</title>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>News line</h2>
    <a href="newsline?action=create">Add News</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="2">
        <thead>
        <tr>
            <th>Image</th>
            <th>Header</th>
            <th>Text</th>
            <th>Date</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${newsline}" var="news">
            <jsp:useBean id="news" scope="page" type="com.valrock.newsline.model.News"/>
            <tr class="${news}">
                <td><img src="upload/${news.imageName}"/></td>
                <td>${news.header}</td>
                <td>${news.textnews}</td>
                <td>${news.dateTime.toLocalDate()} ${news.dateTime.toLocalTime()}</td>
                <td><a href="newsline?action=update&id=${news.id}">Update</a></td>
                <td><a href="newsline?action=delete&id=${news.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
