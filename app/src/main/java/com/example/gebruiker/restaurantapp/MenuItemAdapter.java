package com.example.gebruiker.restaurantapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *  Adapter that shows image, title and price of a menu item.
 */
public class MenuItemAdapter extends ArrayAdapter {

    ArrayList<MenuItem> menuItems;

    public MenuItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        menuItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, parent, false);
        }

        ImageView image = convertView.findViewById(R.id.ivDishImage);
        TextView title = convertView.findViewById(R.id.tvDishTitle);
        TextView price = convertView.findViewById(R.id.tvDishPrice);

        MenuItem currentItem = menuItems.get(position);
        title.setText(currentItem.getName());

        return convertView;
    }
}
