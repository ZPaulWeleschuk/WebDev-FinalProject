/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Item;
import models.User;

/**
 *
 * @author 843876
 */
public class ItemDB {

    public List<Item> getAll(String email) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user = em.find(User.class, email);

            return user.getItemList();// I think the problem is that the itemList is not getting updated for some reason
            //should we make a query where we get all items WHERE owner = email???

            /*List<Item> userItems = new ArrayList<Item>();
            List<Item> allItems = em.createNamedQuery("Item.findAll", Item.class).getResultList();
            for(int i = 0; i < allItems.size(); i++){
               if (allItems.get(i).getOwner().equals(email)){
                   userItems.add(allItems.get(i));
               }
            }
            return userItems;*/
        } finally {
            em.close();
        }
    }

    public void insert(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            User user = item.getOwner();
            user.getItemList().add(item);
            trans.begin();
            em.merge(user);// important line, must update the user db in order to keep things up to date
            em.persist(item);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(Item item) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            User user = item.getOwner();
            user.getItemList().remove(item);
            trans.begin();
            em.merge(user);// important line, must update the user db in order to keep things up to date
            em.remove(em.merge(item));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(Item item) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            //User user = item.getOwner();
            //user.getItemList().remove(item);
            //user.getItemList().add(item);
            trans.begin();
            em.merge(item);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public Item get(int itemId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Item item = em.find(Item.class, itemId);
            return item;
        } finally {
            em.close();
        }
    }
}
