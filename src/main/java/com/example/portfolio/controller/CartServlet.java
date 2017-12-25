/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.controller;

import com.example.portfolio.cart.Cart;
import com.example.portfolio.cart.CartItem;
import com.example.portfolio.database.ProductDB;
import com.example.portfolio.entities.Product;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author james
 */
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        ServletContext sc = getServletContext();

        String url = "/index.jsp";
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        int code = Integer.parseInt(request.getParameter("code"));
        session.setAttribute("code", code);
        
        String quantityString = request.getParameter("quantity");
        session.setAttribute("quantity", quantityString);
        if (submit != null && submit.length() > 0) {

            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }

            Product product = ProductDB.selectProduct(code);

            CartItem cItem = new CartItem();
            cItem.setProduct(product);
            cItem.setQuantity((short) quantity);
            if (quantity > 0) {
                cart.addItem(cItem);
            } else if (quantity == 0) {
                cart.removeItem(cItem);
            }
            if (submit.equals("Add To Cart")) {

//                HttpSession session = request.getSession();
                session.setAttribute("cart", cart);
                double overallTotal = cart.calculateOverallTotal(cart);
                String totalSum = cart.getOverallTotalCurrencyFormat(overallTotal);
                request.setAttribute("overallTotal", overallTotal);
                request.setAttribute("totalSum", totalSum);
                session.setAttribute("overallTotal", overallTotal);
                url = "/cart.jsp";

            } else if (submit.equals("Continue Shopping")) {
                
                url = "/index.jsp";
            } else if (submit.equals("Remove Item")) {
                if (quantity == 0) {
                    cart.removeItem(cItem);
                }
                session.setAttribute("cart", cart);
                double overallTotal = cart.calculateOverallTotal(cart);
                String totalSum = cart.getOverallTotalCurrencyFormat(overallTotal);
                request.setAttribute("overallTotal", overallTotal);
                request.setAttribute("totalSum", totalSum);
                session.setAttribute("overallTotal", overallTotal);
                url = "/cart.jsp";
            } else if (submit.equals("Update")) {
                
                if (quantity > 0) {
                    cart.addItem(cItem);
                } else if (quantity == 0) {
                    cart.removeItem(cItem);
                }
                session.setAttribute("cart", cart);
                double overallTotal = cart.calculateOverallTotal(cart);
                String totalSum = cart.getOverallTotalCurrencyFormat(overallTotal);
                request.setAttribute("overallTotal", overallTotal);
                request.setAttribute("totalSum", totalSum);
                session.setAttribute("overallTotal", overallTotal);
                url = "/cart.jsp";
            } else if (submit.equals("Show Cart")) {
                if (cart == null || cart.getNoOfItems() == 0) {
                    String message = "Your cart is empty";
                    request.setAttribute("message", message);
                  url = "/cart.jsp";  
                }
                
            }
            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);

    }
}
