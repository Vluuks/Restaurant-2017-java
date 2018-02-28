package com.example.gebruiker.restaurantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 *  Presents an overview of available categories in the menu, redirecting to the list of items
 *  in that category upon click.
 */
public class CategoryActivity extends AppCompatActivity implements RestaurantApiHelper.ResponseCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        RestaurantApiHelper helper = new RestaurantApiHelper(this);
        helper.getCategories();
    }

    @Override
    public void onResponseSuccess(ArrayList<?> responseList) {

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_item_category, R.id.textView, (ArrayList<String>) responseList);
        OnCategoryClickedListener listener = new OnCategoryClickedListener();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);
    }


    private class OnCategoryClickedListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(CategoryActivity.this, MenuActivity.class);
            intent.putExtra("category", adapterView.getItemAtPosition(i).toString());
            startActivity(intent);
        }
    }
}
