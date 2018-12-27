package com.example.mySpring.controller;

import com.example.mySpring.domain.Cart;
import com.example.mySpring.domain.CartItem;
import com.example.mySpring.domain.Category;
import com.example.mySpring.repos.ProductRepo;
import com.example.mySpring.service.CartService;
import com.example.mySpring.service.ProductService;
import com.example.mySpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MainController {
//    @Autowired
//    private MessageRepo messageRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;
    @Value("${upload.path}")
    private String uploadPath;
//    @GetMapping("/parts/header")
//    public String header(Model model)
//    {
//        Set<String> categories;
//
//
//        categories = Arrays.stream(Category.values()).map(Category::name)
//                .collect(Collectors.toSet());
//
//
//        model.addAttribute(categories);
//        return this.uploadPath;
//
//    }
    @GetMapping("/")
    public String greeting(Model model ) {
        Set<String> categories;
        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());
        model.addAttribute("categories",categories);

        getUserAndSetModelAttributes(model);

        return "greeting";
    }
    @GetMapping("/profile")
    public String profile(Model model) {
        Set<String> categories;
        categories = Arrays.stream(Category.values()).map(Category::name)
                .collect(Collectors.toSet());
        model.addAttribute("categories",categories);
        getUserAndSetModelAttributes(model);
        return "profile";
    }

    public void getUserAndSetModelAttributes(Model model)   {
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

//    @GetMapping("/main")
//    public String main(@RequestParam(required = false, defaultValue = "") String filter,
//                       Model model) {
////        Iterable<Product> products ;
////        Iterable<Message> messages ;
//
////        if (filter != null && !filter.isEmpty()) {
////            products = productRepo.findByTitle(filter);
//            messages = messageRepo.findByTag(filter);
//        } else {
////            products = productRepo.findAll();
//            messages = messageRepo.findAll();
//        }
//        Set<String> categories;
//        categories = Arrays.stream(Category.values()).map(Category::name)
//                .collect(Collectors.toSet());
//        model.addAttribute("categories",categories);
//
//        model.addAttribute("messages", messages);
//        model.addAttribute("filter", filter);
//        getUserAndSetModelAttributes(model);
//
//        return "main";
//    }




//    @PostMapping("/main")
//    public String add(
////            @Valid Product product,
//            @Valid Message message,
//            BindingResult bindingResult,
//            Model model,
//            @RequestParam("file") MultipartFile file
//    ) throws IOException {
//
//        if (bindingResult.hasErrors()){
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//            model.mergeAttributes(errorsMap);
//            model.addAttribute("message", message);
//        } else {
//            if (file != null && !file.getOriginalFilename().isEmpty()) {
//                File uploadDir = new File(uploadPath);
//
//                if (!uploadDir.exists()) {
//                    uploadDir.mkdir();
//                }
//
//                String uuidFile = UUID.randomUUID().toString();
//                String resultFilename = uuidFile + "." + file.getOriginalFilename();
//
//                file.transferTo(new File(uploadPath + "/img/" + resultFilename));
//
//                message.setFilename(resultFilename);
//            }
//            model.addAttribute("message",null);
//            Set<String> categories;
//            categories = Arrays.stream(Category.values()).map(Category::name)
//                    .collect(Collectors.toSet());
//            model.addAttribute("categories",categories);
//
//            messageRepo.save(message);
//        }
//
//        Iterable<Message> messages = messageRepo.findAll();
//        getUserAndSetModelAttributes(model);
//        model.addAttribute("messages", messages);
//
//        return "main";
//    }
//

}
