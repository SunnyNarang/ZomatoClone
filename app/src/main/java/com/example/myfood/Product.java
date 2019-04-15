package com.example.myfood;

public class Product {

    private String id;
    private String product;
    private String price;

    public Product(String id, String product, String price) {
        this.id = id;
        this.product = product;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public String getPrice() {
        return price;
    }
}