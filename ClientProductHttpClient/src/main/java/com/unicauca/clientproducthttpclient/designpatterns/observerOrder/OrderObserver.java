package com.unicauca.clientproducthttpclient.designpatterns.observerOrder;

import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Order;

public interface OrderObserver {
    void onOrderStateChanged(Order order);
}
