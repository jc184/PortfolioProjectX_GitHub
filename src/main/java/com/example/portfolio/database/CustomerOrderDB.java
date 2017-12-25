/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.database;

import com.example.portfolio.entities.CustomerOrder;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author james
 */
public class CustomerOrderDB {

    public static void insertCustomerOrder(CustomerOrder customerOrder) throws NotSupportedException, SystemException {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction utx = em.getTransaction();
        utx.begin();
        try {
            em.persist(customerOrder);
            utx.commit();
        } catch (Exception e) {
            System.out.println(e);
            utx.rollback();
        } finally {
            em.close();
        }
    }

    public static void updateCustomerOrder(CustomerOrder customerOrder) throws NotSupportedException, SystemException {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        UserTransaction utx = (UserTransaction) em.getTransaction();
        utx.begin();
        try {
            em.merge(customerOrder);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | RollbackException | SystemException e) {
            System.out.println(e);
            utx.rollback();
        } finally {
            em.close();
        }
    }

    public static CustomerOrder selectCustomerOrder(int orderId) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT co FROM customer_order co WHERE co.orderId = :orderId";
        TypedQuery<CustomerOrder> tq = em.createQuery(qString, CustomerOrder.class);
        tq.setParameter("orderId", orderId);
        CustomerOrder result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }

        return (CustomerOrder) result;
    }

    public static CustomerOrder selectCustomerOrderById(int orderId) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        return em.find(CustomerOrder.class, orderId);
    }

    public static List<CustomerOrder> selectCustomerOrders() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT co from customer_order co";
        TypedQuery<CustomerOrder> tq = em.createQuery(qString, CustomerOrder.class);
        List<CustomerOrder> results = null;
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
