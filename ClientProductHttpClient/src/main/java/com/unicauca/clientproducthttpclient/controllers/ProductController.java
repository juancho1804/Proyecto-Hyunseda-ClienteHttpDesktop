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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



/**
 *
 * @author Juan
 */
public class ProductController extends Window implements Initializable{
    private  IProductService productService;
    private  ICategoryService categoryService;

    private String ruta;

    @FXML
    private TextField txtIdProd;
    @FXML
    private TextField txtNombreProd;
    @FXML
    private TextField txtPrecioProd;
    @FXML
    private TextField txtDescProd;
    @FXML
    private ImageView imgProducto;
    @FXML
    private ComboBox<String> cboCategorias;


    @FXML
    private Button btnInicio;
    @FXML
    private Button btnProductos;
    @FXML
    private Button btnCategorias;
    @FXML
    private Button btnAgregarProducto;
    @FXML
    private Button btnSubirImagen;
    @FXML
    private Button btnLimpiarProd;
    @FXML
    private Button btnActualizarProd;
    @FXML
    private Button btnEliminarProd;
    @FXML
    AnchorPane pnlInicio;
    @FXML
    AnchorPane pnlProductos;
    @FXML
    AnchorPane pnlCategorias;
    @FXML
    TableView<Product> tblProductos;
    @FXML
    TableColumn<Product,Integer> colIdProd;
    @FXML
    TableColumn<Product,String> colNombreProd;
    @FXML
    TableColumn<Product,String> colDescripcionProd;
    @FXML
    TableColumn<Product,Double> colPrecioProd;
    @FXML
    private TextField txtBuscarNombreProd;
    @FXML
    private TextField txtBuscarIdProd;
    @FXML
    private TextField txtBuscarCatProd;


    



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.categoryService=new CategoryService();
        this.productService=new ProductService();

        this.pnlInicio.setVisible(false);
        this.pnlCategorias.setVisible(false);
        this.pnlProductos.setVisible(true);
        this.btnProductos.setOnAction(this::btnOnActionProductos);
        this.btnCategorias.setOnAction(this::btnOnActionCategorias);
        this.btnInicio.setOnAction(this::btnOnActionInicio);
        this.btnCerrarSesion.setOnAction(this::btnOnActionCerrarSesion);
        this.btnMinimize.setOnAction(super::btnOnActionMinimize);
        this.btnClose.setOnAction(this::btnOnActionClose);
        this.btnSubirImagen.setOnAction(this::btnOnActionInsertarImagen);
        this.btnLimpiarProd.setOnAction(this::btnOnActionLimpiarProd);
        this.btnAgregarProducto.setOnAction(this::btnOnActionAgregarProducto);
        this.btnEliminarProd.setOnAction(this::btnOnActionEliminarProd);
        this.btnActualizarProd.setOnAction(this::btnOnActionActualizarProd);

        initializelblUsuario();
        initializeCboCategorias();
        initializeTablaProductos();
        txtBuscarNombreProd.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarProductosPorNombre(newValue);
        });

        txtBuscarIdProd.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarProductosPorId(newValue);
        });

        txtBuscarCatProd.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarProductosPorCategoria(newValue);
        });

        // Configurar el listener para la selección de productos en la tabla
        tblProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleProductSelection(null);
            }
        });

    }


    public void btnOnActionProductos(ActionEvent event) {
        pnlCategorias.setVisible(false);
        pnlInicio.setVisible(false);
        pnlProductos.setVisible(true);
    }

    public void btnOnActionAgregarProducto(ActionEvent event) {
        if(validarCampos()){
            Product product=new Product();
            product.setName(txtNombreProd.getText());
            product.setDescription(txtDescProd.getText());
            Category category=categoryService.findOneByName(cboCategorias.getValue());
            System.out.println(category.getCategoryId());
            product.setCategory(category);

            product.setImage(ruta);
            try{
                double precio = validarPrecio(txtPrecioProd.getText());
                product.setPrice(precio);
                productService.create(product);
                Utilities.mostrarAlerta("Información","El producto ha sido agregado con éxito");
                actualizarTablaProductos(productService.findAll());
            }catch (NumberFormatException e){}

        }else{
            Utilities.mostrarAlerta("Error", "Verifique que todos los campos estén llenos");
        }
    }

    public void btnOnActionActualizarProd(ActionEvent event){

        if(validarCampos() &&!txtIdProd.getText().isBlank()){
            try{
                int id= validarId(txtIdProd.getText());
                double precio=validarPrecio(txtPrecioProd.getText());

                Category category=categoryService.findOneByName(cboCategorias.getValue());
                Product product=new Product(id,txtNombreProd.getText(),txtDescProd.getText(),precio
                ,ruta);
                product.setCategory(category);
                if(productService.edit(id,product)){
                    Utilities.mostrarAlerta("Información","Producto editado con éxito");
                    actualizarTablaProductos(productService.findAll());
                }else{
                    Utilities.mostrarAlerta("Información","Producto a editar no encontrado !");
                }
            }catch (NumberFormatException e){}
        }else{
            Utilities.mostrarAlerta("Error", "Verifique que todos los campos estén llenos");
        }
    }

    public void btnOnActionEliminarProd(ActionEvent event) {
        if(!txtIdProd.getText().isBlank()){
            try {
                int id = validarId(txtIdProd.getText());
                productService.delete(id);
                actualizarTablaProductos(productService.findAll());
            } catch (NumberFormatException e) {
                Utilities.mostrarAlerta("Error", "El id debe ser un número válido");
            }
        }else{
            Utilities.mostrarAlerta("Error", "El campo id está vacío");
        }

    }


    public void btnOnActionInsertarImagen(ActionEvent event){
        FileChooser open = new FileChooser();
        open.setTitle("Seleccione una imagen");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagen File", "*.jpg","*.png"));
        File file = open.showOpenDialog(pnlProductos.getScene().getWindow());
        if(file!=null){
            imgProducto.setImage(new Image(file.toURI().toString(), 120, 149, false, true));
            ruta = file.getAbsolutePath();
            System.out.println(ruta);
        }
    }

    public void btnOnActionLimpiarProd(ActionEvent event){
        txtIdProd.setText("");
        txtNombreProd.setText("");
        txtPrecioProd.setText("");
        txtDescProd.setText("");
        imgProducto.setImage(null);
        cboCategorias.getItems().clear();
    }

  public void initializeCboCategorias() {
      List<Category> categoryList = this.categoryService.findAll();
      ObservableList<String>categorias = FXCollections.observableArrayList();
      for(Category category:categoryList){
          categorias.add(category.getName());
      }
      cboCategorias.setItems(categorias);
  }
    public void initializeTablaProductos() {
        // Configuración de las columnas si es necesario
        colIdProd.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colNombreProd.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colPrecioProd.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        colDescripcionProd.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        // Obtener la lista de productos desde el backend
        List<Product> listaDeProductos = productService.findAll();

        // Convertir la lista de productos a ObservableList
        ObservableList<Product> observableList = FXCollections.observableArrayList(listaDeProductos);

        // Asignar la lista de productos a la tabla
        tblProductos.setItems(observableList);
    }
    public void actualizarTablaProductos(List<Product>list){

        // Convertir la lista de productos a ObservableList
        ObservableList<Product> observableList = FXCollections.observableArrayList(list);

        // Asignar la lista de productos a la tabla
        tblProductos.setItems(observableList);

    }

    private void buscarProductosPorNombre(String nombre) {
        List<Product> productosEncontrados = productService.findByName(nombre);
        actualizarTablaProductos(productosEncontrados);
    }

    private void buscarProductosPorId(String id) {
        List<Product> productosEncontrados = productService.findById(id);
        actualizarTablaProductos(productosEncontrados);
    }
    private void buscarProductosPorCategoria(String name){
        List<Product>productosEncontrados = productService.findByCategoryName(name);
        actualizarTablaProductos(productosEncontrados);
    }
    private void handleProductSelection(MouseEvent event) {
        Product selectedProduct = tblProductos.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            txtIdProd.setText(String.valueOf(selectedProduct.getId()));
            txtNombreProd.setText(selectedProduct.getName());
            txtPrecioProd.setText(String.valueOf(selectedProduct.getPrice()));
            txtDescProd.setText(selectedProduct.getDescription());
            String imageUrl = selectedProduct.getImage(); // Obtener la URL de la imagen desde el producto seleccionado
            Image image = null;
            if (imageUrl != null && !imageUrl.isEmpty()) {
                try {
                    image = new Image(imageUrl, true); // Intentar cargar la imagen desde la URL
                } catch (Exception e) {
                    // Si hay un error al cargar la imagen desde la URL, manejarlo y buscar localmente
                }
            }
            if (image == null) {
                // Si la imagen no se cargó desde la URL, buscar localmente
                File localImageFile = new File(selectedProduct.getImage());
                if (localImageFile.exists()) {
                    image = new Image(localImageFile.toURI().toString(), true); // Cargar la imagen localmente
                }
            }
            imgProducto.setImage(image); // Establecer la imagen en el ImageView
            cboCategorias.getSelectionModel().select(selectedProduct.getCategory().getName());
            ruta=imageUrl;
        }
    }



    public Double validarPrecio(String texto)throws NumberFormatException{
        try{
            return Double.parseDouble(texto);
        }catch (NumberFormatException e){
            Utilities.mostrarAlerta("Error", "Verifique el precio sea un número valido");
            throw e;
        }
    }
    public static int validarId(String texto) throws NumberFormatException {
        try {
            int id= Integer.parseInt(texto);
            return id;
        } catch (NumberFormatException e) {
            Utilities.mostrarAlerta("Error","El id no es valido");
            throw e;
        }
    }
    public boolean validarCampos(){
        if(txtNombreProd.getText().isBlank()||txtDescProd.getText().isBlank()||txtPrecioProd.getText().isBlank()
        ||cboCategorias.getValue()==null||ruta==null){
            return false;
        }
        return true;
    }



}
