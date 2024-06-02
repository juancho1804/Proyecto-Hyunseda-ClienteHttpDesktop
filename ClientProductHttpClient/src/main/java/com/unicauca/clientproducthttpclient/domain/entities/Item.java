/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unicauca.clientproducthttpclient.designpatterns.observerItem.ItemObserver;

import java.util.ArrayList;
import java.util.List;

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

    private List<ItemObserver> observers = new ArrayList<>();

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
        this.subtotal = cantidad * product.getPrice();
        notifyObservers();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void addObserver(ItemObserver observer) {
        observers.add(observer);
    }

    // Método para eliminar observadores
    public void removeObserver(ItemObserver observer) {
        observers.remove(observer);
    }

    // Método para notificar a los observadores
    private void notifyObservers() {
        for (ItemObserver observer : observers) {
            observer.onItemStateChanged(this);
        }
    }

}
