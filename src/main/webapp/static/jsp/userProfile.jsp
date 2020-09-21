<%--
  Created by IntelliJ IDEA.
  User: savin
  Date: 12.08.2020
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="static/css/main.css" >
    <link rel="stylesheet" type="text/css" href="static/css/topPanel.css" >

    <title>Title</title>
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

<form name="userData" style="text-align:center; vertical-align: middle; margin-top: 50px"  method="post" action="">
    Login: <input type="text" style="margin-top: 5px"name="login" value=${login} /><br />
    Password: <input type="text" style="margin-top: 5px" name="password" value=${password} /><br />
    Role: <input type="text" style="margin-top: 5px" name="role" value=${role} readonly/><br />

    <button type="submit" style="margin-top: 5px" class="primary">Save</button>
</form>
</body>
</html>
