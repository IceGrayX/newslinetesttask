<%--
  Created by IntelliJ IDEA.
  User: Валерий
  Date: 17.03.2017
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>${param.action == 'create' ? 'Create news' : 'Edit news'}</h2>
    <hr>
    <jsp:useBean id="news" type="com.valrock.newsline.model.News" scope="request"/>
    <form method="post" action="newsline" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${news.id}">
        <dl>
            <dt>Header:</dt>
            <dd><input type="text" value="${news.header}" size="40" name="header"></dd>
        </dl>
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" value="${news.dateTime}" name="dateTime"></dd>
        </dl>
        <dl>
            <dt>Text:</dt>
            <dd><input type="text" value="${news.textnews}" size="100" name="textnews"></dd>
        </dl>
        <dl>
            <dt>Image:</dt>
            <dd><input type="file"  name="image" value="${news.imageName}"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
