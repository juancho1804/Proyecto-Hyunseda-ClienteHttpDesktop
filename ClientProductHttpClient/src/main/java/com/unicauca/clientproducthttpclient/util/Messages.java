/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.util;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.JOptionPane;
import java.net.http.HttpResponse.BodyHandlers;

/**
 *
 * @author Juan
 */
public class Messages {
    private static final String WHATSAPP_API_URL = "https://graph.facebook.com/v13.0/299365229927110/messages";
    private static final String WHATSAPP_AUTH_TOKEN = "EAALpUGDBp1ABOwLf4dDQEl5ZB9nvUIGWiZBQYfn0rwNoqpIGWaToCmGWOfGnfVnvPOypDqcqoGqaUEGnInuSYWUB5HZChXtKQlrFz5up4VBvESAPDhFjk9Y7qgW1ZCWAGltiJyekM23LsZAl7YiZAhbRBuRcu9gx4d12F87rK80wXkJfpueHqZAo2YAg6B7MXZBnP5auOKUMsZBVD5jdcrRsZD";
   // private static final String RECIPIENT_PHONE_NUMBER = "573209825275";
   

    
    public static void showMessageDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static int showConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public static void showMessageError(String message, String title){
         JOptionPane.showMessageDialog(null, message,title,JOptionPane.ERROR_MESSAGE);
    }
    
    
    public static void sendPaymentConfirmationMessage(String nombre, String correo, String telefono) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(WHATSAPP_API_URL))
                    .header("Authorization", "Bearer " + WHATSAPP_AUTH_TOKEN)
                    .header("Content-Type", "application/json")
                    //.POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"" + RECIPIENT_PHONE_NUMBER + "\", \"type\": \"template\", \"template\": { \"name\": \"factura\", \"language\": { \"code\": \"es\" }, \"components\":[{\"type\":\"header\",\"parameters\":[{\"type\":\"image\",\"image\":{\"link\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkEBUhWAQkK2D_4PxGFsIMT3fl7vqJbndbkiLuSbbR9A&s\"}}]},] } }"))
                    .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"" + telefono + "\", \"type\": \"template\", \"template\": { \"name\": \"factura\", \"language\": { \"code\": \"es\" }, \"components\":[{\"type\":\"header\",\"parameters\":[{\"type\":\"image\",\"image\":{\"link\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkEBUhWAQkK2D_4PxGFsIMT3fl7vqJbndbkiLuSbbR9A&s\"}}]}, {\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\"" + nombre + "\"}, {\"type\":\"text\",\"text\":\"" + correo + "\"}]}] } }"))
                    .build();
            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
