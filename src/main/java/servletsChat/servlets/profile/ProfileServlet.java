package servletsChat.servlets.profile;

import servletsChat.DAO.Dao;
import servletsChat.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public  class ProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setAttribute("login",session.getAttribute("login"));
        request.setAttribute("password",session.getAttribute("password"));
        request.setAttribute("role",session.getAttribute("role"));

        request.setAttribute("chats", Dao.getChats());
        request.getRequestDispatcher("/static/jsp/userProfile.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = Dao.getUser(session.getAttribute("login").toString());
        if(login!=null&&password!=null&&!login.isEmpty()&&!password.isEmpty()){
            user.setLogin(login);
            user.setPassword(password);
            session.removeAttribute("login");
            session.removeAttribute("password");
            session.setAttribute("login",login);
            session.setAttribute("password",password);
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/chatsList");
        requestDispatcher.forward(request, response);
    }
}
