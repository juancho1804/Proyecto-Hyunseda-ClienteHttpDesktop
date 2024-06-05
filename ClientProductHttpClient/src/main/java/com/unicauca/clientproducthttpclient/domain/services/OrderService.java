package com.unicauca.clientproducthttpclient.domain.services;


import com.unicauca.clientproducthttpclient.access.IOrderRepository;
import com.unicauca.clientproducthttpclient.domain.entities.Client;
import com.unicauca.clientproducthttpclient.domain.entities.Order;

public class OrderService implements IOrderService {
    private  IOrderRepository repo;

    public OrderService(IOrderRepository repo) {
        this.repo = repo;
    }

    public Order createOrder(Order order) {
        return repo.create(order);
    }

    public Order createOrderClient(Order order) {
        return repo.createOrderClient(order);
    }
}
