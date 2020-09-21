<%--
  Created by IntelliJ IDEA.
  User: savin
  Date: 19.08.2020
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="static/css/topPanel.css" >
    <link rel="stylesheet" type="text/css" href="static/css/main.css" >
    <link rel="stylesheet" type="text/css" href="static/css/chat.css" >
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

<div>
    <form name="addUser" method="post" action="">
        Add User: <input type="text" name="login"  /><br />
        <button type="submit" class="primary" name="chatId" value=${chatId}>Add user</button>
    </form>
    <form name="leaveChat" method="post" action="/my-app/leave">
        <button type="submit" class="primary" name="chatId" value=${chatId}>Leave Chat</button>
    </form>

</div>
</body>
</html>
