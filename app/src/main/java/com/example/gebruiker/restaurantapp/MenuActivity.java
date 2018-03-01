package com.example.gebruiker.restaurantapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  Contains an overview of the items that belong to a certain category. Redirecting to a page
 *  that allows ordering and more information upon click.
 *
 *  TODO add loading dialogs
 *  TODO change top bar to show current page
 *  TODO add menu that lets you go to your order directly
 */
public class MenuActivity extends AppCompatActivity implements RestaurantApiHelper.MenuItemsCallback {

    //ArrayList<MenuItem> currentOrder;
    HashMap<MenuItem, Integer> currentOrder;

    private static final String TAG = "MenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        RestaurantApiHelper helper = new RestaurantApiHelper(this);
        helper.getCategoryMenuItemsAnon(category, this);

        currentOrder = loadFromSharedPrefs();
    }

    @Override
    public void onResponseSuccess(ArrayList<MenuItem> menuItemList) {
        ListView listView = findViewById(R.id.listView);
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.row_item_menu, menuItemList);
        OnMenuItemClickedListener listener = new OnMenuItemClickedListener();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);
    }

    private class OnMenuItemClickedListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // Set the layout for the dialog.
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.add_item_dialog, null);

            // Set title and description.
            TextView titleView = dialogView.findViewById(R.id.tvDialogTitle);
            TextView descriptionView = dialogView.findViewById(R.id.tvDialogContent);

            final MenuItem clickedItem = (MenuItem) adapterView.getItemAtPosition(i);
            titleView.setText(clickedItem.getName());
            descriptionView.setText(clickedItem.getDescription());

            // Initialize numberpicker.
            final NumberPicker numberPicker = dialogView.findViewById(R.id.dialogNumberPicker);
            numberPicker.setMaxValue(10);
            numberPicker.setMinValue(1);

            // Build the AlertDialog.
            AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
            builder.setView(dialogView);
            builder.setNegativeButton("Cancel", null);
            builder.setPositiveButton("Add to order", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    int orderAmount = numberPicker.getValue();
                    clickedItem.setQuantity(orderAmount);

                    currentOrder.put(clickedItem, orderAmount);
                    saveToSharedPrefs(currentOrder);

                }
            });

            // Create the AlertDialog.
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }

    public void saveToSharedPrefs(HashMap<MenuItem, Integer> orderList) {

        Gson gson = new Gson();

        String jsonString = gson.toJson(orderList, new TypeToken<HashMap<MenuItem, Integer>>() {}.getType());
        Log.d("json", jsonString);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("order", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("orderjson", jsonString);
        editor.apply();
    }

    public HashMap<MenuItem, Integer> loadFromSharedPrefs() {

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("order", MODE_PRIVATE);
        String jsonString = prefs.getString("order", null);

        if (jsonString != null) {
            Gson gson = new Gson();
            currentOrder = gson.fromJson(jsonString, new TypeToken<HashMap<MenuItem, Integer>>(){}.getType());
        }
        else {
            currentOrder = new HashMap<>();
        }
        return currentOrder;
    }

}



