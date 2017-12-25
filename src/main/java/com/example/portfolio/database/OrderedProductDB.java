/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.database;

import com.example.portfolio.entities.OrderedProduct;
import com.example.portfolio.entities.OrderedProductPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author james
 */
public class OrderedProductDB {

    public static OrderedProduct selectOrderedProduct(OrderedProductPK orderedProductPK) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT op FROM ordered_product op WHERE (op.customer_order_id = :customerOrderId) AND (op.product_code = :productCode)";
        TypedQuery<OrderedProduct> tq = em.createQuery(qString, OrderedProduct.class);
        tq.setParameter("orderedProductPK", orderedProductPK);
        OrderedProduct result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return (OrderedProduct) result;
    }

    public static OrderedProduct selectOrderedProductById(OrderedProductPK orderedProductPK) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        return em.find(OrderedProduct.class, orderedProductPK);
    }

    public static List<OrderedProduct> selectOrderedProducts() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT op from ordered_product op";
        TypedQuery<OrderedProduct> tq = em.createQuery(qString, OrderedProduct.class);
        List<OrderedProduct> results = null;
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
