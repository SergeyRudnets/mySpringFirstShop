package com.example.mySpring.service;

import com.example.mySpring.domain.Product;
import com.example.mySpring.repos.ProductRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;


    public Product loadProductByTitle(String title) throws NotFoundException {
        Product product= productRepo.findByTitle(title);
        if (product == null){
            throw new NotFoundException("Product not found");
        }

        return product;

    }

    public boolean addProduct(Product product) {
        Product productFromDb = productRepo.findByTitle(product.getTitle());

        if (productFromDb != null) {
            return false;
        }


        productRepo.save(product);
        return true;
    }



    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }
    public Product findByTitle(String title) {
        return productRepo.findByTitle(title);
    }
    public Iterable<Product> findByCategoryAndTitleLike(String category, String search) {
//        if(category.length()==0) {
//            if(search.length()==0)
//                return productRepo.findAll();
//            else
                return productRepo.findByTitleLike(search);
//    }
//        else
//            return productRepo.findByCategoryAndTitleLike(category, search);
//        return productRepo.findByCategoryAndTitleLike(category, search);
    }

    public void saveProduct(Product product, Double price, Map<String,String> form) {
        product.setPrice(price);
        productRepo.save(product);
    }

    public void updateProduct(Product product, Double price) {
        Double prodPrice = product.getPrice();
        boolean isPriceChanged = (price !=null && !prodPrice.equals(price)||
                (prodPrice != null && prodPrice.equals(price)));
        if (isPriceChanged){
            product.setPrice(price);
        }
        productRepo.save(product);


    }

    public Iterable<Product> findByCategory(String category) {
       return productRepo.findByCategory(category);
    }

    public Product findById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
    }
}
