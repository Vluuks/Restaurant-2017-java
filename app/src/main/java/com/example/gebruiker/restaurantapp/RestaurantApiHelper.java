package com.example.gebruiker.restaurantapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 27-2-2018.
 */

public class RestaurantApiHelper {

    private String TAG = "RestaurantApi";

    public MenuResponseCallback menuActivityDelegate;
    public CategoryResponseCallback categoryActivityDelegate;
    private Context context;

    public RestaurantApiHelper(Context context) {
        //this.menuActivityDelegate = (MenuResponseCallback) context;
        this.categoryActivityDelegate = (CategoryResponseCallback) context;
        this.context = context;
    }

    public void getCategories() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://resto.mprog.nl/categories";

        // Request a string response from the provided URL.
        JsonResponseCategoryListener listener = new JsonResponseCategoryListener();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, listener, listener);
        queue.add(jsonObjectRequest);
    }

    public void getCategoryMenuItems() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://resto.mprog.nl/menu?category=entrees";

        // Request a string response from the provided URL.
        JsonResponseMenuListener listener = new JsonResponseMenuListener();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, listener, listener);
        queue.add(jsonObjectRequest);
    }

    private class JsonResponseCategoryListener implements Response.Listener<JSONObject>, Response.ErrorListener {
        @Override
        public void onResponse(JSONObject response) {

            try {
                ArrayList<String> categories = new ArrayList<>();
                JSONArray categoryArray = response.getJSONArray("categories");
                for (int i = 0; i < categoryArray.length(); i++) {
                    categories.add(categoryArray.getString(i));
                }
                categoryActivityDelegate.onResponseSuccess(categories);
            }
            catch (JSONException e) {
                Log.d(TAG, e.toString());
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }

    private class JsonResponseMenuListener implements Response.Listener<JSONObject>, Response.ErrorListener {

        @Override
        public void onResponse(JSONObject response) {
            ArrayList<MenuItem> menuItems = parseMenuItemJson(response);
            menuActivityDelegate.onResponseSuccess(menuItems);
            Log.d(TAG, "success" + response);
        }

        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }

    private ArrayList<MenuItem> parseMenuItemJson(JSONObject response) {

        ArrayList<MenuItem> menuItems = new ArrayList<>();

        try {
            JSONArray items = response.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject menuItemJson = items.getJSONObject(i);

                int id = menuItemJson.getInt("id");
                String name = menuItemJson.getString("name");
                String description = menuItemJson.getString("description");
                float price = menuItemJson.getInt("price");
                String imageUrl = menuItemJson.getString("image_url");

                MenuItem item = new MenuItem(id, name, description, imageUrl, price);
                menuItems.add(item);
            }
        }
        catch (JSONException e) {
            Log.d("JSON", e.toString());
        }

        return menuItems;
    }
}
