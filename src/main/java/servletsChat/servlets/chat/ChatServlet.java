package servletsChat.servlets.chat;

import servletsChat.DAO.Dao;
import servletsChat.model.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/open")
public class ChatServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/static/jsp/chatsList.jsp").forward(request, response);
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login =  session.getAttribute("login").toString();
        int chatId = Integer.parseInt( request.getParameter("chatId"));
        List<Message> messages = Dao.getMessages(chatId);
        request.setAttribute("messages", messages);
        request.setAttribute("chat", Dao.getChatById( chatId));
        request.setAttribute("login",login);
        request.getRequestDispatcher("/static/jsp/chat.jsp").forward(request, response);
    }


}
