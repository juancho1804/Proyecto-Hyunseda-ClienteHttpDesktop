/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.services;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Juan
 */
public interface IProductService {

    List<Product> findAll();
    void create(Product product);
    boolean edit(int id,Product productUpdated);
    boolean delete(int id);
    List<Product> findByName(String name);
    Map<String, Integer> contarProductosPorCategoria();
    List<Product> findById(String id);
    List<Product> findByCategoryName(String name);
    
}
