package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Order;

import java.util.List;

public interface IOrderService {
    Order createOrderClient(Order order, List<Item> items);
    List<Order> getOrdersByUsername(String username);
    List<Order> findAll();
}
