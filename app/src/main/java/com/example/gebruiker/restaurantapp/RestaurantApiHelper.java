package com.example.gebruiker.restaurantapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Gebruiker on 27-2-2018.
 */

public class RestaurantApiHelper {

    private String TAG = "RestaurantApi";

    public ResponseCallback delegate;
    private Context context;

    public RestaurantApiHelper(Context context) {
        this.delegate = (ResponseCallback) context;
        this.context = context;
    }

    public void getEntrees() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://resto.mprog.nl/menu?category=entrees";

        // Request a string response from the provided URL.
        JsonResponseListener listener = new JsonResponseListener();
        JsonObjectRequest stringRequest = new JsonObjectRequest(url, null, listener, listener);

        queue.add(stringRequest);
    }

    private class JsonResponseListener implements Response.Listener<JSONObject>, Response.ErrorListener {
        @Override
        public void onResponse(JSONObject response) {

            try {
                JSONArray items = response.getJSONArray("items");
                ArrayList<MenuItem> menuItems = new ArrayList<>();

                for (int i = 0; i < items.length(); i++) {
                    JSONObject menuItemJson = items.getJSONObject(i);

                    int id = menuItemJson.getInt("id");
                    String name = menuItemJson.getString("name");
                    String description = menuItemJson.getString("description");
                    float price = menuItemJson.getInt("price");
                    String imageUrl = menuItemJson.getString("image_url");
                }
            }
            catch (JSONException e) {

            }
            delegate.onResponseSuccess();
            Log.d(TAG, "success" + response);


        }

        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }

    public void getAppetizers() {

    }
}
