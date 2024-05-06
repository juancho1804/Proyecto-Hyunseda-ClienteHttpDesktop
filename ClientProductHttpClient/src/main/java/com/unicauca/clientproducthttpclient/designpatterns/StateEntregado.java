package com.unicauca.clientproducthttpclient.designpatterns;

public class StateEntregado extends State{
    @Override
    public String estadoPedido() {
        return ("El pedido ya fue entregado.");
    }
}
