package com.example.gebruiker.restaurantapp;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 27-2-2018.
 */

public class RestaurantApiHelper {

    public void getEntrees() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

// Request a string response from the provided URL.
        JsonResponseListener listener = new JsonResponseListener();
        JsonObjectRequest stringRequest = new JsonObjectRequest(url, null, listener, listener);


    }

    private class JsonResponseListener implements Response.Listener<JSONObject>, Response.ErrorListener {
        @Override
        public void onResponse(JSONObject response) {

        }

        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }

    public void getAppetizers() {

    }
}
