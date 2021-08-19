package com.ideas.lab;


import org.apache.commons.math3.util.Precision;

import java.util.List;


public class Billing {

    private final List<Product> products;
    protected double costOfItems;
    protected double totalTaxPaid;

    Billing(List<Product> products) {
        this.products = products;
    }

    public List<Product> generateBill(double basicTax, double importedTax) {
        products.forEach(product -> {
            double taxValue = roundedAmount(product.getPrice() *
                    (product.getProductType().value * basicTax + product.getIsImported() * importedTax) / 100);
            costOfItems += product.getPrice() + taxValue;
            totalTaxPaid += taxValue;
            product.setCostOfProduct(product.getPrice() + taxValue);
        });
        costOfItems = Precision.round(costOfItems, 2);
        totalTaxPaid = Precision.round(totalTaxPaid, 2);
        return products;
    }

    private double roundedAmount(double value) {
        return Math.ceil(value * 20) / 20.0;
    }
}

