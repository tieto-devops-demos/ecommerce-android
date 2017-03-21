package com.tieto.ecommerce.fragments;

import android.widget.ListView;

import com.tieto.ecommerce.R;
import com.tieto.ecommerce.listAdapters.CustomerListAdapter;
import com.tieto.ecommerce.shopItems.Customer;
import com.tieto.ecommerce.shopItems.ShopItem;

import java.util.List;

public class OrdersFragment extends ItemsFragment {

    public OrdersFragment() {
        requestPath = "/customer/customer";
    }

    protected void populateList(ListView listView, String json) {
    }

}