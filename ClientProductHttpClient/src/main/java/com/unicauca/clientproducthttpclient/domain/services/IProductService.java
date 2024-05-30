/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.access.IProductRepository;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Juan
 */
public interface IProductService {

    public List<Product> findAll();
    public void create(Product product);
    public boolean edit(int id,Product productUpdated);
    public boolean delete(int id);
    public Product findById(int id);
    public List<Product> findByName(String name);
    public Map<String, Integer> contarProductosPorCategoria();
    public List<Product> findById(String id);
    public List<Product> findByCategoryName(String name);
    
}
