/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.entities.Category;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import com.unicauca.clientproducthttpclient.domain.services.IProductService;
import com.unicauca.clientproducthttpclient.domain.services.ProductService;
import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author Juan
 */
public class ProductController extends Window implements Initializable{
    private final IProductService productService;

    @FXML
    private TextField txtNombreProd;
    @FXML
    private TextField txtPrecioProd;
    @FXML
    private TextField txtDescProd;
    @FXML
    private ComboBox<Category> cboCategorias;

    @FXML
    private Button btnInicio;
    @FXML
    private Button btnProductos;
    @FXML
    private Button btnAgregarProducto;
    @FXML
    AnchorPane pnlInicio;
    @FXML
    AnchorPane pnlProductos;
    @FXML
    private Label lblUsuario;
    


    public ProductController() {
        productService = new ProductService();
    }
    public ProductController(IProductService productService){
        this.productService=productService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.pnlInicio.setVisible(false);
        this.pnlProductos.setVisible(true);
        this.btnProductos.setOnAction(this::btnOnActionProductos);
        this.btnInicio.setOnAction(this::btnOnActionInicio);
        this.btnCerrarSesion.setOnAction(this::btnOnActionCerrarSesion);
        this.btnMinimize.setOnAction(super::btnOnActionMinimize);
        this.btnClose.setOnAction(this::btnOnActionClose);

    }

    public void btnOnActionInicio(ActionEvent event){
        try {
            // Cargar el archivo FXML con el nuevo controlador
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/homeAdmin.fxml"));
            loader.setController(new HomeAdminController()); // Establecer el nuevo controlador
            Parent root = loader.load();

            HomeAdminController controller = loader.getController();
            controller.setLblUsuario(this.lblUsuario.getText());

            // Configurar la nueva escena
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnInicio.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void btnOnActionProductos(ActionEvent event) {
        pnlInicio.setVisible(false);
        pnlProductos.setVisible(true);
    }




    public List<Product> findAll() {
        return productService.findAll();
    }
   
    public void create(Product product){
        productService.create(product);
    }

    public void edit(int id,Product productUpdated){
        productService.edit(id, productUpdated);
    }
    
    
    public void delete(int id){
        productService.delete(id);
    }
    public Product findById(int id){
        return productService.findById(id);
    }
    public Product findByName(String name) {
        return productService.findByName(name);
    }

    public void setLblUsuario(String text) {
        this.lblUsuario.setText(text);
    }
}
