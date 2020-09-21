package servletsChat.servlets.chatList;

import servletsChat.DAO.Dao;
import servletsChat.model.Chat;
import servletsChat.model.Message;
import servletsChat.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@WebServlet("/createChat")
public class CreateChatServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/static/jsp/createChat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chatName = request.getParameter("chatName");
        HttpSession session=request.getSession();
        String login =session.getAttribute("login").toString();
        User user = Dao.getUser(login);
        Set users = new HashSet();
        users.add(user);
        List<Message> messages = new ArrayList<>();
        Chat chat = new Chat();
        chat.setUsers(users);
        chat.setName(chatName);
        chat.setMessages(messages);
        Dao.addChat(chat);
        response.sendRedirect("/my-app/chats");

    }
}
