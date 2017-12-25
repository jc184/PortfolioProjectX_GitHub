/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.util;

import com.example.portfolio.database.CategoryDB;
import com.example.portfolio.entities.Category;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author james
 */
public class CategoryContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();

        // initialize the list of categories
        List<Category> categories = CategoryDB.selectCategories();
        sc.setAttribute("categories", categories);

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
//        EntityManagerFactory emf = getEntityManagerFactory();
//        emf.close();
    }
}
