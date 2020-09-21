

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String login = session.getAttribute("login").toString(); %>

<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" type="text/css" href="static/css/topPanel.css" >
    <link rel="stylesheet" type="text/css" href="static/css/main.css" >
    <link rel="stylesheet" type="text/css" href="static/css/chat.css" >

</head>
<body onload="init();">
<div class="topnav">
    <a class="active" href="chats">Chats</a>
    <a href="profile">Profile</a>
    <a href="createChat">Create chat</a>
    <form action="/my-app/logout" method="post" style="float:right">
        <button name="logout"  >Logout</button>
    </form>
</div>
<form action="/my-app/chatOptions" method="post" target="_blank" style="float:right">
    <input type="text" name="chatName" value=${chat.name} readonly />
    <button name="chatId" value=${chat.id} >Chat Options</button>
</form>

<div style="text-align:center;  vertical-align: middle;">
    <div id="main" class="main"  >
        <h>You are logged in as:</h>
        <input type="text" id="login" value=${login} readonly />

        <h>Chat Id:</h>
        <input type="button" value=${chat.id}
                   class="button" />


        <div id="scrolling-messages" class="scrolling-messages" >
            <c:forEach items="${messages}" var="message">
                <c:choose>
                    <c:when test= "${message.userName == login}">
                        <p style="background: #ebebe0;"><span>  ${message.userName}   :   ${message.content}  </span></p>
                    </c:when>
                    <c:otherwise>
                        <p><span>  ${message.userName}   :   ${message.content}  </span></p>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

            <div class="message-label">
                <span>Enter message:</span>
            </div>
            <div class="message-section">
                <div>
                    <textarea id="message"></textarea>
                </div>
                <div >
                    <input type="button" value="submit" onclick="sendMessage();"
                           class="primary" />
                </div>
            </div>
        </div>

</div>
<script id="chatScript" src="static/js/app.js" data-login=${login} data-chatId=${chat.id}>
</script>
</body>
</html>
