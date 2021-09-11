/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author 843876
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        List<User> users = as.getAllUsers();

        request.setAttribute("users", users);

        String action = request.getParameter("action");
        if (action != null && action.equals("updateView")) {
            try {
                String userEmail = request.getParameter("userEmail");
                User user = as.getUser(userEmail);
                request.setAttribute("selectedUser", user);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("email");// this is getting the admin login not the new emial
        String userEmail;
        String newEmail;
        String firstName;
        String lastName;
        String password;
        Boolean active;
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "create":
                    newEmail = request.getParameter("newEmail");
                    firstName = request.getParameter("firstName");
                    lastName = request.getParameter("lastName");
                    password = request.getParameter("password");
                    as.insert(newEmail, firstName, lastName, password, true);
                    break;
                case "delete":
                    userEmail = request.getParameter("userEmail");
                    as.delete(userEmail);
                    break;
                case "update":
                    newEmail = request.getParameter("newEmail");
                    firstName = request.getParameter("firstName");
                    lastName = request.getParameter("lastName");
                    password = request.getParameter("password");
                    //User user = as.getUser(newEmail);
                    as.update(newEmail, firstName, lastName, password);
                    break;
                case "activate":
                    userEmail = request.getParameter("userEmail");
                    as.updateActive(userEmail, true);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("message", "error in action");
        }
        List<User> users = as.getAllUsers();
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

    }

}
