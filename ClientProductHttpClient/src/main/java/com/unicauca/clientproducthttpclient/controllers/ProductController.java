/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.entities.Product;
import com.unicauca.clientproducthttpclient.domain.services.IProductService;
import java.util.List;

/**
 *
 * @author Juan
 */
public class ProductController {
    private IProductService productService;
    
    public ProductController(IProductService productService){
        this.productService=productService;
    }
       
    public List<Product> findAll() {
        return productService.findAll();
    }
   
    public void create(Product product){
        productService.create(product);
    }

    public void edit(int id,Product productUpdated){
        productService.edit(id, productUpdated);
    }
    
    
    public void delete(int id){
        productService.delete(id);
    }
    public Product findById(int id){
        return productService.findById(id);
    }
    public Product findByName(String name) {
        return productService.findByName(name);
    }
    
}
