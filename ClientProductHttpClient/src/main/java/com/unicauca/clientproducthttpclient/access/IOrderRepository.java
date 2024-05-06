package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Order;

public interface IOrderRepository {
    public Order create(Order order);
    public void createOrderClient(Integer id,Order order);
}
