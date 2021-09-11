package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author 843876
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // code that is executed before the servlet
        HttpServletRequest httpRequest = (HttpServletRequest) request;// must cast as a filter is not a servlet
        HttpSession session = httpRequest.getSession();
        String email = (String) session.getAttribute("email");

        if (email == null) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;//cast
            httpResponse.sendRedirect("Login");
            return; // these returns are needed in order to 'jump' to the redirect
        }

        AccountService as = new AccountService();
        try {
            User user = as.getUser(email);
            if (!user.getActive()) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;//cast
                httpResponse.sendRedirect("Login");
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

        chain.doFilter(request, response); // execute the servlet or next filter

        // code that is executed after the servlet
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // dont need these for this application
    }

    @Override
    public void destroy() {
        // dont need these for this application
    }

}
