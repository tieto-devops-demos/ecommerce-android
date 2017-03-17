package com.tieto.ecommerce;

import android.support.annotation.NonNull;

import com.tieto.ecommerce.fragments.ProductsFragment;

import org.json.JSONObject;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ProductsTest{
    @Test
    public void getProducts() throws Exception {
        ProductsFragment fragment = new ProductsFragment();
        String json = readResource("/test_products.json");
        assertThat(fragment.getProductsFromJson(json), equalTo(createExpectedProducts()));
    }

    @NonNull
    private List<Product> createExpectedProducts() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product(1, "iPod", 42.0));
        expected.add(new Product(2, "iPod touch", 21.0));
        expected.add(new Product(3, "iPod nano", 1.0));
        expected.add(new Product(4, "Apple TV", 100.0));
        return expected;
    }

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
    public void nullJson() throws Exception {
        ProductsFragment fragment = new ProductsFragment();
        assertThat(fragment.getProductsFromJson(null),
                equalTo((List) new ArrayList<Product>()));
    }

    @Test
    public void createProductFromJsonObject() throws Exception {
        Product product = new Product(new JSONObject(
                "{\"id\": 1, \"name\": \"iPod\", \"price\": 42.0}"));
        assertEquals(1, product.id);
        assertEquals("iPod", product.title);
        assertEquals(42, product.price, 0.1);
    }
}