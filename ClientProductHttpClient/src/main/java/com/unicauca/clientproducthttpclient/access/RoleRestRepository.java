package com.unicauca.clientproducthttpclient.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.clientproducthttpclient.Main;
import com.unicauca.clientproducthttpclient.domain.entities.Role;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleRestRepository implements IRoleRepository{
    public Role findById(Long id) {
        Role foundRole = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String url = "http://localhost:8004/RoleModel/byId/" + id;
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Response status: " + statusCode);
            if (statusCode == 200) {
                HttpEntity responseEntity = response.getEntity();
                String responseBody = EntityUtils.toString(responseEntity);
                ObjectMapper objectMapper = new ObjectMapper();
                foundRole = objectMapper.readValue(responseBody, Role.class);
            } else {
                System.out.println("Error: No se pudo encontrar el rol con el ID proporcionado");
            }
            httpClient.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foundRole;
    }

}
