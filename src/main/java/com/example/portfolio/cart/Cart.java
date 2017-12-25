/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.cart;

import com.example.portfolio.entities.Product;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class Cart {

    List<CartItem> items;
    int noOfItems;
    double total;

    public Cart() {
        items = new ArrayList<>();
        noOfItems = this.getNumberOfItems();
        total = this.calculateOverallTotal(this);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public double getTotal() {
        return total;
    }

    public void addItem(CartItem item) {
        int code = item.getProduct().getCode();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++) {
            CartItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode() == code) {
                lineItem.setQuantity((short) quantity);
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(CartItem item) {
        int code = item.getProduct().getCode();
        for (int i = 0; i < items.size(); i++) {
            CartItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode() == code) {
                items.remove(i);
                return;
            }
        }
    }

    public int getNumberOfItems() {
        noOfItems = 0;
        for (CartItem cartItem : items) {
            noOfItems += cartItem.getQuantity();
        }
        return noOfItems;
    }

    public double getSubtotal() {
        double amount = 0;
        for (CartItem cartItem : items) {
            Product product = (Product) cartItem.getProduct();
            amount += (cartItem.getQuantity() * product.getPrice());
        }
        return amount;
    }

    public void calcTotal(String charge) {
        double amount = 0;
        // cast surcharge as double
        double s = Double.parseDouble(charge);
        amount = this.getSubtotal();
        amount += s;
        total = amount;
    }

    public void clear() {
        items.clear();
        noOfItems = 0;
        total = 0;
    }

    public double calculateOverallTotal(Cart cart) {
        double overallTotal = 0;
        for (CartItem item : items) {
            double itemTotal = item.getTotal();
            overallTotal += itemTotal;
        }
        return (overallTotal);
    }

    public String getOverallTotalCurrencyFormat(double total) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        Cart cart = null;
        return currency.format(calculateOverallTotal(cart));
    }

    @Override
    public String toString() {
        return "Cart{" + "items=" + items + ", noOfItems=" + this.getNumberOfItems() + ", total=" + this.calculateOverallTotal(this) + '}';
    }
    
    
}
