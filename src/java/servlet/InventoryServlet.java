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
import models.Category;
import models.Item;
import models.User;
import services.AccountService;
import services.InventoryServices;

/**
 *
 * @author 843876
 */
public class InventoryServlet extends HttpServlet {

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
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        InventoryServices is = new InventoryServices();
        try {
            //HttpSession session = request.getSession();
            //String email = (String) session.getAttribute("email");
            List<Item> items = is.getAllItem(email);
            request.setAttribute("items", items);
            List<Category> categorys = is.getAllCategory();
            request.setAttribute("categorys", categorys);

        } catch (Exception e) {
            request.setAttribute("message", "error");
        }

        String action = request.getParameter("itemAction");
        if (action != null && action.equals("itemEdit")) {
            try {
                int itemID = Integer.parseInt(request.getParameter("itemID"));
                Item item = is.getItem(itemID);
                request.setAttribute("selectedItem", item);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "error with item get", ex);

            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        InventoryServices is = new InventoryServices();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        String itemId;
        int itemIdInt;
        String category;
        int categoryId;
        String itemName;
        String price;
        double doubPrice;

        String itemAction = request.getParameter("itemAction");
        try {
            switch (itemAction) {
                case "itemDelete":
                    itemId = request.getParameter("itemID");
                    itemIdInt = Integer.parseInt(itemId);
                    is.delete(itemIdInt, email);
                    break;
                case "itemAdd":
                    category = request.getParameter("category");
                    categoryId = Integer.parseInt(category);
                    itemName = request.getParameter("itemName");
                    price = request.getParameter("price");
                    if (is.isPosNumber(price)) {
                        doubPrice = Double.parseDouble(price);
                        is.insert(categoryId, itemName, doubPrice, email);
                    }
                    break;
                case "itemEdit":
                    itemId = request.getParameter("itemID");
                    itemIdInt = Integer.parseInt(itemId);
                    category = request.getParameter("category");
                    categoryId = Integer.parseInt(category);
                    itemName = request.getParameter("itemName");
                    price = request.getParameter("price");
                    if (is.isPosNumber(price)) {
                        doubPrice = Double.parseDouble(price);
                        is.update(itemIdInt, categoryId, itemName, doubPrice, email);
                    }
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("message", "error in itemAction");
        }

        try {

            User user = as.getUser(email);
            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getLastName());
            List<Item> items = is.getAllItem(email);
            request.setAttribute("items", items);
            List<Category> categorys = is.getAllCategory();
            request.setAttribute("categorys", categorys);
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
