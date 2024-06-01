package com.unicauca.clientproducthttpclient.designpatterns.strategy;

import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenFacebookPageStrategy implements ActionStrategy{

    @Override
    public void execute(ActionEvent actionEvent) {
        String pageName="hyunseda";
        try {
            URI uri = new URI("https://www.facebook.com/" + pageName);
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }
}
