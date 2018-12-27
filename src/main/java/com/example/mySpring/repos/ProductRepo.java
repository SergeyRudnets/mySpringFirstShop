package com.example.mySpring.repos;

import com.example.mySpring.domain.Cart;
import com.example.mySpring.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long>, JpaRepository<Product, Long> {
    Product findByTitle(String title);
    List<Product> findByCategory(String category);
    List<Product> findByCategoryAndTitleLike(String category, String title);
    List<Product> findByTitleLike(String title);
//    Product findOne(long Id);

}
