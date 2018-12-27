package com.example.mySpring.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @Column(name = "cartId", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @OneToOne
    @JoinColumn (name="user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = CartItem.class, mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>(0);

    @Column(name = "products_cost")
    private double productsCost;

    @Column(name = "total_items")
    private int totalItems;


    public Cart() {
        this.totalItems = 0;
        this.productsCost = 0;
    }
    public Cart(User user) {
        this.user = user;
        this.totalItems = 0;
        this.productsCost = 0;
    }
    public void update(Product product, int quantity) {
        CartItem newItem = new CartItem(this, product, quantity);
        List<CartItem> items = getCartItems();
        if (items.contains(newItem)) {
            int index = items.indexOf(newItem);
            if (quantity > 0) {
                items.get(index).setQuantity(quantity);
            } else {
                items.remove(newItem);
            }
        } else {
            items.add(newItem);
        }
        revalidateCartMetrics();
    }
    public void clear() {
        getCartItems().clear();
        revalidateCartMetrics();
    }

    public void revalidateCartMetrics() {
        int total = 0;
        int cost = 0;
        for (CartItem item : getCartItems()) {
            total += item.getQuantity();
            cost += item.getQuantity() * item.getProduct().getPrice();
        }
        setProductsCost(cost);
        setTotalItems(total);
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getProductsCost() {
        return productsCost;
    }

    public void setProductsCost(double productsCost) {
        this.productsCost = productsCost;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
