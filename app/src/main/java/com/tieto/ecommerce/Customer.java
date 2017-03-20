package com.tieto.ecommerce;

import org.json.JSONException;
import org.json.JSONObject;

public class Customer {
    public int id;
    public String firstName;
    public String lastName;

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
