package com.example.mySpring.controller;

import com.example.mySpring.domain.Cart;
import com.example.mySpring.domain.CartItem;
import com.example.mySpring.domain.Category;
import com.example.mySpring.domain.User;
import com.example.mySpring.service.CartService;
import com.example.mySpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller

public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @GetMapping("/cart")
    public String viewCart(@AuthenticationPrincipal User user, Model model){
        Set<String> categories;
        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());
        model.addAttribute("categories",categories);
        Cart cart = cartService.getUserCart(user);
        List<CartItem> cartList = cart.getCartItems();
        Set<CartItem> itemsFromCart = new HashSet<>();
        for (CartItem cart1:cartList)
            if(cart1.getQuantity()!=0)
                itemsFromCart.add(cart1);
        model.addAttribute("cart",itemsFromCart);
        model.addAttribute("total",cart.getTotalItems());
        double productsCost = cart.getProductsCost();
        model.addAttribute("sum", productsCost);
        double disc = (double) 0;
        if(productsCost >500) disc = (double) Math.round(productsCost) / 100;
        if(productsCost >1000) disc = (double) Math.round(productsCost*2) / 100;
        if(productsCost >1500) disc = (double) Math.round(productsCost*3) / 100;
        if(productsCost >2000) disc = (double) Math.round(productsCost*4) / 100;
        if(productsCost >2500) disc = (double) Math.round(productsCost*5) / 100;
        model.addAttribute("disc", disc);
        return "cart";
    }
    @GetMapping("/checkout")
    public String placeOrder(@AuthenticationPrincipal User user, Model model){
        Set<String> categories;
        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());
        model.addAttribute("categories",categories);
        Cart cart = cartService.getUserCart(user);
        List<CartItem> cartList = cart.getCartItems();

        Set<CartItem> itemsFromCart = new HashSet<>();
        for (CartItem cart1:cartList)
            if(cart1.getQuantity()!=0){
                CartItem cartItem = cart1;
                cartItem.getProduct().setPrice(cart1.getProduct().getPrice()*cart1.getQuantity());
                itemsFromCart.add(cartItem);}
        model.addAttribute("cart",itemsFromCart);
        model.addAttribute("total",cart.getTotalItems());
        double productsCost = cart.getProductsCost();
        model.addAttribute("sum", productsCost);
        Double disc = Double.valueOf(0);
        if(productsCost >500) disc = (double) Math.round(productsCost) / 100;
        if(productsCost >1000) disc = (double) Math.round(productsCost*2) / 100;
        if(productsCost >1500) disc = (double) Math.round(productsCost*3) / 100;
        if(productsCost >2000) disc = (double) Math.round(productsCost*4) / 100;
        if(productsCost >2500) disc = (double) Math.round(productsCost*5) / 100;
        model.addAttribute("disc", productsCost - disc);
        return "checkout";
    }


    @PostMapping(value = "add-to-cart-btn_")
    public RedirectView addToCart(@AuthenticationPrincipal User user,
                                  @RequestParam("add-to-cart-btnVal") String title,
                                  @RequestParam("add-to-cart-btnVal2") String category,
                                  Model model) throws Exception {
        Set<String> categories;
        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());
        model.addAttribute("category", category);
        model.addAttribute("categories",categories);
        Cart cart = cartService.getUserCart(user);
        cartService.updateCartObject(user,productService.findByTitle(title),1);
        RedirectView redirect = new RedirectView(category);
        redirect.setExposeModelAttributes(true);
        return redirect;

    }


}
