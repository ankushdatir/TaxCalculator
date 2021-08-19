package com.ideas.lab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BillingTest {

    private static final double BASIC_TAX = 10.0;
    private static final double IMPORTED_TAX = 5.0;

    Billing billing;

    @Test
    void testContainingTaxExemptedAndNonExemptedProductsWhichAreNotImported() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 124.99, 1, false, ProductType.BOOK));
        productList.add(new Product("Music CD", 149.99, 1, false, ProductType.OTHER));
        productList.add(new Product("Chocolate Bar", 40.85, 1, false, ProductType.FOOD));

        Map<String, Double> expectedPriceOfEachProduct = new HashMap<>();
        expectedPriceOfEachProduct.put("Book", 124.99);
        expectedPriceOfEachProduct.put("Music CD", 164.99);
        expectedPriceOfEachProduct.put("Chocolate Bar", 40.85);

        withProductListAndBasicTaxAndImportedTaxOf(productList)
                .costOfEachProductIs(expectedPriceOfEachProduct).havingTotalCostAndTaxPaidAs(330.83, 15.0);
    }

    @Test
    void testContainingTaxExemptedAndNonExemptedAndImportedProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Perfume Bottle", 270.99, 1, true, ProductType.OTHER));
        productList.add(new Product("Perfume", 180.99, 1, false, ProductType.OTHER));
        productList.add(new Product("Headache Pills", 19.75, 1, false, ProductType.MEDICAL));
        productList.add(new Product("Chocolate Box", 210.25, 1, true, ProductType.FOOD));

        Map<String, Double> expectedPriceOfEachProduct = new HashMap<>();
        expectedPriceOfEachProduct.put("Perfume Bottle", 311.64);
        expectedPriceOfEachProduct.put("Perfume", 199.09);
        expectedPriceOfEachProduct.put("Headache Pills", 19.75);
        expectedPriceOfEachProduct.put("Chocolate Box", 220.80);

        withProductListAndBasicTaxAndImportedTaxOf(productList)
                .costOfEachProductIs(expectedPriceOfEachProduct).havingTotalCostAndTaxPaidAs(751.28, 69.30);
    }

    @Test
    void testContainingAllImportedProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Perfume Bottle", 470.50, 1, true, ProductType.OTHER));
        productList.add(new Product("Chocolate Box", 100.00, 1, true, ProductType.FOOD));

        Map<String, Double> expectedPriceOfEachProduct = new HashMap<>();
        expectedPriceOfEachProduct.put("Perfume Bottle", 541.10);
        expectedPriceOfEachProduct.put("Chocolate Box", 105.00);

        withProductListAndBasicTaxAndImportedTaxOf(productList)
                .costOfEachProductIs(expectedPriceOfEachProduct).havingTotalCostAndTaxPaidAs(646.10, 75.60);
    }

    private BillingTest withProductListAndBasicTaxAndImportedTaxOf(List<Product> productList) {
        this.billing = new Billing(productList);
        return this;
    }

    private BillingTest costOfEachProductIs(Map<String, Double> expectedPriceOfEachProduct) {
        List<Product> actual = billing.generateBill(BASIC_TAX, IMPORTED_TAX);
        actual.forEach(product -> Assertions.assertEquals(expectedPriceOfEachProduct.get(product.getProductName()),
                product.getCostOfProduct()));
        return this;
    }

    private void havingTotalCostAndTaxPaidAs(double totalBill, double totalTax) {
        Assertions.assertEquals(totalBill, billing.costOfItems);
        Assertions.assertEquals(totalTax, billing.totalTaxPaid);
    }
}
