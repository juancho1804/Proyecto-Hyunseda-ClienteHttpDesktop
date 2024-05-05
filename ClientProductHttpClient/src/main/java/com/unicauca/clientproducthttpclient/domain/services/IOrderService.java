package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.domain.entities.Order;

public interface IOrderService {
    public void createOrder(Order order);
    public void createOrderClient(Integer clientId, Order order);
}
