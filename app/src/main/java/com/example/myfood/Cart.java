package com.example.myfood;

public class Cart {
    String name;

    public Cart(String name, String quantity, String price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    String quantity;
    String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
