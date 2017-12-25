/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.database;

import com.example.portfolio.entities.Category;
import com.example.portfolio.entities.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author james
 */
public class ProductDB {

    public static Product selectProduct(int code) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT p FROM Product p WHERE p.code = :code";
        TypedQuery<Product> tq = em.createQuery(qString, Product.class);
        tq.setParameter("code", code);
        Product result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }

        return (Product) result;
    }

    public static Product selectProductById(int code) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        return em.find(Product.class, code);
    }

    public static List<Product> selectProducts() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT p from Product p";
        TypedQuery<Product> tq = em.createQuery(qString, Product.class);
        List<Product> results = null;
        try {
            results = tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return results;
    }

    public static List<Product> selectProductsByCategory(Category id) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT p from Product p WHERE p.category = :id";
        TypedQuery<Product> tq = em.createQuery(qString, Product.class);
        tq.setParameter("id", id);
        List<Product> results = null;
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
