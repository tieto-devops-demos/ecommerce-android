package com.tieto.ecommerce;

import android.support.annotation.NonNull;

import com.tieto.ecommerce.shopItems.Product;
import com.tieto.ecommerce.shopItems.ShopItem;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ProductsTest{

    @Test
    public void createProductFromJsonObject() throws JSONException {
        Product product = new Product(new JSONObject(
                "{\"id\": 1, \"name\": \"iPod\", \"price\": 42.0}"));
        assertEquals(1, product.id);
        assertEquals("iPod", product.title);
        assertEquals(42, product.price, 0.1);
    }

    @Test
    public void getProductsFromJson() throws Exception {
        String json = ResourceReader.read("/test_products.json");
        assertThat(Product.createListFromJson(json), equalTo(createExpectedProducts()));
    }

    @NonNull
    private List<ShopItem> createExpectedProducts() {
        List<ShopItem> list = new ArrayList<>();
        list.add(new Product(1, "iPod", 42.0));
        list.add(new Product(2, "iPod touch", 21.0));
        list.add(new Product(3, "iPod nano", 1.0));
        list.add(new Product(4, "Apple TV", 100.0));
        return list;
    }

    @Test
    public void nullJson() throws Exception {
        assertThat(Product.createListFromJson(null), equalTo((List) new ArrayList()));
    }
}