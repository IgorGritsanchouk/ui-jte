package com.ui.service;

import com.ui.model.Product;
import com.ui.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private Logger logger= LoggerFactory.getLogger(ProductService.class);
    public ProductService(ProductRepository productRepository){
        this.productRepository= productRepository;
    }

    private ProductRepository productRepository;

    public Product addProduct(@RequestBody Product product){
        return this.productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }
    public Product getProductByName(String name){
        return productRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Product not found with name: " + name));
    }

}
