package com.unicauca.clientproducthttpclient.designpatterns.strategy;

import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SendEmailStrategy implements ActionStrategy {

    @Override
    public void execute(ActionEvent actionEvent) {
        String emailAddress="contact@hyun.com.co";
        try {
            URI uri = new URI("mailto:" + emailAddress);
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }
}
