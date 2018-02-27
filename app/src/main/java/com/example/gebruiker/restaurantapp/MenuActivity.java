package com.example.gebruiker.restaurantapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MenuActivity extends AppCompatActivity implements ResponseCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        RestaurantApiHelper helper = new RestaurantApiHelper(this);
        helper.getEntrees();
    }

    public void getMenu() {

    }

    @Override
    public void onResponseSuccess() {
        Log.d("test", "the interface does something yay");
    }

}
