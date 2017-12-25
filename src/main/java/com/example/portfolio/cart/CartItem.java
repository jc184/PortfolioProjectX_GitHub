/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.portfolio.cart;

import com.example.portfolio.entities.Product;
import java.text.NumberFormat;

/**
 *
 * @author james
 */
public class CartItem {

    Product product;
    short quantity;

    public CartItem(Product product) {
        this.product = product;
        quantity = 1;
    }

    public CartItem() {
    }

    public Product getProduct() {
        return product;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public double getTotal() {
        double amount = 0;
        amount = (this.getQuantity() * product.getPrice());
        return amount;
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(this.getTotal());
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
