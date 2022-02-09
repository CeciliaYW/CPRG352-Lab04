package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("userObject") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        } else {
            String logout = request.getParameter("logout");
          
            if (logout != null) {
                request.setAttribute("logout_message", "You have been successfully logged out.");
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            } else {
                // if do not click on log out, redirect to home page
                response.sendRedirect("home");
                return;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String userName = request.getParameter("u_name");
        String userPassword = request.getParameter("u_password");

        User u1 = new User(userName, userPassword);
        AccountService validateMethod = new AccountService();
        
        if (userName != "" && userPassword != "") {
            if (validateMethod.login(userName, userPassword) != null) {
                session.setAttribute("userObject", u1);
                response.sendRedirect("home");
                return;
            } else {
                request.setAttribute("error_message", "Incorrect input. Please try again.");
                request.setAttribute("username", userName);
                request.setAttribute("userpassword", userPassword);

                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }

        } else {
            request.setAttribute("error_message", "Invalid input. Please try again.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
    }
}