/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import dataaccess.ItemDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author 843876
 */
public class InventoryServices {

    public List<Item> getAllItem(String email) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getAll(email); // problem??
        return items;
    }

    public Item getItem(int itemId) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemId);
        return item;
    }

    public List<Category> getAllCategory() throws Exception {
        CategoriesDB categoryDB = new CategoriesDB();
        List<Category> categorys = categoryDB.getAll();
        return categorys;
    }

    public Category getCategory(int categoryId) throws Exception {
        CategoriesDB categoryDB = new CategoriesDB();
        Category category = categoryDB.get(categoryId);
        return category;
    }

    public void delete(int itemId, String email) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = getItem(itemId);

        if (item.getOwner().getEmail().equals(email)) {
            itemDB.delete(item);
        } else {
            System.out.println("if statement didnt work");
        }

    }

    public void insert(int categoryId, String itemName, double price, String email) throws Exception {
        Item item = new Item(itemName, price);

        Category category = getCategory(categoryId);
        item.setCategory(category);

        AccountService as = new AccountService();
        User user = as.getUser(email);
        item.setOwner(user);

        ItemDB itemDB = new ItemDB();
        itemDB.insert(item);
    }

    public void update(int itemID, int categoryID, String itemName, double price, String email) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = getItem(itemID);

        if (item.getOwner().getEmail().equals(email)) {
            Category category = getCategory(categoryID);
            item.setCategory(category);
            item.setItemName(itemName);
            item.setPrice(price);
            itemDB.update(item);
        } else {
            System.out.println("if statement didnt work");
        }

    }

    public void insertCategory(String categoryName) throws Exception {
        Category category = new Category(categoryName);
        CategoriesDB categoriesDB = new CategoriesDB();
        categoriesDB.insert(category);
    }

    public void updateCategory(int categoryId, String categoryName)throws Exception {
        CategoriesDB categoriesDB = new CategoriesDB();
        Category category = categoriesDB.get(categoryId);
        category.setCategoryName(categoryName);
        categoriesDB.update(category);
    }

    public boolean isPosNumber(String number) {
        try {
            double isNumber = Double.parseDouble(number);
            if (isNumber > 0.0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("error, not a number");
            return false;

        }
    }
    
        public boolean isValidStringInput(String input) {
        try {
            if (input != null) {
                if (input.length() != 0) {
                    if (!input.equals("")) {
                        if (!input.trim().isEmpty()) {
                            return true;
                        }
                    }
                }
            }
            throw new Exception();
        } catch (Exception ex) {
            return false;
        }
    }

}
