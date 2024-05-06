/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unicauca.clientproducthttpclient.designpatterns.State;
import com.unicauca.clientproducthttpclient.designpatterns.StateEntregado;
import com.unicauca.clientproducthttpclient.designpatterns.StateEnviado;
import com.unicauca.clientproducthttpclient.designpatterns.StateSolicitado;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Juan
 */
public class Order {
    
    @JsonProperty("id")
    Long id;
    @JsonProperty("date")
    String date;
    @JsonProperty("state")
    State state;
    @JsonProperty("items")
    List<Item> items;

    public Order() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        // Convertir a String
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date=fechaHoraActual.format(formato);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    
}
