package com.structurax.design.ai.circulation;

import com.structurax.entity.Room;

public class CirculationPath {

    private Room from;

    private Room to;

    private double distance;

    public Room getFrom() {
        return from;
    }

    public void setFrom(Room from) {
        this.from = from;
    }

    public Room getTo() {
        return to;
    }

    public void setTo(Room to) {
        this.to = to;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}