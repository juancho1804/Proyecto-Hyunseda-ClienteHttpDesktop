/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.unicauca.clientproducthttpclient.designpatterns.state.State;
import com.unicauca.clientproducthttpclient.designpatterns.state.StateEntregado;
import com.unicauca.clientproducthttpclient.designpatterns.state.StateEnviado;
import com.unicauca.clientproducthttpclient.designpatterns.state.StateSolicitado;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Juan
 */
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Order {

    @JsonProperty("id")
    Long id;
    @JsonProperty("date")
    String date;
    @JsonProperty("state")
    State state;
    @JsonProperty("items")
    String items;
    @JsonProperty("clientModel")
    Client client;


    public Order(Client client) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date=fechaHoraActual.format(formato);
        this.client = client;
        solicitado();
    }
    public void solicitado(){
        this.state=new StateSolicitado();
    }
    public void enviar(){
        this.state= new StateEnviado();
    }
    public void entregar(){
        this.state=new StateEntregado();
    }


    
    
}
