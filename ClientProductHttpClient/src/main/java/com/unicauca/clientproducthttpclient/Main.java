package com.unicauca.clientproducthttpclient;

import com.unicauca.clientproducthttpclient.access.CategoryRestRepository;
import com.unicauca.clientproducthttpclient.access.ICategoryRepository;
import com.unicauca.clientproducthttpclient.access.IProductRepository;
import com.unicauca.clientproducthttpclient.access.ProductRestRepository;
import com.unicauca.clientproducthttpclient.controllers.CategoryController;
import com.unicauca.clientproducthttpclient.controllers.ProductController;
import com.unicauca.clientproducthttpclient.controllers.ShoppingCartController;
import com.unicauca.clientproducthttpclient.domain.services.CategoryService;
import com.unicauca.clientproducthttpclient.domain.services.ICategoryService;
import com.unicauca.clientproducthttpclient.domain.services.IProductService;
import com.unicauca.clientproducthttpclient.domain.services.ProductService;
import com.unicauca.clientproducthttpclient.domain.services.ShoppingCartService;
import com.unicauca.clientproducthttpclient.presentation.GUIInicio;
import com.unicauca.clientproducthttpclient.presentation.GUIConfirmarPago;
import com.unicauca.clientproducthttpclient.presentation.GUIShoppingCart;


/**
 *
 * @author libardo
 * Este main muestra el funcionamiento basico de las peticiones a una API Rest
 * usando la biblioteca Apache HttpClient
 */
public class Main {

    public static void main(String[] args) {
        ICategoryRepository categoryRepository=new CategoryRestRepository();
        IProductRepository productRepository=new ProductRestRepository();
        
        ICategoryService categoryService=new CategoryService(categoryRepository);
        IProductService productService=new ProductService(productRepository);
        
        ProductController productController=new ProductController(productService);
        CategoryController categoryController =new CategoryController(categoryService);
        
        ShoppingCartService shoppingCartService = new ShoppingCartService();
        ShoppingCartController ShopCartControler = new ShoppingCartController(shoppingCartService);
        
        GUIInicio guiInicio=new GUIInicio(productController,categoryController,ShopCartControler);
        guiInicio.setVisible(true);
        
                
        

    }
}

    