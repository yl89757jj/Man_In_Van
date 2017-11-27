package com.example.luy.maninvan;

import java.io.Serializable;

/**
 * Created by Lulalulali on 11/8/17.
 */

public class User implements Serializable {
    Job[] postedJobs = {};
    String name;
    String phone;//identify user
    String type;
    boolean isRequester;
    int price = 0;
    String description;
    int Pic;
}
