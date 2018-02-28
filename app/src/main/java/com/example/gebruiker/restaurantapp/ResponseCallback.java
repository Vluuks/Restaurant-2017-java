package com.example.gebruiker.restaurantapp;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 27-2-2018.
 */

public interface ResponseCallback {
    void onResponseSuccess(ArrayList<MenuItem> finishedList); // make return the arraylist of thingies
}
