/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;
import models.Item;

/**
 *
 * @author 843876
 */
public class CategoriesDB {
    public List<Category> getAll() throws Exception{
              EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{  
            return em.createNamedQuery("Category.findAll", Category.class).getResultList();
           
        }finally{
            em.close();
        }
    }
    
    public Category get(int categoryId){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            Category category = em.find(Category.class,categoryId);
            return category;
        }finally{
            em.close();
        }
    }
    
    public void insert(Category category) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.persist(category);
            trans.commit();
        }catch (Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
    
    public void update(Category category) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.merge(category);
            trans.commit();
        }catch (Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
    
}
