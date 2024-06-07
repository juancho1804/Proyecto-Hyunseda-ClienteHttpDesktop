package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.domain.entities.Client;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Order;

import java.util.List;

public interface IOrderService {
    public Order createOrder(Order order);
    public Order createOrderClient(Order order, List<Item> items);
    public List<Order> getOrdersByUsername(String username);
    public List<Order> findAll();
}
