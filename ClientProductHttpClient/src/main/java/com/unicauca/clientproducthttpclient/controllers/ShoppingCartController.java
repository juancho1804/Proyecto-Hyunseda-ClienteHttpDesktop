/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import com.unicauca.clientproducthttpclient.domain.entities.ShoppingCart;
import com.unicauca.clientproducthttpclient.domain.services.IShoppingCartService;
import com.unicauca.clientproducthttpclient.domain.services.ShoppingCartService;

/**
 *
 * @author Juan
 */
public class ShoppingCartController {
    private IShoppingCartService shopService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shopService = shoppingCartService;
    }

    public void agregarItem(Product producto, int cantidad) {
        this.shopService.agregarProducto(producto, cantidad);
    }

    public void eliminarProducto(Item item) {
        this.shopService.eliminarProducto(item);
    }

    public void limpiarCarrito() {
        this.shopService.limpiarCarrito();
    }

    public void actualizarSubTotal() {
        this.shopService.actualizarSubTotal();
    }

    public ShoppingCart getShoppingCart() {
        return this.shopService.getShoppingCart();
    }

    public boolean actualizarCantidadItem(Product producto, int cantidad) {
        return this.shopService.actualizarCantidadItem(producto, cantidad);
    }

    public double obtenerIva() {
        return this.shopService.obtenerIva();
    }

    public double obtenerTotal() {
        return this.shopService.obtenerTotal();
    }
    
}
