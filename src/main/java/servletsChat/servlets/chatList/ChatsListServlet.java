package servletsChat.servlets.chatList;

import servletsChat.DAO.Dao;
import servletsChat.model.Chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/chats")
public class ChatsListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        String login =session.getAttribute("login").toString();
        List<Chat> userChats=Dao.getChatsByUser(login);
        request.setAttribute("userChats",userChats );
        request.getRequestDispatcher("/static/jsp/chatsList.jsp").forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session=req.getSession();
        String login =session.getAttribute("login").toString();
        String chatName = req.getParameter("searchBy");
        chatName = Objects.isNull(chatName) ? "" : chatName;
        List<Chat> userChats = Dao.getChatsByChatNameAndUSer(chatName,login);
        List<Chat> allChats = Dao.getChatsByChatName(chatName);

        req.setAttribute("userChats",userChats );
        req.setAttribute("allChats",allChats );
        req.getRequestDispatcher("/static/jsp/chatsList.jsp").forward(req, response);


    }
}
