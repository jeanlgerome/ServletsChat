<%--
  Created by IntelliJ IDEA.
  User: savin
  Date: 10.08.2020
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="static/css/main.css" >

</head>
<body background="static/css/chat.jpg">
<div id="input-page">
    <div class="input-container">
        <h1 class="title">Enter your data</h1><br>
        <c:choose>
            <c:when test="${empty warning}">

            </c:when>
            <c:otherwise>
                <h2 style="color:Tomato;">${warning}</h2>
            </c:otherwise>
        </c:choose>

        <form method="post" action="">
            <div class="form-group">
                <input type="text"  placeholder="Login" name="login"
                       autocomplete="off"  /><br>
                <input type="password"  placeholder="password" name="password"
                       autocomplete="off"/><br>
            </div>
            <div class="form-group">
                <button type="submit" class="primary">Registrate</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>