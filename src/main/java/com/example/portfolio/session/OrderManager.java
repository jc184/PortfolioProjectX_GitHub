/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.session;

import com.example.portfolio.cart.Cart;
import com.example.portfolio.cart.CartItem;
import com.example.portfolio.entities.Customer;
import com.example.portfolio.entities.CustomerOrder;
import com.example.portfolio.entities.OrderedProduct;
import com.example.portfolio.entities.OrderedProductPK;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author james
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderManager {

    @Resource
    private SessionContext context;

    @PersistenceContext(unitName = "PortfolioProjectX_war_1.0-SNAPSHOTPU4")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int placeOrder(Customer customer, Cart cart) {
        //em = getEm();
        //EntityTransaction etx = em.getTransaction();
        //etx.begin();
        try {

            CustomerOrder customerOrder = addOrder(customer, cart);
            System.out.println(customer.getEmailaddress());//for debugging
            System.out.println(cart);//for debugging
            addOrderedItems(customerOrder, cart);
            return customerOrder.getId();

        } catch (Exception e) {
            System.out.println(e);
            context.setRollbackOnly();
            return 0;
        }

    }

    private CustomerOrder addOrder(Customer customer, Cart cart) {
        //em = getEm();
        //EntityTransaction etx = em.getTransaction();
        // set up customer order
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomerCustomerid(customer);
        //customerOrder.setId(Integer.SIZE);//For Debugging
        Date orderdate = new Date();
        customerOrder.setOrderdate(orderdate);
        customerOrder.setAmount(BigDecimal.valueOf(cart.calculateOverallTotal(cart)));

        // create confirmation number
        Random random = new Random();
        int i = random.nextInt(999999999);

        customerOrder.setConfirmationNumber(i);
        //etx.begin();

        em.persist(customerOrder);

        return customerOrder;
    }

    private void addOrderedItems(CustomerOrder customerOrder, Cart cart) {

        em.flush();
        //em = getEm();
        //EntityTransaction etx = em.getTransaction();
        List<CartItem> items = cart.getItems();

        System.out.println(customerOrder);//for debugging
        // iterate through shopping cart and create OrderedProducts
        for (CartItem scItem : items) {

            int productCode = scItem.getProduct().getCode();

            // set up primary key object
            OrderedProductPK orderedProductPK = new OrderedProductPK();
            orderedProductPK.setCustomerOrderId(customerOrder.getId());
            //ADDED BY JC
            orderedProductPK.setProductCode(productCode);

            //orderedProductPK.setCustomerOrderId(productCode);
            // create ordered item using PK object
            OrderedProduct orderedItem = new OrderedProduct(orderedProductPK);

            // set quantity
            orderedItem.setQuantity(scItem.getQuantity());

//           etx.begin();
            em.persist(orderedItem);

        }
    }

}
