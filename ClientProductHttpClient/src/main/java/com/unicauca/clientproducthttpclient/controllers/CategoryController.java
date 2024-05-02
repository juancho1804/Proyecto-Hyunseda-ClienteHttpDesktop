/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.entities.Category;
import com.unicauca.clientproducthttpclient.domain.services.ICategoryService;
import java.util.List;

/**
 *
 * @author Juan
 */
public class CategoryController {
    private ICategoryService categoryService;
    
    public CategoryController(ICategoryService categoryService){
        this.categoryService=categoryService;
    }
    
    public List<Category> findAll() {
        return categoryService.findAll();
    }
    
    public void create(Category category){
        categoryService.create(category);
    }
    
    public void edit(int id,Category categoryUpdated){
        categoryService.edit(id, categoryUpdated);
    }
    
    public void delete(int id){
        categoryService.delete(id);
    }
   
    public Category findById(int id){
        return categoryService.findById(id);
    }

    public Category findByName(String name) {
        return categoryService.findByName(name);
    }
}
