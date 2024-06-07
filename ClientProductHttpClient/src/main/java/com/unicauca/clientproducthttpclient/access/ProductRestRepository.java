package com.unicauca.clientproducthttpclient.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.clientproducthttpclient.Main;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ProductRestRepository implements IProductRepository {

    public ProductRestRepository() {

    }

    @Override
    public List<Product> findAll() {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        List<Product> list = new ArrayList<>();
        try {

            // Definir la URL de la API REST de productos
            String apiUrl = "http://localhost:8001/ProductModel";
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
                Product[] products = mapper.readValue(jsonResponse, Product[].class);

                for (Product product : products) {
                    list.add(product);
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

    @Override
    public void create(Product product) {
        try {
            // Crear un objeto CloseableHttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // Especificar la URL a la que se enviará la solicitud POST
            String url = "http://localhost:8001/ProductModel";

            // Crear un objeto HttpPost con la URL especificada
            HttpPost httpPost = new HttpPost(url);

            // Crear un objeto ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Convertir el objeto a JSON
            String jsonRequest = objectMapper.writeValueAsString(product);

            // Configurar la entidad de la solicitud con el JSON
            StringEntity entity = new StringEntity(jsonRequest);
            httpPost.setEntity(entity);

            // Configurar las cabeceras de la solicitud
            httpPost.setHeader("Content-Type", "application/json");
            // Si es necesario, puedes configurar otras cabeceras aquí

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(httpPost);

            // Obtener el cuerpo de la respuesta
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);

            // Imprimir la respuesta
            System.out.println("Response status: " + response.getStatusLine());
            System.out.println("Response body: " + responseBody);


            // Cerrar el cliente HttpClient
            httpClient.close();

        } catch (JsonProcessingException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean edit(int id, Product productUpdated) {
        try {
            // Crear un objeto CloseableHttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // Especificar la URL a la que se enviará la solicitud PUT
            String url = "http://localhost:8001/ProductModel/"+id; // URL del producto a actualizar

            // Crear un objeto HttpPut con la URL especificada
            HttpPut httpPut = new HttpPut(url);

            // Crear un objeto ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Convertir el objeto a JSON
            String requestBody = objectMapper.writeValueAsString(productUpdated);

            // Configurar el cuerpo de la solicitud con el JSON
            StringEntity entity = new StringEntity(requestBody);
            httpPut.setEntity(entity);

            // Configurar las cabeceras de la solicitud si es necesario
            httpPut.setHeader("Content-Type", "application/json");

            // Ejecutar la solicitud PUT y obtener la respuesta
            HttpResponse response = httpClient.execute(httpPut);

            // Obtener el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();

            // Imprimir el código de estado de la respuesta
            System.out.println("Status code: " + statusCode);

            // Si se desea, también se puede obtener y mostrar el cuerpo de la respuesta
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response body: " + responseBody);
            if(statusCode==200){
                return true;
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
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            // Crear un objeto CloseableHttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // Especificar la URL a la que se enviará la solicitud DELETE
            String url = "http://localhost:8001/ProductModel/"+id; // URL del producto a eliminar

            // Crear un objeto HttpDelete con la URL especificada
            HttpDelete httpDelete = new HttpDelete(url);

            // Ejecutar la solicitud DELETE y obtener la respuesta
            HttpResponse response = httpClient.execute(httpDelete);

            // Obtener el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();

            // Imprimir el código de estado de la respuesta
            System.out.println("Status code: " + statusCode);

            // Si se desea, también se puede obtener y mostrar el cuerpo de la respuesta
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response body: " + responseBody);

            if(statusCode==200){
                //Utilities.mostrarAlerta("Información","Producto eliminado con éxito");
                return true;
            }
            // Cerrar el cliente HttpClient
            httpClient.close();

        } catch (IOException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Map<String, Integer> contarProductosPorCategoria() {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> productosPorCategoria = new HashMap<>();

        try {
            // Definir la URL de la API REST que cuenta productos por categoría
            String apiUrl = "http://localhost:8001/ProductModel/contarProductosPorCategoria"; // Cambia esto por la URL correcta de tu API

            // Crear una solicitud GET para contar los productos por categoría
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a un mapa de String (nombre de categoría) a Integer (cantidad de productos)
                productosPorCategoria = mapper.readValue(jsonResponse, Map.class);
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, "Error al contar productos por categoría. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productosPorCategoria;
    }

    public List<Product> findByName(String name) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productList = new ArrayList<>(); // Lista para almacenar los productos

        try {
            String apiUrl;
            if (name.isEmpty()) {
                // Si el campo de búsqueda está vacío, obtener todos los productos
                apiUrl = "http://localhost:8001/ProductModel";
            } else {
                String encodedName= name.replace(" ","%20");
                // Si el campo de búsqueda tiene un valor, buscar por coincidencia de cadenas en el nombre
                apiUrl = "http://localhost:8001/ProductModel/byNameMatching/" + encodedName;
            }

            // Crear una solicitud GET para obtener los productos
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a objetos Product
                productList = Arrays.asList(mapper.readValue(jsonResponse, Product[].class));
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, "Error al obtener productos. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productList;
    }

    public List<Product> findById(String id) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productList = new ArrayList<>(); // Lista para almacenar los productos

        try {
            String apiUrl;
            if (id.isEmpty()) {
                // Si el campo de búsqueda está vacío, obtener todos los productos
                apiUrl = "http://localhost:8001/ProductModel";
            } else {
                String encodedId= id.replace(" ","%20");
                // Si el campo de búsqueda tiene un valor, buscar por coincidencia de cadenas en el nombre
                apiUrl = "http://localhost:8001/ProductModel/byIdMatching/" + encodedId;
            }

            // Crear una solicitud GET para obtener los productos
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a objetos Product
                productList = Arrays.asList(mapper.readValue(jsonResponse, Product[].class));
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, "Error al obtener productos. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productList;
    }

    public List<Product> findByCategoryName(String name) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productList = new ArrayList<>(); // Lista para almacenar los productos

        try {
            String apiUrl;
            if (name.isEmpty()) {
                // Si el campo de búsqueda está vacío, obtener todos los productos
                apiUrl = "http://localhost:8001/ProductModel";
            } else {
                String encodedName= name.replace(" ","%20");
                // Si el campo de búsqueda tiene un valor, buscar por coincidencia de cadenas en el nombre
                apiUrl = "http://localhost:8001/ProductModel/byCategoryMatching/" + encodedName;
            }

            // Crear una solicitud GET para obtener los productos
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a objetos Product
                productList = Arrays.asList(mapper.readValue(jsonResponse, Product[].class));
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, "Error al obtener productos. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productList;
    }


    





}
