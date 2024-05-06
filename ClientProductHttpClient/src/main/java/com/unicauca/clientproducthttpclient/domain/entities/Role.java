package com.unicauca.clientproducthttpclient.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Role {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
    public Role() {

    }
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }


}

