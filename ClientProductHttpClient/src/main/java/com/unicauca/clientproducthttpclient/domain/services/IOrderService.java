package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.domain.entities.Client;
import com.unicauca.clientproducthttpclient.domain.entities.Order;

public interface IOrderService {
    public Order createOrder(Order order);
    public Order createOrderClient(Order order);
}
