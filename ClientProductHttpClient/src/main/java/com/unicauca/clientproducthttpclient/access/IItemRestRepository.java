package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Product;

import java.io.IOException;
import java.util.List;

public interface IItemRestRepository {
    Item create(Product product, int cantidad) throws IOException;
    List<Item> findAll();
}
