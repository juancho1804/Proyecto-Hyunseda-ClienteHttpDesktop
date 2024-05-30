/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Category;
import com.unicauca.clientproducthttpclient.domain.entities.Product;

import java.util.List;

/**
 *
 * @author Juan
 */
public interface ICategoryRepository {
    
    public List<Category> findAll();

    public void create(Category category);

    public boolean edit(int id, Category categoryUpdated);

    public void delete(int id);

    public List<Category> findById(String id);
    public List<Category> findByName(String name);
    public Category findOneByName(String name);
    public Category findOneById(int id);
    
}
