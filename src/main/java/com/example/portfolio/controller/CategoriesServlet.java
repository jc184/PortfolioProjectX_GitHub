/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.controller;

import com.example.portfolio.database.ProductDB;
import com.example.portfolio.entities.Category;
import com.example.portfolio.entities.Product;
import java.io.IOException;
import java.util.List;
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
public class CategoriesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CategoriesServlet() {
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

        String url = "/index.jsp";

        HttpSession session = request.getSession();
        String submit = request.getParameter("submit");
        if (submit != null && submit.length() > 0) {

            if (submit.equals("Show Items")) {

                int id = Integer.parseInt(request.getParameter("id"));
                Category category = new Category(id);

                switch (id) {
                    case 1:
                        List<Product> mountainbikes = ProductDB.selectProductsByCategory(category);
                        request.setAttribute("mountainbikes", mountainbikes);
                        url = "/mountainbikes.jsp";
                        break;
                    case 2:
                        List<Product> roadracers = ProductDB.selectProductsByCategory(category);
                        request.setAttribute("roadracers", roadracers);
                        url = "/roadracers.jsp";
                        break;
                    case 3:
                        List<Product> headwears = ProductDB.selectProductsByCategory(category);
                        request.setAttribute("headwears", headwears);
                        url = "/headwears.jsp";
                        break;
                    case 4:
                        List<Product> clothing = ProductDB.selectProductsByCategory(category);
                        request.setAttribute("clothing", clothing);
                        url = "/clothing.jsp";
                        break;
                    case 5:
                        List<Product> accessories = ProductDB.selectProductsByCategory(category);
                        request.setAttribute("accessories", accessories);
                        url = "/accessories.jsp";
                        break;
                    case 6:
                        List<Product> spareparts = ProductDB.selectProductsByCategory(category);
                        request.setAttribute("spareparts", spareparts);
                        url = "/spareparts.jsp";
                        break;
                    default:
                        break;
                }

            }
            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }

    }
}
