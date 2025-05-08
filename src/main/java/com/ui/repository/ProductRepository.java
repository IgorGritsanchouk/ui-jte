package com.ui.repository;

import com.ui.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
//import org.springframework.stereotype.Repository;

//@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

    Optional<Product> findById(String id);
    Optional<Product> findByName(String name);

}
