package com.tieto.ecommerce.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tieto.ecommerce.R;
import com.tieto.ecommerce.network.AsyncResponse;
import com.tieto.ecommerce.network.HttpGetTask;
import com.tieto.ecommerce.shopItems.ShopItem;

import java.util.List;

abstract public class ItemsFragment extends Fragment implements AsyncResponse {

    final String SERVER_ADDR = "http://ecommerce.common.pub.tds.tieto.com";
    // final String SERVER_ADDR = "http://zuul:8080";

    protected String requestPath;
    private ListView listView = null;

    public ItemsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.items_list);
        requestItemsList();
        return view;
    }


    private void requestItemsList() {
        new HttpGetTask(this).execute(SERVER_ADDR + requestPath);
    }

    @Override
    public void httpTaskFinished(String result) {
        if(result != null) {
            populateList(listView, result);
        }
        else
            showNetworkError();
    }

    abstract protected void populateList(ListView listView, String json);

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
                        requestItemsList();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
