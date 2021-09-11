/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import javax.persistence.EntityManager;
import models.User;
import java.util.*;
import javax.persistence.EntityTransaction;
import models.Item;

/**
 *
 * @author 843876
 */
public class UserDB {

    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }

    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        //List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
        //  return users;
        try {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } finally {
            em.close();
        }
    }

    public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            //get delete all the users items
            ItemDB itemDB = new ItemDB();
            int amountOfItems = user.getItemList().size();
            for (int i = 0; i < amountOfItems; i++) {
                itemDB.delete(user.getItemList().get(i));
            }
            //delete the user
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public User getByUUID(String uuid) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            User user = em.createNamedQuery("User.findByResetPasswordUuid", User.class)
                    .setParameter("resetPasswordUuid", uuid).getSingleResult();
            return user;

        } catch (Exception ex) {
            System.out.print("error" + ex);
        } finally {
            em.close();
        }
        return null;
    }

}
