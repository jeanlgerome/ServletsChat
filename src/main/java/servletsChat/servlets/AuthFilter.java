package servletsChat.servlets;

import servletsChat.DAO.Dao;
import servletsChat.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;


 class AuthFilter implements Filter {

    private static Logger log = Logger.getLogger(AuthFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        String path = ((HttpServletRequest) request).getRequestURI();

        if (path.endsWith("/registration") || path.endsWith(".css")) {
            filterChain.doFilter(request, response);
        }
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final HttpSession session = req.getSession();
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        if (AccessGranted(session, login, password)) {
            filterChain.doFilter(request, response);
        } else {
            req.getRequestDispatcher("/static/jsp/login.jsp").forward(req, res);
        }
    }

    private boolean AccessGranted(HttpSession session, String login, String password) {
        if (session.getAttribute("login") == null || session.getAttribute("password") == null) {

            if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
                User user = Dao.getUser(login, password);
                if (user != null) {
                    session.setAttribute("login", login);
                    session.setAttribute("password", password);
                    session.setAttribute("role", user.getRole());
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void destroy() {
    }


}
