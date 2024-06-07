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

    List<Category> findAll();
    void create(Category category);
    boolean edit(int id, Category categoryUpdated);
    void delete(int id);
    List<Category> findById(String id);
    List<Category> findByName(String name);
    Category findOneByName(String name);
    Category findOneById(int id);
    
}
