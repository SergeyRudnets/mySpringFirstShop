package com.example.mySpring.repos;

import com.example.mySpring.domain.Cart;
import com.example.mySpring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartRepo extends CrudRepository<Cart, Long>, JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
