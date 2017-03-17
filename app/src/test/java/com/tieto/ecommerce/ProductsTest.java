package com.tieto.ecommerce;

import com.tieto.ecommerce.fragments.ProductsFragment;

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

        List<Product> expected = new ArrayList<>();
        expected.add(new Product("iPod", 42.0));
        expected.add(new Product("iPod touch", 21.0));
        expected.add(new Product("iPod nano", 1.0));
        expected.add(new Product("Apple TV", 100.0));
        assertThat(fragment.getProductsFromJson(json), equalTo(expected));
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
}