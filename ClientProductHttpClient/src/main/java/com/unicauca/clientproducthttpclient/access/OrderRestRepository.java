package com.unicauca.clientproducthttpclient.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.clientproducthttpclient.Main;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Order;
import com.unicauca.clientproducthttpclient.util.ReceiptGenerator;
import com.unicauca.clientproducthttpclient.util.Utilities;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderRestRepository implements IOrderRepository{


    public Order createOrderClient(Order order, List<Item> items) {
        Order createdOrder = null; // Aquí almacenaremos la orden creada
        try {
            ReceiptGenerator receiptGenerator = new ReceiptGenerator();
            Long idOrden=this.findMaxId();

        String rutaOrden="D:\\pdfHyunseda\\Orden"+idOrden+".pdf";
        String recibo =receiptGenerator.generateReceiptPDF(items,rutaOrden,order);
        order.setItems(recibo);

        // Crear un objeto CloseableHttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Especificar la URL a la que se enviará la solicitud POST
        String url = "http://localhost:8002/order/"+order.getClient().getId();

        // Crear un objeto HttpPost con la URL especificada
        HttpPost httpPost = new HttpPost(url);

        // Crear un objeto ObjectMapper de Jackson
        ObjectMapper objectMapper = new ObjectMapper();

        // Convertir el objeto a JSON
        String jsonRequest = objectMapper.writeValueAsString(order);

        // Configurar la entidad de la solicitud con el JSON
        StringEntity entity = new StringEntity(jsonRequest);
        httpPost.setEntity(entity);

        // Configurar las cabeceras de la solicitud
        httpPost.setHeader("Content-Type", "application/json");
        // Si es necesario, puedes configurar otras cabeceras aquí

        // Ejecutar la solicitud y obtener la respuesta
        HttpResponse response = httpClient.execute(httpPost);

        // Verificar si la respuesta es exitosa (código 200)
        if (response.getStatusLine().getStatusCode() == 200) {
            // Si la respuesta es exitosa, asignamos la orden creada a createdOrder
            createdOrder = order;
            Utilities.mostrarAlerta("Información","Su orden ha sido procesada, puede revisarla en Ordenes. Gracias por su compra !");
        }

        // Cerrar el cliente HttpClient
        httpClient.close();

    } catch (JsonProcessingException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedEncodingException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Devolver la orden creada (puede ser null si hubo un error)
    return createdOrder;
}

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();

        try {
            // Crear un cliente HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // URL del endpoint
            String apiUrl = "http://localhost:8002/order";

            // Crear una solicitud GET con la URL
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Convertir la respuesta JSON a objetos Order
                ObjectMapper mapper = new ObjectMapper();
                orders = mapper.readValue(jsonResponse, mapper.getTypeFactory().constructCollectionType(List.class, Order.class));
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(OrderRestRepository.class.getName()).log(Level.SEVERE, "Error al obtener órdenes. Código de estado: " + statusCode);
            }

            // Cerrar el cliente HttpClient
            httpClient.close();

        } catch (IOException ex) {
            Logger.getLogger(OrderRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orders;
    }

    @Override
    public List<Order> findOrdersByUserOfClients(String username) {
        List<Order> orders = new ArrayList<>();

        try {
            // Crear un cliente HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // URL del endpoint
            String apiUrl = "http://localhost:8002/order/findByUser/" + username;

            // Crear una solicitud GET con la URL
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Convertir la respuesta JSON a objetos Order
                ObjectMapper mapper = new ObjectMapper();
                orders = mapper.readValue(jsonResponse, mapper.getTypeFactory().constructCollectionType(List.class, Order.class));
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(OrderRestRepository.class.getName()).log(Level.SEVERE, "Error al obtener órdenes. Código de estado: " + statusCode);
            }

            // Cerrar el cliente HttpClient
            httpClient.close();

        } catch (IOException ex) {
            Logger.getLogger(OrderRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orders;
    }

    public Long findMaxId() {
        Long maxId=null;

        try {
            // Crear un objeto CloseableHttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // Especificar la URL a la que se enviará la solicitud GET
            String url = "http://localhost:8002/order/maxId";

            // Crear un objeto HttpGet con la URL especificada
            HttpGet httpGet = new HttpGet(url);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(httpGet);

            // Verificar si la respuesta es exitosa (código 200)
            if (response.getStatusLine().getStatusCode() == 200) {
                // Leer la respuesta y obtener el máximo ID
                String responseBody = EntityUtils.toString(response.getEntity());
                maxId = Long.parseLong(responseBody);
                System.out.println(maxId);

            }

            // Cerrar el cliente HttpClient
            httpClient.close();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Devolver el máximo ID (puede ser null si hubo un error)
        return maxId;
    }






}
