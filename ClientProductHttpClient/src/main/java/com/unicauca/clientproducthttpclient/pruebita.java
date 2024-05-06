/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient;

import com.unicauca.clientproducthttpclient.access.*;
import com.unicauca.clientproducthttpclient.controllers.CategoryController;
import com.unicauca.clientproducthttpclient.controllers.OrderController;
import com.unicauca.clientproducthttpclient.controllers.ProductController;
import com.unicauca.clientproducthttpclient.controllers.UserController;
import com.unicauca.clientproducthttpclient.domain.entities.*;
import com.unicauca.clientproducthttpclient.domain.services.*;
import com.unicauca.clientproducthttpclient.presentation.GUIRegistro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class pruebita {
    public static void main(String[] args) throws IOException {
        /*
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


        //IOrderRepository orderRepository=new OrderRestRepository();
        //IOrderService orderService=new OrderService(orderRepository);
        OrderController orderController=new OrderController();
        Order order=new Order();
        //order.setDate("01/01/2020");
        order.setItems(items);
        //State state=new State();
        //order.setState(state);
        //order.setId(orderController.createOrder(order).getId());
        //System.out.println(orderController.createOrder(order).getId());
        orderController.createOrderClient(2,order);
        System.out.println(order.getId());
        System.out.println(order.getState().estadoPedido());

         */
       /* IUserRepository userRepository=new UserRestRepository();
        Role role = new Role(1L,"Admin");

        User user=new User("diomedes","juanmanuelcerond","123");


        userRepository.createUser(user);
        System.out.println(userRepository.validateUser("diomedes","123"));
        System.out.println(userRepository.validateUser("diomedes","1234"));

        UserController userController=new UserController();
        User user2=new User("DANIEL","DANIEL2","123");
        userController.create(user2);
        System.out.println(userRepository.validateUser("DANIEL","1"));

        */
        //GUIRegistro guiRegistro = new GUIRegistro();
        //guiRegistro.setVisible(true);
        IRoleRepository roleRepository=new RoleRestRepository();
        Role role=new Role();
        role.setId(roleRepository.findById(1L).getId());
        role.setName(roleRepository.findById(1L).getName());

        System.out.println(roleRepository.findById(1L).getName());

        User user=new User("ADMIN2","ADMIN2","ADMIN");
        UserController userController=new UserController();
        userController.create(user);
        user.setRoleModel(role);





    }
    
}

