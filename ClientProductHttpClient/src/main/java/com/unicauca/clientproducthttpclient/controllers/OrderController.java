package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.entities.Order;
import com.unicauca.clientproducthttpclient.domain.services.IOrderService;


public class OrderController {

    private IOrderService orderService;
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }
    public void createOrder(Order order) {
        this.orderService.createOrder(order);
    }
    public void createOrderClient(Integer clientId, Order order){
        this.orderService.createOrderClient(clientId, order);
    }


}
