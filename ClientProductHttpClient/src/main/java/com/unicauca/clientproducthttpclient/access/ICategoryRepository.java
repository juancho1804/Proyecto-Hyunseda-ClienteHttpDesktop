/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Category;
import java.util.List;

/**
 *
 * @author Juan
 */
public interface ICategoryRepository {
    
    public List<Category> findAll();
    
    Category findById(int id);

    public void create(Category category);

    public void edit(int id, Category categoryUpdated);

    public void delete(int id);
    
    Category findByName(String name);
    
}
