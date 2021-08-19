package com.ideas.lab;


import lombok.Data;

@Data
public class Product {

    private String productName;

    private double price;

    private int isImported;

    private ProductType productType;

    private double costOfProduct;

    Product(String productName, double price, int units, boolean isImported, ProductType productType) {
        this.productName = productName;
        this.price = price * units;
        this.isImported = isImported ? 1 : 0;
        this.productType = productType;
    }
}
