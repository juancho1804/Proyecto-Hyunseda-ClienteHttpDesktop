/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.designpatterns.observer.Observer;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.ShoppingCart;

/**
 *
 * @author Juan
 */
public interface IShoppingCartService{

    void actualizarSubTotal();
    ShoppingCart getShoppingCart();
    double obtenerIva();
    double obtenerTotal();
    void eliminarItem(Item item);
    void agregarItem(Item item);
    void addObserver(Observer obs) ;

    /**
     * Notifica a todos los observadores que hubo un cambio en el modelo
     */
    void notifyAllObserves();
    
}
