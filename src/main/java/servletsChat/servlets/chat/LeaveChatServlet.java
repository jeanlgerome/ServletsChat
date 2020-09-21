package servletsChat.servlets.chat;

import servletsChat.DAO.Dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/leave")
public class LeaveChatServlet extends HttpServlet {
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
            Dao.deleteUserFromChat(chatId,login);
            response.sendRedirect("/my-app/chats");
        }

}

