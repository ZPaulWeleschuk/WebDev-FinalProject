/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
public class AdminFilter implements Filter {
   

    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
    HttpServletRequest httpRequest = (HttpServletRequest)request;// must cast as a filter is not a servlet
        HttpSession session = httpRequest.getSession();
        
        String email = (String)session.getAttribute("email");
        String password = (String)session.getAttribute("password");
        AccountService as = new AccountService();
        User user = as.login(email, password);
        if (user.getRole().getRoleId() == 1 ||  user.getRole().getRoleId() == 3){
            //HttpServletResponse httpResponse = (HttpServletResponse) response;//cast
            //httpResponse.sendRedirect("admin");
            //return;// returns are needed in order to 'jump' to redirect, however this pushes us back into the previous filter
        chain.doFilter(request, response);
        
        }
        else{
            HttpServletResponse httpResponse = (HttpServletResponse) response;//cast
            httpResponse.sendRedirect("Inventory");
            return;
        }
    }



 
    @Override
    public void destroy() {        
    }

    @Override    
    public void init(FilterConfig filterConfig) {        
       
    }


}
