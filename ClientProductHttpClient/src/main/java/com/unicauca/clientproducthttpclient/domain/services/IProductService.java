/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.access.IProductRepository;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import java.util.List;

/**
 *
 * @author Juan
 */
public interface IProductService {

    public List<Product> findAll();
    public void create(Product product);
    public void edit(int id,Product productUpdated);
    public void delete(int id);
    public Product findById(int id);
    public Product findByName(String name);
    
}
