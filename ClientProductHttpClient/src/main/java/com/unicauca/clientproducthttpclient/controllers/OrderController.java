package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.access.IOrderRepository;
import com.unicauca.clientproducthttpclient.access.OrderRestRepository;
import com.unicauca.clientproducthttpclient.domain.entities.Order;
import com.unicauca.clientproducthttpclient.domain.services.IOrderService;
import com.unicauca.clientproducthttpclient.domain.services.OrderService;


public class OrderController {
    private IOrderService orderService;
    public OrderController() {
        IOrderRepository orderRepository=new OrderRestRepository();
         this.orderService = new OrderService(orderRepository);
    }

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }
    public Order createOrder(Order order) {
        return this.orderService.createOrder(order);
    }
    public void createOrderClient(Integer clientId, Order order){
        this.orderService.createOrderClient(clientId, order);
    }


}
