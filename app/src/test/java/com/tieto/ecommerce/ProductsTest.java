package com.tieto.ecommerce;

import org.junit.Test;
import java.io.*;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ProductsTest{
    @Test
    public void getProducts() throws Exception {
        MainActivity activity = new MainActivity();
        String json = readResource("/test_products.json");

        assertThat(activity.getProductsFromJson(json),
                is(Arrays.asList("iPod", "iPod touch",  "iPod nano", "Apple TV")));
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
}