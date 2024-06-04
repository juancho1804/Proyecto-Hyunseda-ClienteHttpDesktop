/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.Component;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author jvuan
 */
public class Utilities{
    /*
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof String) {
            String imageUrl = (String) value;
            try {
                URL url = new URL(imageUrl);
                Image image = new ImageIcon(url).getImage();
                setIcon(new ImageIcon(image));
            } catch (Exception e) {
                setIcon(null);
            }
        } else {
            setIcon(null);
        }
        
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }
        
        setHorizontalAlignment(JLabel.CENTER);
        
        return this;
    }*/
    /**
     * Carga una imagen a partir de la url
     * @param name url de la imagen
     * @return Un icono de la imagen
     */
    public static ImageIcon loadImageFromCloud(String name) {

        if (name != null && !name.isEmpty()) { // Verificar que la URL no sea null ni esté vacía
            String imageUrl = name;

            try {
                URL url = new URL(imageUrl);
                ImageIcon icon = new ImageIcon(ImageIO.read(url));
                return icon;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("La URL es nula o está vacía.");
        }
        return null;
    }

    public static FXMLLoader cargarFXML(String rutaFXML, String tituloVentana) {
        try {
            Parent root = FXMLLoader.load(Utilities.class.getResource(rutaFXML));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle(tituloVentana);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FXMLLoader(Utilities.class.getResource(rutaFXML));
    }

    public static void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public static String convertirARutaValidaImagen(String ruta) {
        ruta=ruta.replace('\\','/');
        int indiceInicio= ruta.indexOf("/hyunsedaimages");
        String subcadena= ruta.substring(indiceInicio);
        return subcadena;
    }


    public static String getCitiesByCountryAndDepartamento(String countryCode, String departamento) throws IOException {
        String url = "http://api.geonames.org/searchJSON?country=" + countryCode + "&adminCode1=" + departamento + "&featureClass=P&maxRows=10&username=demo";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }



}
