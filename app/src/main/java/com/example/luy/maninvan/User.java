package com.example.luy.maninvan;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lulalulali on 11/8/17.
 */

public class User implements Serializable {
    ArrayList<Job>jobs;
    String name;
    String phone;//identify user
    Bitmap Pic;
    int id;
    boolean isDriver;

    public User(String name, String phone, boolean isDriver) {
        this.name = name;
        this.phone = phone;
        this.isDriver = isDriver;
    }
}
