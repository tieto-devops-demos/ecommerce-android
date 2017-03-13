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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
        ListView listView = (ListView) findViewById(R.id.products_list);
        List<String> products = new ArrayList<>();
        products.add("iPod");
        products.add("iPod touch");
        products.add("iPod nano");
        products.add("Apple TV");
        products.add("Mercury");
        products.add("Venus");
        products.add("Earth");
        products.add("Mars");
        products.add("Jupiter");
        products.add("Saturn");
        products.add("Neptune");
        products.add("Uran");
        products.add("Pluto");
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                products));
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
