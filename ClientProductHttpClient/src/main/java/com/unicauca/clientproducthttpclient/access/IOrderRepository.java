package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Order;

public interface IOrderRepository {
    public void create(Order order);
}
