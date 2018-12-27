package com.example.mySpring.domain;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 255, message = "Message too long (more than 255)")
    private String title;
    @Length(max = 2048, message = "Message too long (more than 2kB)")
    private String description;

    @Column(name = "price")
    private Double price;

    private String filename;

    private String category;

    @Max(99)
    @Min(0)
    private Integer discount = 10;

    @Formula("(Price * (100 + discount)) / 100")
    private Double oldPrice;

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Product(@NotBlank(message = "Please fill the message") @Length(max = 255, message = "Message too long (more than 255)") String title, @Length(max = 2048, message = "Message too long (more than 2kB)") String description, Double price, String filename, String category, @Max(99) @Min(0) Integer discount, Double oldPrice) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.filename = filename;
        this.category = category;
        this.discount = discount;
        this.oldPrice = oldPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Product() {
    }

    public Product(@NotBlank(message = "Please fill the message") @Length(max = 255, message = "Message too long (more than 255)") String title, @Length(max = 2048, message = "Message too long (more than 2kB)") String description, Double price, String filename, String category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.filename = filename;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
