package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.access.IProductRepository;
import com.unicauca.clientproducthttpclient.access.ProductRestRepository;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService{

    private final IProductRepository repo;

    public ProductService() {
        this.repo=new ProductRestRepository();
    }
    public ProductService(IProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }
    @Override
    public void create(Product product){
        repo.create(product);
    }
    @Override
    public boolean edit(int id,Product productUpdated){
        return repo.edit(id, productUpdated);
    }
    
    @Override
    public void delete(int id){
        repo.delete(id);
    }

    @Override
    public Product findById(int id) {
        return repo.findById(id);
    }
    @Override
    public List<Product> findByName(String name) {
        return repo.findByName(name);
    }
    public Map<String, Integer> contarProductosPorCategoria(){
        return repo.contarProductosPorCategoria();
    }
    public List<Product> findById(String id){
        return repo.findById(id);
    }
    

}
