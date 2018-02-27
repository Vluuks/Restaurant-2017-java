package com.example.gebruiker.restaurantapp;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by Gebruiker on 27-2-2018.
 */

public class MenuItem implements Serializable {

    private int id;
    private String name, category, description;
    private URL imageUrl;
    private float price;

    public MenuItem(int id, String name, String category, String description, URL imageUrl, float price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }
}
