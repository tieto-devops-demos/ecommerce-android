package com.tieto.ecommerce.shopItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Customer implements ShopItem {
    public int id;
    public String firstName;
    public String lastName;

    public static List<ShopItem> createListFromJson(String json) {
        List<ShopItem> list = new ArrayList<>();
        if(json==null)
            return list;
        try {
            JSONArray items = new JSONObject(json).getJSONObject("_embedded").getJSONArray("customer");
            for(int i = 0; i < items.length(); i++)
                list.add(new Customer(items.getJSONObject(i)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Customer(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(JSONObject item) throws JSONException{
        this(item.getInt("id"),
                item.getString("firstname"),
                item.getString("name"));
    }

    @Override
    public boolean equals(Object object) {
        Customer other = (Customer) object;
        return id == other.id && firstName.equals(other.firstName) &&
                lastName.equals(other.lastName);
    }

    public String toString() {
        return String.format("%d: %s %s", id, firstName, lastName);
    }
}
