package com.example.gebruiker.restaurantapp;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Gebruiker on 28-2-2018.
 */

public interface ResponseCallback {
    void onResponseSuccess(ArrayList<?> responseList);
}
