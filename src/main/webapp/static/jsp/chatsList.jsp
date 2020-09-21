<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: savin
  Date: 10.08.2020
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chats List</title>
    <link rel="stylesheet" type="text/css" href="static/css/topPanel.css">
    <link rel="stylesheet" type="text/css" href="static/css/main.css">
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

<div style="text-align:center; vertical-align: middle; margin-top: 20px">
    <a href="/my-app/createChat">You Can Create Chat Here</a>
</div>


<form action="/my-app/chats" method="post" placeholder="Search" style="text-align:center; margin-top: 10px">
    <p><input type="search" name="searchBy">
        <input type="submit" value="search"></p>
</form>
<div style="text-align:center; vertical-align: middle; margin-top: 20px">

    <ol style="text-align:center; list-style:none; vertical-align: middle; margin-top: 20px">
        <h> My Chats List</h>
        <c:forEach items="${userChats}" var="chat">
            <tr>
                <li>
                    <form action="/my-app/open" method="post" target="_blank" style="display: inline-block;">
                        <button name="chatId" value=${chat.id}>Open ${chat.name}</button>
                    </form>
                    <form action="/my-app/leave" method="post" target="_blank" style="display: inline-block;">
                        <button name="chatId" value=${chat.id}>Leave chat</button>
                    </form>
                </li>
            </tr>
        </c:forEach>
        <h> All Chats List (available after search)</h>
        <c:choose>
            <c:when test="${empty allChats}">

            </c:when>
            <c:otherwise>
                <c:forEach items="${allChats}" var="chat">
                    <tr>
                        <li>
                            <form action="/my-app/join" method="post" target="_blank">

                                <button name="chatId" value=${chat.id}> Join  ${chat.name}  </button>
                            </form>

                        </li>

                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>

    </ol>
</div>


</body>
</html>
