package com.unicauca.clientproducthttpclient.util;

import com.unicauca.clientproducthttpclient.domain.entities.Item;

import javax.swing.*;
import java.util.List;

public class ItemListModel extends AbstractListModel <Item> {
    private List<Item> items;
    public ItemListModel(List<Item> items) {
        this.items = items;
    }
    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public Item getElementAt(int index) {
        return items.get(index);
    }
}
