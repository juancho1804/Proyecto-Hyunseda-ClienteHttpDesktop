package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Product;

import java.io.IOException;

public interface IItemService {
    public Item crearItem(Product product, int cantidad) throws IOException;
}
