package com.tieto.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        populateProductsList();
    }

    private void populateProductsList() {
        new HttpGetTask(this).execute("http://131.207.59.30/catalog/catalog");
    }

    @Override
    public void taskFinished(String result) {
        List<Product> products = null;
        try {
            products = getProductsFromJson(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ListView listView = (ListView) findViewById(R.id.products_list);
        listView.setAdapter(new ProductListAdapter(this, products));
    }

    public List<Product> getProductsFromJson(String json) throws JSONException {
        List<Product> products = new ArrayList<>();
        JSONArray items = new JSONObject(json).getJSONObject("_embedded").getJSONArray("catalog");
        for(int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            products.add(new Product(item.getString("name"), item.getDouble("price")));
        }
        return products;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
