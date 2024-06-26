package com.unicauca.clientproducthttpclient.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.clientproducthttpclient.domain.entities.Client;
import com.unicauca.clientproducthttpclient.domain.entities.User;
import org.apache.http.HttpEntity;
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

import static com.unicauca.clientproducthttpclient.access.UserRestRepository.usuarioIngresado;

public class ClientRestRepository implements IClientRestRepository{


    @Override
    public User findByUsername() {
        User user = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        List<User> list = new ArrayList<>();
        try {
            // Definir la URL de la API REST de usuarios
            String apiUrl = "http://localhost:8003/clients/"+usuarioIngresado.getToken()+"/"+usuarioIngresado.getUsername();
            // Crear una solicitud GET para obtener todos los usuarios
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a objetos User
                 user = mapper.readValue(jsonResponse, User.class);

            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(UserRestRepository.class.getName()).log(Level.SEVERE, null, "Error al obtener usuarios. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(UserRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public Client crearCliente(Client client) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            User user = findByUsername();
            client.setUsername(user.getUsername());
            client.setPassword(user.getPassword());
            client.setRoleModel(user.getRoleModel());
            client.setEmail(user.getEmail());
            String url = "http://localhost:8003/clients";
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(client);
            StringEntity entity = new StringEntity(jsonRequest);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json");
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity responseEntity = response.getEntity();
                String responseBody = EntityUtils.toString(responseEntity);
                return objectMapper.readValue(responseBody, Client.class);
            } else {
                throw new RuntimeException("Error al crear cliente. Código de estado: " + statusCode);
            }
        } catch (JsonProcessingException | UnsupportedEncodingException ex) {
            throw new RuntimeException("Error al procesar la solicitud", ex);
        } catch (IOException ex) {
            throw new RuntimeException("Error de E/S al ejecutar la solicitud", ex);
        } finally {
            try {
                httpClient.close();
            } catch (IOException ex) {
                throw new RuntimeException("Error al cerrar el cliente HTTP", ex);
            }
        }
    }



}
