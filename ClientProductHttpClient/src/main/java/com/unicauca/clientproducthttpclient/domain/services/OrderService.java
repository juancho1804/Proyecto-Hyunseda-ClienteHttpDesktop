package com.unicauca.clientproducthttpclient.domain.services;


import com.unicauca.clientproducthttpclient.access.IOrderRepository;
import com.unicauca.clientproducthttpclient.access.OrderRestRepository;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Order;

import java.util.List;

public class OrderService implements IOrderService {
    private  IOrderRepository repo=new OrderRestRepository();

    @Override
    public Order createOrderClient(Order order, List<Item> items) {
        return repo.createOrderClient(order,items);
    }
    @Override
    public List<Order> getOrdersByUsername(String username) {
        return repo.findOrdersByUserOfClients(username);
    }
    @Override
    public List<Order> findAll(){
        return repo.findAll();
    }
}
