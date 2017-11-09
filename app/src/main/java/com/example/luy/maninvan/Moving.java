package com.example.luy.maninvan;

import java.sql.Time;

/**
 * Created by Lulalulali on 11/8/17.
 */

public class Moving {
    private int movingRoom = 0;
    private Time startTime;
    private Time endTime;
    private double maxPrice = 0;
    private String description  ="";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getMovingRoom() {
        return movingRoom;
    }

    public void setMovingRoom(int movingRoom) {
        this.movingRoom = movingRoom;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }


    @Override
    public String toString() {
        return "Moving{" +
                "movingRoom=" + movingRoom +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", maxPrice=" + maxPrice +
                ", description='" + description + '\'' +
                '}';
    }
}

