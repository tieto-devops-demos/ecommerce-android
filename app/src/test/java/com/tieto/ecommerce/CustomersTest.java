package com.tieto.ecommerce;

import android.support.annotation.NonNull;

import com.tieto.ecommerce.fragments.CustomersFragment;

import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomersTest {
    @Test
    public void getCustomers() throws Exception {
        CustomersFragment fragment = new CustomersFragment();
        String json = readResource("/test_customers.json");
        assertThat(fragment.getCustomersFromJson(json), equalTo(createExpectedCustomers()));
    }

    @NonNull
    private List<Customer> createExpectedCustomers() {
        List<Customer> expected = new ArrayList<>();
        expected.add(new Customer(1, "Eberhard", "Wolff"));
        expected.add(new Customer(2, "Rod", "Johnson"));
        return expected;
    }

    // TODO: Move to a common file
    private String readResource(String resource) throws IOException {
        return readStream(getClass().getResourceAsStream(resource));
    }

    private String readStream(InputStream iStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
        StringBuilder text = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null)
            text.append(line + '\n');
        return text.toString();
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