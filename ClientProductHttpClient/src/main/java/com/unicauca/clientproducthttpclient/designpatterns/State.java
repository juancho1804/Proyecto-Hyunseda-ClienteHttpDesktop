package com.unicauca.clientproducthttpclient.designpatterns;

import com.fasterxml.jackson.annotation.JsonProperty;

public  abstract class State {
    @JsonProperty("id")
    private int id;
    public  abstract String estadoPedido();
}
