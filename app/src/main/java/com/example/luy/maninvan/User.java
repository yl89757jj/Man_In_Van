package com.example.luy.maninvan;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.io.Serializable;

/**
 * Created by Lulalulali on 11/8/17.
 */

public class User implements Serializable {
    Job[] postedJobs = {};
    String name;
    String phone;//identify user
    Bitmap Pic;
    int id;
}
