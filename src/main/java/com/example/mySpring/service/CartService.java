package com.example.mySpring.service;

import com.example.mySpring.domain.*;
import com.example.mySpring.repos.CartRepo;
import com.example.mySpring.repos.ProductRepo;
import com.example.mySpring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private UserService userService;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductService productService;

    @Transactional
    public Cart save(Cart cart) {
        return cartRepo.save(cart);
    }

    @Transactional
    public void delete(Cart cart) {
        cartRepo.delete(cart);
    }

    @Transactional
    public Cart findOne(long cartId) {
        return cartRepo.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(cartId)));
    }

    @Transactional(readOnly = true)
    public List<Cart> findAll() {
        return cartRepo.findAll();
    }

    @Transactional
    public Cart getUserCart(User user) {
        Cart cart = cartRepo.findByUser(user);
        if (cart==null) cart = new Cart(user);
        return cart;
    }
   @Transactional
    public Cart clearUserCart(User user) {
       Cart cart = getUserCart(user);
       cart.clear();
       return save(cart);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Cart updateCartObject(User user, Product product, int quantity) throws NullPointerException {

        Cart cart = getUserCart(user);
        cart.update(product, quantity);
        return save(cart);

    }
//    @Transactional(rollbackFor = {Exception.class})
//    public List<Cart> getCarts(Long userId){
//        return cartRepo.findByUserIdAndOrdered(userId, false).isEmpty()?
//                (List<Cart>) new Cart(userId) :cartRepo.findByUserIdAndOrdered(userId, false);
//    }


}
