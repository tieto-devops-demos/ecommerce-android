package com.tieto.ecommerce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomerListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Customer> dataSource;

    public CustomerListAdapter(Context context, List<Customer> items) {
        this.context = context;
        dataSource = items;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.customer_list_item, parent, false);
        TextView idTextView = (TextView) rowView.findViewById(R.id.customer_list_id);
        TextView lastNameTextView = (TextView) rowView.findViewById(R.id.customer_list_last_name);
        TextView firstNameTextView = (TextView) rowView.findViewById(R.id.customer_list_first_name);

        Customer customer = (Customer) getItem(position);
        idTextView.setText(Integer.toString(customer.id));
        lastNameTextView.setText(customer.lastName);
        firstNameTextView.setText(customer.firstName);
        return rowView;
    }

}
