package com.ui.controllers;

import com.ui.model.Product;
import com.ui.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ProductRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserControllerVm.class);

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    private ProductService productService;

    @PostMapping("/add-product")
    public Product addProduct(@RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

//    @GetMapping("/products/{id}")
//    public List<Product> getProductById(@PathVariable String id) {
//        return this.productService.getAllProducts();
//    }
//
//    @GetMapping("/products/{name}")
//    public List<Product> getProductByName(@PathVariable String name) {
//        return this.productService;
//    }

}
