/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.access.CategoryRestRepository;
import com.unicauca.clientproducthttpclient.access.ICategoryRepository;
import com.unicauca.clientproducthttpclient.domain.entities.Category;
import java.util.List;

/**
 *
 * @author Juan
 */
public class CategoryService implements ICategoryService{
    ICategoryRepository repo;

    public CategoryService() {
        this.repo=new CategoryRestRepository();
    }

    public CategoryService(ICategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }
    @Override
    public void create(Category category){
        repo.create(category);
    }
    @Override
    public void edit(int id,Category categoryUpdated){
        repo.edit(id, categoryUpdated);
    }
    
    @Override
    public void delete(int id){
        repo.delete(id);
    }
    @Override
    public Category findById(int id){
        return repo.findById(id);
    }

    @Override
    public Category findByName(String name) {
        return repo.findByName(name);
    }
    
}
