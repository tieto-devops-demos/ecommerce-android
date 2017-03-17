package com.tieto.ecommerce;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {
    public int id;
    public String title;
    public double price;

    public Product(int id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product(JSONObject item) throws JSONException{
        this(item.getInt("id"),
                item.getString("name"),
                item.getDouble("price"));
    }

    @Override
    public boolean equals(Object object) {
        Product other = (Product) object;
        return id == other.id && title.equals(other.title) && price == other.price;
    }

    public String toString() {
        return String.format("%d: %s - %d", id, title, price);
    }
}
