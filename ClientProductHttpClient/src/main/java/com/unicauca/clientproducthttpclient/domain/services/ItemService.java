/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.access.ItemRestRepository;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Product;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Juan
 */
public class ItemService implements IItemService{
    ItemRestRepository itemRestRepository;

    public ItemService(){
        this.itemRestRepository=new ItemRestRepository();
    }
    
    public ItemService(ItemRestRepository itemRestRepository){
        this.itemRestRepository=itemRestRepository;
    }
    
    public Item  crearItem(Product product, int cantidad) throws IOException {
         return itemRestRepository.create(product, cantidad);
    }
    public List<Item> obtenerItems(){
        return itemRestRepository.findAll();
    }
}
