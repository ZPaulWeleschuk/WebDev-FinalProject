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
import models.Category;
import services.InventoryServices;

/**
 *
 * @author 843876
 */
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InventoryServices is = new InventoryServices();
        try {
            List<Category> categories = is.getAllCategory();
            request.setAttribute("categories", categories);
        } catch (Exception ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String action = request.getParameter("action");
        if (action != null && action.equals("loadEditCategory")) {
            int catId = Integer.parseInt(request.getParameter("categoryId"));
            try {
                Category category = is.getCategory(catId);
                request.setAttribute("selectedCategory", category);
            } catch (Exception ex) {
                Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InventoryServices is = new InventoryServices();
        String action = request.getParameter("action");
        String categoryName = request.getParameter("categoryName");
        
        try{
            switch(action){
                case "addCategory":
                    is.isValidStringInput(categoryName);
                    is.insertCategory(categoryName);
                   break;     
                case "editCategory":  
                    int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                    is.updateCategory(categoryId,categoryName);
                    break;
            }
        }catch (Exception ex){
            request.setAttribute("message", "error in action");
        }
        
        List<Category> categories;
        try {
            categories = is.getAllCategory();
            request.setAttribute("categories", categories);
        } catch (Exception ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
   
    }

}
