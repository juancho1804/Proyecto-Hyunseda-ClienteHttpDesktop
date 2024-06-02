package com.unicauca.clientproducthttpclient.designpatterns.observerItem;

import com.unicauca.clientproducthttpclient.domain.entities.Item;

public interface ItemObserver {
    void onItemStateChanged(Item item);
}