package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Product;
import java.util.List;
import java.util.Map;

public interface IProductRepository {

    public List<Product> findAll();

    Product findById(int id);
    
    Product findByName(String name);

    public void create(Product product);

    public boolean edit(int id, Product productUpdated);

    public void delete(int id);

    public Map<String, Integer> contarProductosPorCategoria();

}
