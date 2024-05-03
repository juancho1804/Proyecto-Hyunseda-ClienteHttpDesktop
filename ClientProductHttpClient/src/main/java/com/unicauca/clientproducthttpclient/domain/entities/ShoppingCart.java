/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class ShoppingCart {

    List<Item> items;
    double subtotal;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.subtotal = 0;
    }

    public String imprimirItems() {
        if (items != null) {
            for (Item each : items) {
                return each.getProduct().getName();
            }
        }
        return ".";

    }

    public String getLastItem() {
        if (!items.isEmpty()) {
            return items.get(items.size() - 1).getProduct().getName(); // Retorna el último elemento del carrito
        } else {
            return "El carrito de compras está vacío";
        }
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
