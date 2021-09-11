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
import models.User;
import services.AccountService;

/**
 *
 * @author 843876
 */
public class CreateAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();

        String uuid = (String) request.getParameter("uuid");
        if (uuid != null) {
            request.setAttribute("uuid", uuid);
            getServletContext().getRequestDispatcher("/WEB-INF/verifyAccount.jsp").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/createAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        String newEmail = request.getParameter("newEmail");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String uuid = (String) request.getParameter("uuid");
        String active = (String) request.getParameter("active");

        if ( active != null && active.equals("true")) {
            if (uuid != null) {
                User user = as.getUserByUUID(uuid);
                if (uuid.equals(user.getResetPasswordUuid())) {
                    try {
                        as.updateActive(user.getEmail(), true);
                        as.updateWithUUID(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword(), null);//set uuid to null
                        request.setAttribute("message", "account activated, forwarded to login page");
                        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

                    } catch (Exception ex) {
                        Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            try {
                String path = getServletContext().getRealPath("/WEB-INF");
                String url = request.getRequestURL().toString();
                as.insert(newEmail, firstName, lastName, password, false);
                as.sendAccountActiveEmail(newEmail, path, url);
                request.setAttribute("message", "Account created successfully, a verification link will be sent to email");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("message", "error when creating account");
                Logger.getLogger(AdminServlet.class.getName()).log(Level.WARNING, "error when creating account", ex);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/createAccount.jsp").forward(request, response);
        }
    }

}
