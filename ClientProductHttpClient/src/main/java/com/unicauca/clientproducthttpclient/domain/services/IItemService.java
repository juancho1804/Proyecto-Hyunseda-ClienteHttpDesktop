package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Product;

import java.io.IOException;
import java.util.List;

public interface IItemService {
    Item crearItem(Product product, int cantidad) throws IOException;
    List<Item> obtenerItems();
}
