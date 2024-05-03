/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import com.unicauca.clientproducthttpclient.domain.entities.ShoppingCart;

/**
 *
 * @author Juan
 */
public interface IShoppingCartService {
    public void agregarProducto(Product producto, int cantidad);

    public void eliminarProducto(Item item);

    public void limpiarCarrito();

    public void actualizarSubTotal();

    public ShoppingCart getShoppingCart();

    public boolean actualizarCantidadItem(Product producto, int cantidad);

    public double obtenerIva();

    public double obtenerTotal();
    
}
