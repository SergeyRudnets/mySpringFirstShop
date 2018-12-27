package com.example.mySpring.controller;

import com.example.mySpring.domain.Cart;
import com.example.mySpring.domain.Category;
import com.example.mySpring.domain.User;
import com.example.mySpring.domain.dto.CaptchaResponseDto;
import com.example.mySpring.service.CartService;
import com.example.mySpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService UserService;

    @Value("${recaptcha.secret}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;
@Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("registration")
    public String registration(Model model) {
        Set<String> categories;


        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());
        getUserAndSetModelAttributes(model);
        model.addAttribute("categories",categories);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ){
        Set<String> categories;


        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());


//        model.addAttribute("categories",categories);
//            List<Cart> carts = cartService.getCarts(user.getId());
//            Set<Cart> itemsFromCart = null;
//            for (Cart cart1:carts)
//                if(cart1.getQuantity()!=0)
//                    itemsFromCart.add(cart1);
//            model.addAttribute("cart",itemsFromCart);

        //
//            Set<CartItem> itemsFromCart;
//            itemsFromCart = (Set<CartItem>) cartService.getUserCart(user).getCartItems();
//            model.addAttribute("cart", itemsFromCart);


        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(),CaptchaResponseDto.class);
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);

        if (!response.isSuccess()){
            model.addAttribute("captchaError", "Fill captcha");
        }
        if (isConfirmEmpty){
            model.addAttribute("password2Error","password confirmation cannot be empty");
        }
        if (user.getPassword() !=null &&!user.getPassword().equals(passwordConfirm)){
            model.addAttribute("passwordError", "Passwords are different!");
        }
        if (isConfirmEmpty || bindingResult.hasErrors()||!response.isSuccess()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);

            return "registration";
        }
        if (!UserService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(Model model){
        Set<String> categories;
        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());
        model.addAttribute("categories",categories);
        getUserAndSetModelAttributes(model);
        return "login";
    }
    public void getUserAndSetModelAttributes(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

//        if (!name.equals("anonymousUser")) {
//            List<Cart> carts = cartService.getCarts(userService.findByName(name).getId());
//            Set<Cart> itemsFromCart = null;
//            for (Cart cart1:carts)
//                if(cart1.getQuantity()!=0)
//                    itemsFromCart.add(cart1);
//            model.addAttribute("cart",itemsFromCart);
//        }
    }
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        Set<String> categories;
        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());
        model.addAttribute("categories",categories);
        getUserAndSetModelAttributes(model);
        boolean isActivated = UserService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }
}
