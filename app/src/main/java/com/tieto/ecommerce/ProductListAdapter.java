package com.tieto.ecommerce;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ProductListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Product> dataSource;

    public ProductListAdapter(Context context, List<Product> items) {
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
        View rowView = inflater.inflate(R.layout.product_list, parent, false);
        TextView titleTextView = (TextView) rowView.findViewById(R.id.product_list_title);
        TextView priceTextView = (TextView) rowView.findViewById(R.id.product_list_price);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.product_list_image);

        Product product = (Product) getItem(position);
        titleTextView.setText(product.title);
        priceTextView.setText(String.format("%.1f €", product.price));
        imageView.setImageResource(getImageId(position));
        return rowView;
    }

    private int getImageId(int position) {
        if (position > 3)
            return R.drawable.product;
        String imageName = String.format("product_%d", position + 1);
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }
}
