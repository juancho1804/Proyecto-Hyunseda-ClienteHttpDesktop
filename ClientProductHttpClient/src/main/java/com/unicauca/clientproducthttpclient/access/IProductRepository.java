package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Product;
import java.util.List;
import java.util.Map;

public interface IProductRepository {

    List<Product> findAll();

    List<Product> findByName(String name);

    void create(Product product);

    boolean edit(int id, Product productUpdated);

    boolean delete(int id);

    Map<String, Integer> contarProductosPorCategoria();

    List<Product> findById(String id);

    List<Product> findByCategoryName(String name);

}
