package com.example.gebruiker.restaurantapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MenuActivity extends AppCompatActivity implements ResponseCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void getMenu() {

    }

    @Override
    public void onResponseSuccess() {

    }

}
