package com.ideas.lab;

enum ProductType {
    BOOK(0), FOOD(0), MEDICAL(0), OTHER(1);

    int value;

    ProductType(int value) {
        this.value = value;
    }

}