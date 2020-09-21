<%--
  Created by IntelliJ IDEA.
  User: savin
  Date: 10.08.2020
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="static/css/main.css" >
    <link rel="stylesheet" type="text/css" href="static/css/topPanel.css" >
    <link rel="stylesheet" type="text/css" href="static/css/input.css" >
    <link rel="stylesheet" type="text/css" href="static/css/button.css" >
    <title>Login</title>
</head>
<body background="static/css/chat.jpg">

<div class="topnav">

</div>
<div div id="input-page">
    <div class="input-container">

        <form method="post" action="">
            <h2 style="text-align:center">Enter your data</h2>
            <div style="text-align:center">
                <input type="text" required placeholder="login" name="login" ><br>
                <input type="password" required placeholder="password" name="password"><br><br>
                <button type="submit" class="primary">Login</button>
            </div>
        </form>

        <a href="/my-app/registration"> Or you can register here</a>
    </div>
</div>

</body>
</html>