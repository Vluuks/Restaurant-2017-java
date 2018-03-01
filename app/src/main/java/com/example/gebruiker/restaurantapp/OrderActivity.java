package com.example.gebruiker.restaurantapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderActivity extends AppCompatActivity {

    HashMap<MenuItem, Integer> currentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        SharedPreferences prefs = getSharedPreferences("order", MODE_PRIVATE);
        String jsonString = prefs.getString("orderJson", null);

        if (jsonString != null) {
            Gson gson = new Gson();
            currentOrder = gson.fromJson(jsonString, new TypeToken<HashMap<MenuItem, Integer>>(){}.getType());
        }
        else {
            currentOrder = new HashMap<>();
        }

        ArrayList<MenuItem> orderList = new ArrayList<>();

//        for (int i = 0; i < currentOrder.size(); i++) {
//            MenuItem item = currentOrder.;
//
//        }


    }
}
