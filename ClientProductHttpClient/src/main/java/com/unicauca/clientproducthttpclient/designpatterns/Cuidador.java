package com.unicauca.clientproducthttpclient.designpatterns;

import java.util.ArrayList;
import java.util.List;

public class Cuidador {
    private List<Memento> estados = new ArrayList<>();

    public void agregarMemento(final Memento memento) {
        estados.add(memento);
    }

    public Memento getMemento(final int indice) {
        return estados.get(indice);
    }
}
