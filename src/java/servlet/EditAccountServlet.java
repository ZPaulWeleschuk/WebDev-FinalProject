/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
public class EditAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        User user;
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        try {
            user = as.getUser(email);
            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getLastName());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("password", user.getPassword());
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, "error displaying user info in editAccounPage", ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/EditAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("email");
        String newEmail;
        String firstName;
        String lastName;
        String password;
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "update":
                    newEmail = request.getParameter("newEmail");
                    if (email.equals(newEmail)) {
                        firstName = request.getParameter("firstName");
                        lastName = request.getParameter("lastName");
                        password = request.getParameter("password");
                        as.update(newEmail, firstName, lastName, password);
                        request.setAttribute("message", "Account edited successfully");
                        request.setAttribute("firstName", firstName);
                        request.setAttribute("lastName", lastName);
                        getServletContext().getRequestDispatcher("/WEB-INF/EditAccount.jsp").forward(request, response);

                    } else {
                        request.setAttribute("message", "email names not working");
                        throw new Exception();
                    }
                    break;
                case "deactivate":
                    as.updateActive(email, false);
                    request.setAttribute("message", "Account deactivated");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                    break;

            }
        } catch (Exception ex) {
            request.setAttribute("message", "error when editing account");
            Logger.getLogger(AdminServlet.class.getName()).log(Level.WARNING, "error when editing account", ex);
            getServletContext().getRequestDispatcher("/WEB-INF/EditAccount.jsp").forward(request, response);
        }

    }
}
