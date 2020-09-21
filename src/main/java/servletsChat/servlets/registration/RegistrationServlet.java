package servletsChat.servlets.registration;

import servletsChat.DAO.Dao;
import servletsChat.model.Role;
import servletsChat.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/static/jsp/registration.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException{
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        User user = Dao.getUser(login);
        req.setAttribute("warning","Login is already taken");
        if(user!=null){
            req.getRequestDispatcher("/static/jsp/registration.jsp").forward(req, response);
        }else {
            user = new User(login, password, Role.USER);
            Dao.addUser(user);
            response.sendRedirect("/my-app/chats");
        }
    }
}