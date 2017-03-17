package com.tieto.ecommerce.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tieto.ecommerce.Product;
import com.tieto.ecommerce.ProductListAdapter;
import com.tieto.ecommerce.R;
import com.tieto.ecommerce.network.AsyncResponse;
import com.tieto.ecommerce.network.HttpGetTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment implements AsyncResponse {

    public ProductsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.products_fragment, container, false);
        requestProductsList();
        return view;
    }


    private void requestProductsList() {
        new HttpGetTask(this).execute("http://131.207.59.30/catalog/catalog");
    }

    @Override
    public void httpTaskFinished(String result) {
        List<Product> products = null;
        try {
            products = getProductsFromJson(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        populateProductsList(products);
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

    private void populateProductsList(List<Product> products) {
        ListView listView = (ListView) getView().findViewById(R.id.products_list);
        listView.setAdapter(new ProductListAdapter(getActivity(), products));
    }

}