package com.example.mySpring.controller;

import com.example.mySpring.domain.Cart;
import com.example.mySpring.domain.Category;
import com.example.mySpring.domain.Role;
import com.example.mySpring.domain.User;
import com.example.mySpring.service.CartService;
import com.example.mySpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model, User user) {
        model.addAttribute("users", userService.findAll());
        Set<String> categories;


        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());


        model.addAttribute("categories",categories);
//        List<Cart> carts = cartService.getCarts(user.getId());
//        Set<Cart> itemsFromCart = null;
//        for (Cart cart1:carts)
//            if(cart1.getQuantity()!=0)
//                itemsFromCart.add(cart1);
//        model.addAttribute("cart",itemsFromCart);


        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        Set<String> categories;


        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());

//        List<Cart> carts = cartService.getCarts(user.getId());
//        Set<Cart> itemsFromCart = null;
//        for (Cart cart1:carts)
//            if(cart1.getQuantity()!=0)
//                itemsFromCart.add(cart1);
//        model.addAttribute("cart",itemsFromCart);
        model.addAttribute("categories",categories);


        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, username, form);

        return "redirect:/user";
    }
    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        Set<String> categories;


        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());

//        List<Cart> carts = cartService.getCarts(user.getId());
//        Set<Cart> itemsFromCart = null;
//        for (Cart cart1:carts)
//            if(cart1.getQuantity()!=0)
//                itemsFromCart.add(cart1);
//        model.addAttribute("cart",itemsFromCart);
        model.addAttribute("categories",categories);


        return "profile";
    }
    @PostMapping("profile")
    public String updateProfile(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ){
//        if (user != null) {
//            List<Cart> carts = cartService.getCarts(user.getId());
//            Set<Cart> itemsFromCart = null;
//            for (Cart cart1:carts)
//                if(cart1.getQuantity()!=0)
//                    itemsFromCart.add(cart1);
//            model.addAttribute("cart",itemsFromCart);
//        }
        userService.updateProfile(user,password,email);
        return "redirect:/user/profile";
    }
}
