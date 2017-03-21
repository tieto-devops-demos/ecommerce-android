package com.tieto.ecommerce;

import android.support.annotation.NonNull;

import com.tieto.ecommerce.shopItems.Customer;
import com.tieto.ecommerce.shopItems.ShopItem;

import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomersTest {
    @Test
    public void getCustomers() throws Exception {
        String json = ResourceReader.read("/test_customers.json");
        assertThat(Customer.createListFromJson(json), equalTo(createExpectedCustomers()));
    }

    @NonNull
    private List<ShopItem> createExpectedCustomers() {
        List<ShopItem> list = new ArrayList<>();
        list.add(new Customer(1, "Eberhard", "Wolff"));
        list.add(new Customer(2, "Rod", "Johnson"));
        return list;
    }

    @Test
    public void createCustomerFromJsonObject() throws Exception {
        Customer customer = new Customer(new JSONObject(
                "{\"id\": 1, \"name\": \"Wolff\", \"firstname\": \"Eberhard\"}"));
        assertEquals(1, customer.id);
        assertEquals("Wolff", customer.lastName);
        assertEquals("Eberhard", customer.firstName);
    }

}