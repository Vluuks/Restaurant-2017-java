package com.example.gebruiker.restaurantapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *  Contains an overview of the items that belong to a certain category. Redirecting to a page
 *  that allows ordering and more information upon click.
 *
 *  TODO add loading dialogs
 *  TODO change top bar to show current page
 *  TODO add menu that lets you go to your order directly
 */
public class MenuActivity extends AppCompatActivity implements RestaurantApiHelper.ResponseCallback {

    private static final String TAG = "MenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        RestaurantApiHelper helper = new RestaurantApiHelper(this);
        helper.getCategoryMenuItemsAnon(category, this);
    }

    @Override
    public void onResponseSuccess(ArrayList<?> finishedList) {
        ListView listView = findViewById(R.id.listView);
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.row_item_menu, (ArrayList<MenuItem>) finishedList);
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

            MenuItem clickedItem = (MenuItem) adapterView.getItemAtPosition(i);
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
                    Log.d(TAG, String.valueOf(orderAmount));

                    // add to database
                }
            });

            // Create the AlertDialog.
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }

}



