package com.example.gebruiker.restaurantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 *  Contains an overview of the items that belong to a certain category. Redirecting to a page
 *  that allows ordering and more information upon click.
 */
public class MenuActivity extends AppCompatActivity implements ResponseCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        RestaurantApiHelper helper = new RestaurantApiHelper(this);
        helper.getCategoryMenuItems(category);
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
            Log.d("test", "item clicked");
        }
    }
}
