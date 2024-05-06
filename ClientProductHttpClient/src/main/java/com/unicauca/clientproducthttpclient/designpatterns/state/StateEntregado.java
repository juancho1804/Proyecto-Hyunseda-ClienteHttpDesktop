package com.unicauca.clientproducthttpclient.designpatterns.state;

public class StateEntregado extends State{
    @Override
    public String estadoPedido() {
        return ("El pedido ya fue entregado.");
    }
}
