/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.designpatterns.observer.Subject;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import com.unicauca.clientproducthttpclient.domain.entities.ShoppingCart;
import java.util.ArrayList;
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

    public void agregarProducto(Product producto, int cantidad) {
        Item item = new Item(producto, cantidad);
        List<Item> items = shoppingCart.getItems();
        items.add(item);
        shoppingCart.setItems(items);
        actualizarSubTotal();
    }

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

    public void eliminarProducto(Item item) {
        List<Item> items = shoppingCart.getItems();
        items.remove(item);
        shoppingCart.setItems(items);
        actualizarSubTotal();
    }
    
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
    
    public Item obtenerItem(Product producto){
        for(Item item:shoppingCart.getItems()){
            if(item.getProduct().getId()==producto.getId()){
                return item;
            }
        }
        return null;
    }

    public void limpiarCarrito() {
        shoppingCart.setItems(new ArrayList<>());
        shoppingCart.setSubtotal(0.0);
    }

    public void actualizarSubTotal() {
        int subtotal = 0;
        for (Item item : shoppingCart.getItems()) {
            subtotal += item.getSubtotal();
        }
        shoppingCart.setSubtotal(subtotal);
        this.notifyAllObserves();

    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public boolean actualizarCantidadItem(Product producto, int cantidad) {
        List<Item> items = shoppingCart.getItems();
        for (Item item : items) {
            if (item.getProduct().equals(producto)) {
                item.setCantidad(item.getCantidad() + cantidad);
                item.setSubtotal(item.getProduct().getPrice() * item.getCantidad());
                return true;
            }
        }
        return false;
    }

    public double obtenerIva() {
        double iva = 0.19;
        return this.getShoppingCart().getSubtotal() * iva;
    }

    public double obtenerTotal() {
        double total = this.getShoppingCart().getSubtotal() + this.obtenerIva();
        return total;
    }

}
