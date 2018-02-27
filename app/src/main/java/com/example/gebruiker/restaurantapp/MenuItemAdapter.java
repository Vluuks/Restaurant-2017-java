package com.example.gebruiker.restaurantapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Gebruiker on 27-2-2018.
 */

public class MenuItemAdapter extends ArrayAdapter {

    public MenuItemAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }
}
