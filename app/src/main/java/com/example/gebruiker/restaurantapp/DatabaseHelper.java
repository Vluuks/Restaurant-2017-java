package com.example.gebruiker.restaurantapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gebruiker on 27-2-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "order";
    private static final String DISH_NAME = "dishname";
    private static final String DISH_ID = "dishid";
    private static final String DISH_QUANTITY = "dishquantity";
    private static final String DISH_PRICE = "dishprice";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
