package com.unicauca.clientproducthttpclient.designpatterns.strategy;

import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class OpenWhatsAppConversationStrategy implements ActionStrategy{
    @Override
    public void execute(ActionEvent actionEvent) {
        String phoneNumber="573147889201";
        try {
            URI uri = new URI("whatsapp://send?phone=" + phoneNumber);
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
