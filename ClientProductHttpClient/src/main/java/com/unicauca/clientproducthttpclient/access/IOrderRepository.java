package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Order;

import java.util.List;

public interface IOrderRepository {
    public Order create(Order order);
    //public Order createOrderClient(Order order);
    public Order findOrderByClientId(Long id);
    public Order createOrderClient(Order order,List<Item> items);
    public Long findMaxId();
    public List<Order> findOrdersByUserOfClients(String username);
}
