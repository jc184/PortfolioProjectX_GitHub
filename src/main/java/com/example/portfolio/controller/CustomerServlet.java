/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.controller;

import com.example.portfolio.cart.Cart;
import com.example.portfolio.database.CustomerDB;
import com.example.portfolio.session.OrderManager;
import com.example.portfolio.entities.Customer;
import com.example.portfolio.util.MailUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author james
 */
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private OrderManager orderManager;


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/checkoutpage.jsp";
        String email = request.getParameter("exampleInputEmail1");
        String password = request.getParameter("exampleInputPassword1");
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
//        boolean authorized = Boolean.parseBoolean(request.getParameter("authorized"));
        if (submit != null && submit.length() > 0) {
            if (submit.equals("login")) {
                if (CustomerDB.checkCustomerExists(email, password) == true) {
                    Customer customer = CustomerDB.selectCustomer(email, password);
                    session.setAttribute("customer", customer);
                    Cart cart = (Cart) session.getAttribute("cart");

                    //session.setAttribute("cart", cart);
                    double overallTotal = (double) session.getAttribute("overallTotal");
                    String totalSum = cart.getOverallTotalCurrencyFormat(overallTotal);
                    request.setAttribute("overallTotal", overallTotal);
                    request.setAttribute("totalSum", totalSum);
                    session.setAttribute("overallTotal", overallTotal);

                    url = "/placeorder.jsp";
//                    if (authorized) {
//                        sendEmailConfirmation(request, response, customer.getEmailaddress());
//                    }
                } else {
                    String message = "Your login details are wrong. Please re-enter or register to continue.";
                    request.setAttribute("message", message);
                    url = "/checkoutpage.jsp";
                }
            } else if (submit.equals("continue")) {
                try {
                    String firstName = request.getParameter("exampleInputFirstName");
                    String lastName = request.getParameter("exampleInputLastName");
                    String address1 = request.getParameter("exampleInputAddressLine1");
                    String address2 = request.getParameter("exampleInputAddressLine2");
                    String city = request.getParameter("exampleInputCity");
                    String postCode = request.getParameter("exampleInputPostCode");
                    String country = request.getParameter("exampleInputCountry");
                    String company = request.getParameter("exampleInputCompany");
                    String ccexpirydate = request.getParameter("exampleInputCCExpiry");

                    String creditcardnumber = request.getParameter("exampleInputCCNumber");
                    String creditcardtype = request.getParameter("exampleInputCCType");

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                    Date sdfccExpiryDate = formatter.parse(ccexpirydate);

                    email = request.getParameter("exampleInputEmail2");
                    password = request.getParameter("exampleInputPassword2");

                    Customer customer = (Customer) session.getAttribute("customer");
                    if (customer == null) {
                        customer = new Customer();
                    }

                    customer.setFirstname(firstName);
                    customer.setLastname(lastName);
                    customer.setAddressline1(address1);
                    customer.setAddressline2(address2);
                    customer.setCity(city);
                    customer.setPostcode(postCode);
                    customer.setCountry(country);
                    customer.setCompany(company);
                    customer.setCreditcardexpiry(ccexpirydate);
                    customer.setCreditcardnumber(creditcardnumber);
                    customer.setCreditcardtype(creditcardtype);

                    customer.setEmailaddress(email);
                    customer.setLoginpassword(password);

                    if (CustomerDB.checkCustomerExists(email, password) == true) {
                        //CustomerDB.updateCustomer(customer);
                        String message = "You have already registered with those details. Please login with your username and password.";
                        request.setAttribute("message", message);

                        url = "/checkoutpage.jsp";
                    } else {
                        CustomerDB.insertCustomer(customer);

                        session.setAttribute("customer", customer);
                        Cart cart = (Cart) session.getAttribute("cart");

                        //session.setAttribute("cart", cart);
                        double overallTotal = (double) session.getAttribute("overallTotal");
                        String totalSum = cart.getOverallTotalCurrencyFormat(overallTotal);
                        request.setAttribute("overallTotal", overallTotal);
                        request.setAttribute("totalSum", totalSum);
                        session.setAttribute("overallTotal", overallTotal);

                        String message = "You have been added to our database.";
                        request.setAttribute("message", message);
                        // remove all items from the customer's cart
                        //session.setAttribute("cart", null);

                        url = "/placeorder.jsp";
//                        if (authorized) {
//                            sendEmailConfirmation(request, response, customer.getEmailaddress());
//                        }
                    }
                    session.setAttribute("customer", customer);
                } catch (ParseException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (submit.equals("Place Order")) {
                Cart cart = (Cart) session.getAttribute("cart");
                Customer customer = (Customer) session.getAttribute("customer");
                orderManager.placeOrder(customer, cart);
                url = "/paypalJS.jsp";
            }
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private void sendEmailConfirmation(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        // send an email to the user to confirm the order.
        String to = customer.getEmailaddress();
        String from = "confirmation@moravianmountainbikes.com";
        String subject = "Moravian Mountain Bikes Order Confirmation";
        String body = "Dear " + customer.getFirstname() + ",\n\n"
                + "Thanks for ordering from us. "
                + "You should receive your order in 3-5 business days. "
                + "Please contact us if you have any questions.\n"
                + "Have a great day and thanks again!\n\n"
                + "John Smith\n"
                + "Moravian Mountain Bikes";
        boolean isBodyHTML = false;
        try {
            MailUtil.sendMail(to, from, subject, body, isBodyHTML);
        } catch (MessagingException e) {
            this.log(
                    "Unable to send email. \n"
                    + "You may need to configure your system as "
                    + "described in chapter 15. \n"
                    + "Here is the email you tried to send: \n"
                    + "=====================================\n"
                    + "TO: " + to + "\n"
                    + "FROM: " + from + "\n"
                    + "SUBJECT: " + subject + "\n"
                    + "\n"
                    + body + "\n\n");
        }
    }
}
