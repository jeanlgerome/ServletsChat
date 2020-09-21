package servletsChat.servlets.chat;

import servletsChat.DAO.Dao;
import servletsChat.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/chatOptions")
public  class ChatOptions extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/static/jsp/chatsList.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int chatId = Integer.parseInt( request.getParameter("chatId")  );
        String chatName = request.getParameter("chatName") ;
        List<User> users=Dao.getUsersByChat(chatId);
        request.setAttribute("users",users);
        request.setAttribute("chatName",chatName);
        request.setAttribute("chatId",chatId);
        request.getRequestDispatcher("/static/jsp/chatOptions.jsp").forward(request, response);

    }
}

