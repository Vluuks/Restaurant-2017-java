package com.example.gebruiker.restaurantapp;

import java.util.ArrayList;

/**
 *  Handles the callbacks from API requests to their corresponding activities.
 */
public interface ResponseCallback {
    void onResponseSuccess(ArrayList<?> responseList);
}
