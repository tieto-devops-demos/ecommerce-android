package com.tieto.ecommerce;

import org.junit.Test;
import java.io.InputStream;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ProductsTest{
    @Test
    public void getProducts() throws Exception {
        MainActivity activity = new MainActivity();
        InputStream iStream = getClass().getResourceAsStream("/test_products.json");
        assertThat(activity.getProducts(iStream),
                is(Arrays.asList("iPod", "iPod touch",  "iPod nano", "Apple TV")));
    }
}