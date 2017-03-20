package com.tieto.ecommerce.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tieto.ecommerce.Customer;
import com.tieto.ecommerce.CustomerListAdapter;
import com.tieto.ecommerce.R;
import com.tieto.ecommerce.network.AsyncResponse;
import com.tieto.ecommerce.network.HttpGetTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CustomersFragment extends Fragment implements AsyncResponse {

    public CustomersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        requestCustomersList();
        return view;
    }

    private void requestCustomersList() {
        new HttpGetTask(this).execute("http://131.207.59.30/customer/customer");
    }


    @Override
    public void httpTaskFinished(String result) {
        if(result==null) {
            showNetworkError();
            return;
        }
        List<Customer> customers = null;
        try {
            customers = getCustomersFromJson(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        populateList(customers);
    }

    private void showNetworkError() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Network error")
                .setMessage("Cannot connect to the server")
                .setNeutralButton(android.R.string.ok, null)
                .setPositiveButton(R.string.retry, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        requestCustomersList();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public List<Customer> getCustomersFromJson(String json) throws JSONException {
        List<Customer> list = new ArrayList<>();
        if(json==null)
            return list;
        JSONArray items = new JSONObject(json).getJSONObject("_embedded").getJSONArray("customer");
        for(int i = 0; i < items.length(); i++)
            list.add(new Customer(items.getJSONObject(i)));
        return list;
    }

    private void populateList(List<Customer> items) {
        ListView listView = (ListView) getView().findViewById(R.id.items_list);
        listView.setAdapter(new CustomerListAdapter(getActivity(), items));
    }

}