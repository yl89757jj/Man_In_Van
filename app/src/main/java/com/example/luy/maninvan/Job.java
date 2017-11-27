package com.example.luy.maninvan;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Lulalulali on 11/8/17.
 */

public class Job implements Serializable {
    public User user;
    public boolean isRequest;
    public int movingRoom = 0;
    public Time startTime;
    public Time endTime;
    public int price;
    public String description  ="";


    public Job(User user, int movingRoom, Time startTime, Time endTime, int price, String description) {
        this.user = user;
        this.isRequest = this.user.isRequester;
        this.movingRoom = movingRoom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Job{" +
                "user=" + user +
                ", isRequest=" + isRequest +
                ", movingRoom=" + movingRoom +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

}

