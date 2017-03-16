package com.tieto.ecommerce;

public class Product {
    public String title;
    public double price;

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        Product product = (Product) object;
        return title.equals(product.title) && price == product.price;
    }

    public String toString() {
        return title + " - " + price;
    }
}
