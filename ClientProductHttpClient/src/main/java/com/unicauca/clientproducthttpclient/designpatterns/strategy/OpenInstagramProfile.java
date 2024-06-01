package com.unicauca.clientproducthttpclient.designpatterns.strategy;

import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenInstagramProfile implements ActionStrategy{

    @Override
    public void execute(ActionEvent actionEvent) {
        String username="hyunseda_";
        try {
            URI uri = new URI("https://www.instagram.com/" + username);
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

    }
}
