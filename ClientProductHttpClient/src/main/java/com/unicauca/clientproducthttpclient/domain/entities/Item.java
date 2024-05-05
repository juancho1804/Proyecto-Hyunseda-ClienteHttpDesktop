/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Juan
 */
public class Item {
    @JsonProperty("id")
    Long id;
    @JsonProperty("product")
    Product product;
    @JsonProperty("cantidad")
    Integer cantidad;
    @JsonProperty("subtotal")
    double subtotal;

    public Item(){

    }
    public Item(Product product, int cantidad) {
        this.product = product;
        this.cantidad = cantidad;
        this.subtotal = cantidad * product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

}
