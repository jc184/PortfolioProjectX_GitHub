/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.database;

import com.example.portfolio.entities.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author james
 */
public class CategoryDB {

    public static Category selectCategory(int id) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT c FROM Category c WHERE c.id = :id";
        TypedQuery<Category> tq = em.createQuery(qString, Category.class);
        tq.setParameter("id", id);
        Category result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return (Category) result;
    }

    public static Category selectCategoryById(int id) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        return em.find(Category.class, id);
    }

    public static List<Category> selectCategories() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT c from Category c";
        TypedQuery<Category> tq = em.createQuery(qString, Category.class);
        List<Category> results = null;
        try {
            results = tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return results;
    }
}
