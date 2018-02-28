package com.example.gebruiker.restaurantapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuResponseCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        RestaurantApiHelper helper = new RestaurantApiHelper(this);
        helper.getCategoryMenuItems();
    }

    @Override
    public void onResponseSuccess(ArrayList<MenuItem> finishedList) {
        ListView lv = findViewById(R.id.listView);
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.row_item, finishedList);
        lv.setAdapter(adapter);
    }
}
