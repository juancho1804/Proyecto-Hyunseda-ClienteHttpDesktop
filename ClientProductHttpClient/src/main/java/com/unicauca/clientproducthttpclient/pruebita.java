/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient;

import com.unicauca.clientproducthttpclient.access.*;
import com.unicauca.clientproducthttpclient.controllers.CategoryController;
import com.unicauca.clientproducthttpclient.controllers.OrderController;
import com.unicauca.clientproducthttpclient.controllers.ProductController;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Order;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import com.unicauca.clientproducthttpclient.domain.services.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class pruebita {
    public static void main(String[] args) throws IOException {
        ICategoryRepository categoryRepository=new CategoryRestRepository();
        IProductRepository productRepository=new ProductRestRepository();
        
        ICategoryService categoryService=new CategoryService(categoryRepository);
        IProductService productService=new ProductService(productRepository);
        
        ProductController productController=new ProductController(productService);
        CategoryController categoryController =new CategoryController(categoryService);
        ItemRestRepository itemRestRepository=new ItemRestRepository();
        IItemService itemService=new ItemService(itemRestRepository);
        Product product =productController.findById(39);
        Item item=itemService.crearItem(product, 10);
        System.out.println(item.getProduct().getName());

        List<Item> items = new ArrayList<>();
        items.add(item);


        IOrderRepository orderRepository=new OrderRestRepository();
        IOrderService orderService=new OrderService(orderRepository);
        OrderController orderController=new OrderController(orderService);
        Order order=new Order();
        //order.setDate("01/01/2020");
        order.setItems(items);
        order.setState("Pedido");
        //orderController.createOrder(order);
        orderController.createOrderClient(2,order);






    }
    
}
