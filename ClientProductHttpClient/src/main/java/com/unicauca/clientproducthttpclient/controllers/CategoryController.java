/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.entities.Category;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import com.unicauca.clientproducthttpclient.domain.services.CategoryService;
import com.unicauca.clientproducthttpclient.domain.services.ICategoryService;
import com.unicauca.clientproducthttpclient.domain.services.IProductService;
import com.unicauca.clientproducthttpclient.domain.services.ProductService;
import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.unicauca.clientproducthttpclient.controllers.ProductController.validarId;

/**
 *
 * @author Juan
 */
public class CategoryController extends Window implements Initializable {
    private ICategoryService categoryService;

    @FXML
    TextField txtIdCategoria;
    @FXML
    TextField txtNombreCategoria;
    @FXML
    private AnchorPane pnlCategorias;
    @FXML
    private AnchorPane pnlInicio;
    @FXML
    private AnchorPane pnlProductos;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnProductos;
    @FXML
    private Button btnCategorias;
    @FXML
    public Button btnMinimize;
    @FXML
    public Button btnClose;
    @FXML
    private Button btnAgregarCategoria;
    @FXML
    private Button btnActualizarCategoria;
    @FXML
    private Button btnEliminarCategoria;
    @FXML
    private Button btnLimpiarCat;

    @FXML
    TableView<Category> tblCategorias;
    @FXML
    TableColumn<Category,Integer> colIdCategoria;
    @FXML
    TableColumn<Category,String> colNombreCategoria;
    @FXML
    TextField txtBuscarCatNombre;
    @FXML
    TextField txtBuscarCatId;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.categoryService = new CategoryService();
        this.pnlProductos.setVisible(false);
        this.pnlInicio.setVisible(false);
        this.pnlCategorias.setVisible(true);
        initializelblUsuario();
        this.btnProductos.setOnAction(this::btnOnActionProductos);
        this.btnInicio.setOnAction(this::btnOnActionInicio);
        this.btnCategorias.setOnAction(this::btnOnActionCategorias);
        this.btnCerrarSesion.setOnAction(this::btnOnActionCerrarSesion);
        this.btnClose.setOnAction(this::btnOnActionClose);
        this.btnMinimize.setOnAction(this::btnOnActionMinimize);
        this.btnAgregarCategoria.setOnAction(this::btnOnActionAgregarCategoria);
        this.btnEliminarCategoria.setOnAction(this::btnOnActionEliminarCategoria);
        initializeTablaCategorias();
        txtBuscarCatNombre.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarCategoriasPorNombre(newValue);
        });

        txtBuscarCatId.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarCategoriasPorId(newValue);
        });

    }

    public void btnOnActionCategorias(ActionEvent event) {
        pnlInicio.setVisible(false);
        pnlProductos.setVisible(false);
        pnlCategorias.setVisible(true);
    }

    public void initializeTablaCategorias() {
        // Configuración de las columnas si es necesario
        colIdCategoria.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCategoryId()).asObject());
        colNombreCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        // Obtener la lista de productos desde el backend
        List<Category> listaDeCategorias = categoryService.findAll();

        // Convertir la lista de productos a ObservableList
        ObservableList<Category> observableList = FXCollections.observableArrayList(listaDeCategorias);

        // Asignar la lista de productos a la tabla
        tblCategorias.setItems(observableList);
    }

    public void actualizarTablaCategorias(List<Category>list){

        // Convertir la lista de productos a ObservableList
        ObservableList<Category> observableList = FXCollections.observableArrayList(list);

        // Asignar la lista de productos a la tabla
        tblCategorias.setItems(observableList);

    }

    private void buscarCategoriasPorNombre(String nombre) {
        List<Category> categoriasEncontradas = categoryService.findByName(nombre);
        actualizarTablaCategorias(categoriasEncontradas);
    }

    private void buscarCategoriasPorId(String id) {
        List<Category> categoriasEncontradas = categoryService.findById(id);
        actualizarTablaCategorias(categoriasEncontradas);
    }

    public void btnOnActionAgregarCategoria(ActionEvent event) {
        if(!txtNombreCategoria.getText().isBlank()){
            Category category=new Category();
            category.setName(txtNombreCategoria.getText());
            categoryService.create(category);
            Utilities.mostrarAlerta("Información","La categoria ha sido agregado con éxito");
            actualizarTablaCategorias(categoryService.findAll());
        }else{
            Utilities.mostrarAlerta("Error", "Verifique que todos los campos estén llenos");
        }
    }

    public void btnOnActionEliminarCategoria(ActionEvent event) {
        if (!txtIdCategoria.getText().isBlank()) {
            try {
                int id = validarId(txtIdCategoria.getText());

                // Mostrar un diálogo de confirmación
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar eliminación");
                alert.setHeaderText(null);
                alert.setContentText("¿Estás seguro de que quieres eliminar esta categoría? Esta acción no se puede deshacer.");

                // Obtener la respuesta del usuario
                ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    System.out.println("entro aqui");
                    // El usuario confirmó la eliminación, proceder con la eliminación
                    IProductService productService = new ProductService();
                    Category category=categoryService.findOneById(id);
                    String nombreCategoria=category.getName();
                    List<Product> productos = productService.findByCategoryName(nombreCategoria.toLowerCase());
                    System.out.println(productos.size());
                    if(productos.size()>0){
                        for(Product product:productos){
                            productService.delete(product.getId());
                        }
                        categoryService.delete(id);
                    }
                    actualizarTablaCategorias(categoryService.findAll());
                }else{
                    System.out.println("entro acaa");
                }
            } catch (NumberFormatException e) {
                Utilities.mostrarAlerta("Error", "El id debe ser un número válido");
            }
        } else {
            Utilities.mostrarAlerta("Error", "El campo id está vacío");
        }

    }





}
