package com.tieto.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add product", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        populateProductsList();
    }

    private void populateProductsList() {
        new HttpGetTask(this).execute("http://131.207.59.30/catalog/catalog");
    }

    @Override
    public void taskFinished(String result) {
        List<String> products = null;
        try {
            products = getProductsFromJson(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ListView listView = (ListView) findViewById(R.id.products_list);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                products));
    }

    public List<String> getProductsFromJson(String json) throws JSONException {
        List<String> products = new ArrayList<>();
        JSONArray items = new JSONObject(json).getJSONObject("_embedded").getJSONArray("catalog");
        for(int i = 0; i < items.length(); i++)
            products.add(items.getJSONObject(i).getString("name"));
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
