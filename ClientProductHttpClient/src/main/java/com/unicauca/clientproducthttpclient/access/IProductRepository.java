package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Product;
import java.util.List;

public interface IProductRepository {

    public List<Product> findAll();

    Product findById(int id);
    
    Product findByName(String name);

    public void create(Product product);

    public void edit(int id, Product productUpdated);

    public void delete(int id);

}
