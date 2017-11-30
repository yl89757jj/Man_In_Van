package com.example.luy.maninvan;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Lulalulali on 11/8/17.
 */

public class Job implements Serializable {
    public User user;
    public boolean taken = false;
    public int movingRoom = 0;
    public String startTime;
    public String endTime;
    public int price;
    public String description  ="";


    public Job(User user, int movingRoom, String startTime, String endTime, int price, String description) {
        this.user = user;
        this.movingRoom = movingRoom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.description = description;
    }

    public void beTook(){
        this.taken = true;
    }


    @Override
    public String toString() {
        return "Job{" +
                "user=" + user +
                ", movingRoom=" + movingRoom +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

}

