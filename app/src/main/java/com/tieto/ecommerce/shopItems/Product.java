package com.tieto.ecommerce.shopItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Product implements ShopItem{
    public int id;
    public String title;
    public double price;

    public static List<ShopItem> createListFromJson(String json) {
        List<ShopItem> list = new ArrayList<>();
        if(json==null)
            return list;
        try {
            JSONArray items = new JSONObject(json).getJSONObject("_embedded").getJSONArray("catalog");
            for(int i = 0; i < items.length(); i++)
                list.add(new Product(items.getJSONObject(i)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

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
        return String.format("%d: %s - %.1f", id, title, price);
    }
}
