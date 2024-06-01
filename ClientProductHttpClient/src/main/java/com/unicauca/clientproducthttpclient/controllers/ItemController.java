package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.services.IItemService;
import com.unicauca.clientproducthttpclient.domain.services.IShoppingCartService;
import com.unicauca.clientproducthttpclient.domain.services.ShoppingCartService;
import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ItemController{
    private IShoppingCartService shoppingCartService;


    @FXML
    private ImageView imgItem;
    @FXML
    private Label lblNombreItem;
    @FXML
    private Label lblDesc1Item;
    @FXML
    private Label lblDesc2Item;
    @FXML
    private Label lblPrecioItem;
    @FXML
    private Button btnAgregarItem;
    @FXML
    private Button btnEliminarItem;
    @FXML
    private Label lblItem;

    private Item item;

    public void setData(Item item) {
    this.item = item;
    if (item.getProduct() != null) {
        lblNombreItem.setText(item.getProduct().getName());
        lblDesc1Item.setText(item.getProduct().getDescription());
        // Verifica y establece el resto de las propiedades de Product
        lblPrecioItem.setText(String.valueOf(item.getProduct().getPrice()));

        // Load image from resources
        String imagePath = item.getProduct().getImage();

        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                String ruta= Utilities.convertirARutaValidaImagen(imagePath);
                Image image = new Image(getClass().getResourceAsStream(ruta));
                imgItem.setImage(image);
            } catch (NullPointerException e) {
                System.out.println("Error loading image: " + imagePath);
            }
        } else {
        }
    } else {
        // Manejar el caso cuando el producto es null
        }
    }

    public void agregarItem(ActionEvent actionEvent) {
        shoppingCartService.agregarItem(item);
        lblItem.setText(item.getCantidad()+" en el carrito");
        System.out.println("ACTUALIZACION -----------------");
        for(Item item1:shoppingCartService.getShoppingCart().getItems()) {
            System.out.println(item1.getProduct().getName());
            System.out.println(item1.getCantidad());
            System.out.println(item1.getSubtotal());
        }
        btnEliminarItem.setVisible(true);
    }
    public void eliminarItem(ActionEvent actionEvent) {
        shoppingCartService.eliminarItem(item);
        if(item.getCantidad()==0){
            btnEliminarItem.setVisible(false);
            lblItem.setText("");
        }else{
            System.out.println("ELIMINADO");
            lblItem.setText(item.getCantidad()+" en el carrito");
        }
    }


    public void setShoppingCartService(IShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
}
