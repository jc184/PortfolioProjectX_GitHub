/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.database;

import com.example.portfolio.entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author james
 */
public class CustomerDB {

    public static void insertCustomer(Customer customer) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(customer);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }

    public static void updateCustomer(Customer customer) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(customer);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        } finally {
            em.close();
        }
    }

    public static Customer selectCustomer(String email) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT c FROM Customer c WHERE c.email = :email";
        TypedQuery<Customer> tq = em.createQuery(qString, Customer.class);
        tq.setParameter("email", email);
        Customer result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return result;
    }

    public static Customer selectCustomer(String emailAddress, String password) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT c FROM Customer c WHERE (c.emailaddress = :emailaddress) AND (c.loginpassword = :loginpassword)";
        TypedQuery<Customer> tq = em.createQuery(qString, Customer.class);
        tq.setParameter("emailaddress", emailAddress);
        tq.setParameter("loginpassword", password);
        Customer result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return result;
    }

    public static List<Customer> selectCustomers() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT c from Customer c";
        TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
        List<Customer> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

        return results;
    }

    public static boolean checkEmailExists(String email) {
        Customer c = selectCustomer(email);
        return c != null;
    }

    public static Customer addCustomer(String firstname, String lastname, String addressline1, String addressline2, String city, String postcode, String country, String company, String creditcardexpiry, String creditcardnumber, String creditcardtype, String emailaddress, String loginpassword) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        Customer customer = new Customer();
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        customer.setAddressline1(addressline1);
        customer.setAddressline2(addressline2);
        customer.setCity(city);
        customer.setPostcode(postcode);
        customer.setCountry(country);
        customer.setCreditcardexpiry(creditcardexpiry);
        customer.setCreditcardnumber(creditcardnumber);
        customer.setCreditcardtype(creditcardtype);
        customer.setEmailaddress(emailaddress);
        customer.setLoginpassword(loginpassword);

        em.persist(customer);
        return customer;
    }

    public static boolean checkCustomerExists(String emailAddress, String loginPasswd ) {
        for (Customer c : selectCustomers()) {
            if (c.getEmailaddress().equals(emailAddress) && (c.getLoginpassword().equals(loginPasswd))) {
                return true;
            }
        }
        return false;
    }

//    public static boolean loginCustomer(String emailAddress, String loginPasswd) {
//        for (Customer c : selectCustomers()) {
//            if (c.getEmailaddress().equals(emailAddress) && (c.getLoginpassword().equals(loginPasswd))) {
//                return true;
//            }
//        }
//        return false;
//    }

}
