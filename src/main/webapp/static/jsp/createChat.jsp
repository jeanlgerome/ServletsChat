<%--
  Created by IntelliJ IDEA.
  User: savin
  Date: 05.09.2020
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Chat</title>
    <link rel="stylesheet" type="text/css" href="static/css/main.css" >
    <link rel="stylesheet" type="text/css" href="static/css/topPanel.css" >
    <link rel="stylesheet" type="text/css" href="static/css/input.css" >
    <link rel="stylesheet" type="text/css" href="static/css/button.css" >
    <link rel="stylesheet" type="text/css" href="static/css/center.css" >
</head>
<body>
<div class="topnav">
    <a class="active" href="chats">Chats</a>
    <a href="profile">Profile</a>
    <a href="createChat">Create chat</a>
    <form action="/my-app/logout" method="post" style="float:right">
        <button name="logout"  >Logout</button>
    </form>
</div>

<div class="center">
    <form name="chatData" method="post" action="">
        Chat Name: <input type="text" name="chatName"  /><br />
        <button type="submit" class="primary">Create</button>
    </form>
</div>

</body>
</html>
