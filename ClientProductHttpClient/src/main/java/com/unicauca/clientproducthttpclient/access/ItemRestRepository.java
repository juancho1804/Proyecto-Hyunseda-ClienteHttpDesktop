/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.clientproducthttpclient.Main;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import com.unicauca.clientproducthttpclient.util.Messages;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Juan
 */
public class ItemRestRepository implements IItemRestRepository{
        
    public List<Item> findAll() {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        List<Item> list = new ArrayList<>();
        try {

            // Definir la URL de la API REST de productos
            String apiUrl = "http://localhost:8002/items";
            // Crear una solicitud GET para obtener todos los productos
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a objetos Product
                Item[] items = mapper.readValue(jsonResponse, Item[].class);

                for (Item item : items) {
                    list.add(item);
                }

            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, "Error al obtener productos. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Item create(Product product, int cantidad) throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    String url = "http://localhost:8002/items?cantidad=" + cantidad;
    HttpPost httpPost = new HttpPost(url);
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonRequest = objectMapper.writeValueAsString(product);
    StringEntity entity = new StringEntity(jsonRequest);
    httpPost.setEntity(entity);
    httpPost.setHeader("Content-Type", "application/json");

    try {
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        String responseBody = EntityUtils.toString(responseEntity);
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response body: " + responseBody);

        // Verificar si la respuesta fue exitosa (código de estado 200)
        if (response.getStatusLine().getStatusCode() == 200) {
            // Convertir la respuesta JSON a un objeto Item
            return objectMapper.readValue(responseBody, Item.class);
        } else {
            // Manejar otros códigos de estado si es necesario
            // Por ejemplo, lanzar una excepción si la creación falla
            throw new IOException("Error al crear el producto: " + responseBody);
        }
    } finally {
        // Asegúrate de cerrar el cliente HttpClient
        httpClient.close();
    }
}



}
