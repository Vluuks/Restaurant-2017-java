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
 *  Helper class that handles the calls to the restaurant API. It can retrieve all menu items of
 *  a certain category and the list of available categories.
 */
public class RestaurantApiHelper {

    /* Interfaces that handle the callback to the corresponding activities. */
    public interface CategoriesCallback {
        void onResponseSuccess(ArrayList<String> responseList);
    }

    public interface MenuItemsCallback {
        void onResponseSuccess(ArrayList<MenuItem> responseList);
    }

    private static final String TAG = "RestaurantApi";
    private Context context;

    public RestaurantApiHelper(Context context) {
        this.context = context;
    }

    /**
     *  Retrieves all available categories from the API.
     */
    public void getCategoriesAnon(final RestaurantApiHelper.CategoriesCallback callbackHandler) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://resto.mprog.nl/categories";

        // Request a string response from the provided URL.
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<String> categories = new ArrayList<>();
                    JSONArray categoryArray = response.getJSONArray("categories");
                    for (int i = 0; i < categoryArray.length(); i++) {
                        categories.add(categoryArray.getString(i));
                    }
                    callbackHandler.onResponseSuccess(categories);
                }
                catch (JSONException e) {
                    Log.d(TAG, e.toString());
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
            }
        };

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, responseListener, errorListener);
        queue.add(jsonObjectRequest);
    }

    /**
     *  Retrieves menu items that belong to a certain category as specified by parameter.
     */
    public void getCategoryMenuItemsAnon(String category, final RestaurantApiHelper.MenuItemsCallback callbackHandler) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/menu?category=" + category;

        // Request a string response from the provided URL.
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArrayList<MenuItem> menuItems = parseMenuItemJson(response);
                callbackHandler.onResponseSuccess(menuItems);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
            }
        };

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, responseListener, errorListener);
        queue.add(jsonObjectRequest);
    }

    /**
     *  Parses the JSONObject containing menu items to an arraylist.
     */
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
        catch (JSONException error) {
            Log.d(TAG, error.toString());
        }

        return menuItems;
    }
}
