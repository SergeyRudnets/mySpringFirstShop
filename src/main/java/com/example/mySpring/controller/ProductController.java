package com.example.mySpring.controller;

import com.example.mySpring.domain.*;
import com.example.mySpring.repos.ProductRepo;
import com.example.mySpring.service.CartService;
import com.example.mySpring.service.ProductService;
import com.example.mySpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;
import java.util.stream.Collectors;

@Controller

public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserService userService;

    @GetMapping("/{category}")
    public String productlist(@PathVariable String category,
                              Model model) {
        String search;
        if (model.asMap().containsKey("search")) {
            search = (String) model.asMap().get("search");
       }
        search="";
        Iterable<Product> products;
        if (search==""||search.isEmpty())
            products = productService.findByCategory(category);
                else
            products = productService.findByCategoryAndTitleLike(category,search);


        model.addAttribute("products", products);
        model.addAttribute("category", category);
        model.addAttribute("search", search);
        Set<String> categories;


        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());
        getUserAndSetModelAttributes(model);


        model.addAttribute("categories",categories);
        return "productList";
    }

    private void getUserAndSetModelAttributes(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!name.equals("anonymousUser")) {
            Cart cart = cartService.getUserCart(userService.findByName(name));
            List<CartItem> cartList = cart.getCartItems();

            Set<CartItem> itemsFromCart = new HashSet<>();
            for (CartItem cart1:cartList)
                if(cart1.getQuantity()!=0)
                    itemsFromCart.add(cart1);
            model.addAttribute("cart",itemsFromCart);
            model.addAttribute("total",cart.getTotalItems());
            model.addAttribute("sum",cart.getProductsCost());

        }
    }

    @GetMapping("/{category}/{title}")
    public String getProduct(
                                @PathVariable("category") String category,
                             @PathVariable("title") String title,
                             Model model){
        Product product = productService.findByTitle(title);
        model.addAttribute("title", title);
//        model.addAttribute("prefix", "/**");

//        String category = product.getCategory();
        model.addAttribute("category", category);
        model.addAttribute("product", product);
        model.addAttribute("oldPrice", product.getOldPrice());
        Set<String> categories;
        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());
        model.addAttribute("categories",categories);
        getUserAndSetModelAttributes(model);
        return "productView";
    }
    @GetMapping(value = "productListSearch")
    public View searchForm(
            @RequestParam(required = false, defaultValue = "productlist", value = "section") String category,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            Model model) {
        String newSearch;
        if (search!=""||!search.isEmpty())
        newSearch = search.substring(0,search.length()-1);
        else newSearch="";

        model.addAttribute("category", category);
        model.addAttribute("search", newSearch);
        Set<String> categories;
        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());
        getUserAndSetModelAttributes(model);
        model.addAttribute("categories",categories);
        RedirectView redirect = new RedirectView(category);
        redirect.setExposeModelAttributes(true);


        return redirect;
    }


}
