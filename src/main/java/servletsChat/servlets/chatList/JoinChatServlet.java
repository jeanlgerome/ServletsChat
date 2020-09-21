package servletsChat.servlets.chatList;

import servletsChat.DAO.Dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
@WebServlet("/join")
public class JoinChatServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/static/jsp/chatsList.jsp").forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = session.getAttribute("login").toString();
        int chatId=Integer.parseInt(request.getParameter("chatId"));

        if(!Dao.isUserInChat(chatId, login)){
            Dao.addUserToChat(chatId,login);
        }
        request.getRequestDispatcher("/static/jsp/chatsList.jsp").forward(request, response);
    }
}
