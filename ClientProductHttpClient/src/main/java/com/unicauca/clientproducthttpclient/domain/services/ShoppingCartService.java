/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.designpatterns.observer.Subject;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.ShoppingCart;
import java.util.List;

/**
 *
 * @author Juan
 */
public class ShoppingCartService  extends Subject implements IShoppingCartService {

    private ShoppingCart shoppingCart;

    public ShoppingCartService() {
        this.shoppingCart = new ShoppingCart();
    }


    @Override
    public void agregarItem(Item item){
        List<Item> items = shoppingCart.getItems();
        if(items.contains(item)){
            item.setCantidad(item.getCantidad() + 1);
        }else {
            item.setCantidad(item.getCantidad()+1);
            items.add(item);
        }
        shoppingCart.setItems(items);
        actualizarSubTotal();

    }


    @Override
    public void eliminarItem(Item item) {
        List<Item> items = shoppingCart.getItems();
        if(item.getCantidad()>1){
            item.setCantidad(item.getCantidad()-1);
        }else{
            item.setCantidad(0);
            items.remove(item);
        }
        shoppingCart.setItems(items);
        actualizarSubTotal();
        
    }


    @Override
    public void actualizarSubTotal() {
        int subtotal = 0;
        for (Item item : shoppingCart.getItems()) {
            subtotal += item.getSubtotal();
        }
        shoppingCart.setSubtotal(subtotal);
        this.notifyAllObserves();

    }

    @Override
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }


    @Override
    public double obtenerIva() {
        double iva = 0.19;
        return this.getShoppingCart().getSubtotal() * iva;
    }

    @Override
    public double obtenerTotal() {
        double total = this.getShoppingCart().getSubtotal() + this.obtenerIva();
        return total;
    }

}
