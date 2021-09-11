/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import models.User;
import dataaccess.UserDB;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import models.Role;

/**
 *
 * @author 843876
 */
public class AccountService {

    RoleDB roleDB = new RoleDB();

    public User login(String email, String password) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (user.getActive() == true) {
                if (password.equals(user.getPassword())) {
                    return user;
                }
            }

        } catch (Exception e) {
            System.out.println("error here");
            //should return error: password does not match
        }

        return null;
    }

    public List<User> getAllUsers() {
        UserDB userDB = new UserDB();
        try {
            return userDB.getAll();
        } catch (Exception e) {
            //String message = "cannot get all users";
        }
        return null;
    }

    public User getUser(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }

    public void insert(String email, String firstName, String lastName, String password, boolean active) throws Exception {
        if (isValidStringInput(email)) {
            if (isValidStringInput(firstName)) {
                if (isValidStringInput(lastName)) {
                    if (isValidStringInput(password)) {
                        User user = new User(email, active, firstName, lastName, password);
                        Role role = new Role(2, "regular user");
                        user.setRole(role);
                        UserDB userdb = new UserDB();
                        userdb.insert(user);
                    }
                }
            }
        }
    }

    public void delete(String userEmail) throws Exception {

        UserDB userDB = new UserDB();
        User user = userDB.get(userEmail);
        //do check to make sure admin cannot be deleted
        try {
            if (user.getRole().getRoleId() == 2) {
                userDB.delete(user);
            }
        } catch (Exception e) {
            System.out.println("admin cannot be deleted");
        }

    }

    public void update(String email, String firstName, String lastName, String password) throws Exception {
        if (isValidStringInput(email)) {
            if (isValidStringInput(firstName)) {
                if (isValidStringInput(lastName)) {
                    if (isValidStringInput(password)) {
                        UserDB userDB = new UserDB();
                        User user = userDB.get(email);
                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        user.setPassword(password);
                        userDB.update(user);
                    }
                }
            }
        }

    }

    public void updateActive(String email, boolean status) throws Exception {

        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setActive(status);
        userDB.update(user);
    }

    public boolean isValidStringInput(String input) {
        Boolean tempBoo = false;
        try {
            if (input != null) {
                if (input.length() != 0) {
                    if (!input.equals("")) {
                        if (!input.trim().isEmpty()) {
                            tempBoo = true;
                            return tempBoo;
                        }else {return tempBoo;}
                    }else {return tempBoo;}
                }else {return tempBoo;}
            }else {return tempBoo;}
        } catch (Exception ex) {
            return false;
        }
    }
    
    public void SendPasswordResetEmail(String email, String path, String url) throws Exception{
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        String uuid = UUID.randomUUID().toString();
        user.setResetPasswordUuid(uuid);
        userDB.update(user);
                
        String to = email;
        String subject = "Nventory App, Reset Password";
        String template = path + "/emailtemplates/resetPassword.html";
        String link = url + "?uuid=" + uuid;
        
        HashMap<String, String> tags = new HashMap<>();
        tags.put("firstName", user.getFirstName());
        tags.put("lastName", user.getLastName());
        tags.put("email", email);
        tags.put("date", (new java.util.Date()).toString());
        tags.put("link", link);
        
        GmailService.sendMail(to, subject,template, tags);
    }
    
    public void sendAccountActiveEmail(String email, String path, String url) throws Exception{
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        String uuid = UUID.randomUUID().toString();
        user.setResetPasswordUuid(uuid); // using uuid to account verification as well since its set to null afterwards
        userDB.update(user);
        
        String to = email;
        String subject = "Nventory App, Account Verification";
        String template = path + "/emailtemplates/verifyAccount.html";
        String link = url + "?uuid=" + uuid;
        
        HashMap<String, String> tags = new HashMap<>();
        tags.put("firstName", user.getFirstName());
        tags.put("lastName", user.getLastName());
        tags.put("email", email);
        tags.put("date", (new java.util.Date()).toString());
        tags.put("link", link);
        
        GmailService.sendMail(to, subject,template, tags);
    }
    
        public void updateWithUUID(String email, String firstName, String lastName, String password, String UUID) throws Exception {
        if (isValidStringInput(email)) {
            if (isValidStringInput(firstName)) {
                if (isValidStringInput(lastName)) {
                    if (isValidStringInput(password)) {
                        UserDB userDB = new UserDB();
                        User user = userDB.get(email);
                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        user.setPassword(password);
                        user.setResetPasswordUuid(UUID);
                        userDB.update(user);
                    }
                }
            }
        }
    }
        
    
        public User getUserByUUID(String UUID){
            UserDB userDB = new UserDB();
            User user = userDB.getByUUID(UUID);
            return user;
        }
    

}
