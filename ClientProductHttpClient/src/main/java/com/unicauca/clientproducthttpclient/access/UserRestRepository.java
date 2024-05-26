package com.unicauca.clientproducthttpclient.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.clientproducthttpclient.Main;
import com.unicauca.clientproducthttpclient.domain.entities.Role;
import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.util.Messages;
import com.unicauca.clientproducthttpclient.util.Resultado;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRestRepository implements IUserRepository{

    @Override
    public Resultado registerUser(User user) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String url = "http://localhost:8004/auth/register";
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(user);
            StringEntity entity = new StringEntity(jsonRequest);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json");
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            System.out.println("Response status: " + response.getStatusLine());
            System.out.println("Response body: " + responseBody);



            if(response.getStatusLine().getStatusCode() == 200) {
                return new Resultado(true, "Usuario agregado");
            }


            httpClient.close();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public Resultado validateUser(User user) {
        boolean isValid = false;
        String role="";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String url = "http://localhost:8004/auth/login";
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(user);
            StringEntity entity = new StringEntity(jsonRequest);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json");
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            System.out.println("Response status: " + response.getStatusLine());
            System.out.println("Response body: " + responseBody);


            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Response status: " + statusCode);

            // Verificar si la respuesta es 200 (OK)
            if (statusCode == 200) {
                isValid = true; // Convertir el cuerpo de la respuesta a boolean
                role = responseBody.substring(responseBody.indexOf("[") + 1, responseBody.indexOf("]"));
                return new Resultado(isValid,role);
            }
            httpClient.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
