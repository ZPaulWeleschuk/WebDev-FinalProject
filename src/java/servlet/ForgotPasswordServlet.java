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
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();

        String uuid = (String) request.getParameter("uuid");
        if (uuid != null) {
            request.setAttribute("uuid", uuid);
            getServletContext().getRequestDispatcher("/WEB-INF/ResetPassword.jsp").forward(request, response);
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/forgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //also need to specifiy weather we are porparing link to are reseting password
        AccountService as = new AccountService();
        String email = request.getParameter("resetPasswordEmail");
        String path = getServletContext().getRealPath("/WEB-INF");
        String url = request.getRequestURL().toString();
        String uuid = (String) request.getParameter("uuid");
        String newPassword = request.getParameter("newPassword");

        if (uuid != null) {
            //reset password
            if (as.isValidStringInput(newPassword)) {
                try {
                    User user = as.getUserByUUID(uuid);
                    if (uuid.equals(user.getResetPasswordUuid())) {
                        as.updateWithUUID(user.getEmail(), user.getFirstName(), user.getLastName(), newPassword, null);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, "couldnt get email", ex);
                }
                request.setAttribute("message", "password changed, forwarded to login page");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
            request.setAttribute("message", "enter valid string");
        } else {
            try {
                // send an email with this email, create links etc, etc.
                as.SendPasswordResetEmail(email, path, url);
            } catch (Exception ex) {
                Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.setAttribute("message", "If your email matches with an account an email will be sent");
        getServletContext().getRequestDispatcher("/WEB-INF/forgotPassword.jsp").forward(request, response);

        //reset password
    }
}
