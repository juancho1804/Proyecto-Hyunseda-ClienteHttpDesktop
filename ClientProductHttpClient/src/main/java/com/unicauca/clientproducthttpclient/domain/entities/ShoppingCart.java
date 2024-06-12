/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvan
 */
public class ShoppingCart {

    List<Item> items;
    double subtotal;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.subtotal = 0;
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
    
}
