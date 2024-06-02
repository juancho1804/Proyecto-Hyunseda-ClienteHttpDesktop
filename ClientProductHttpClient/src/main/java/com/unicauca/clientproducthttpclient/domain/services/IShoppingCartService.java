/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.designpatterns.observer.Observer;
import com.unicauca.clientproducthttpclient.designpatterns.observer.Subject;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import com.unicauca.clientproducthttpclient.domain.entities.ShoppingCart;

import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public interface IShoppingCartService{
    
    public void agregarProducto(Product producto, int cantidad);

    public void eliminarProducto(Item item);

    public void limpiarCarrito();

    public void actualizarSubTotal();

    public ShoppingCart getShoppingCart();

    public boolean actualizarCantidadItem(Product producto, int cantidad);

    public double obtenerIva();

    public double obtenerTotal();
    
    public void eliminarItem(Item item);
    
    public Item obtenerItem(Product producto);
    public void agregarItem(Item item);



    public void addObserver(Observer obs) ;

    /**
     * Notifica a todos los observadores que hubo un cambio en el modelo
     */
    public void notifyAllObserves();
    
}
