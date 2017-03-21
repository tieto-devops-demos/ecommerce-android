package com.tieto.ecommerce.fragments;

import android.widget.ListView;

import com.tieto.ecommerce.shopItems.Product;
import com.tieto.ecommerce.listAdapters.ProductListAdapter;
import com.tieto.ecommerce.R;
import com.tieto.ecommerce.shopItems.ShopItem;

import java.util.List;

public class ProductsFragment extends ItemsFragment {

    public ProductsFragment() {
        requestPath = "/catalog/catalog";
    }

    protected void populateList(ListView listView, String json) {
        listView.setId(R.id.products_list);
        List<ShopItem> items = Product.createListFromJson(json);
        listView.setAdapter(new ProductListAdapter(getActivity(), items));
    }

}