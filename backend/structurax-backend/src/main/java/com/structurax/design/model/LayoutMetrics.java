package com.structurax.design.model;

public class LayoutMetrics {

    private double totalPlotArea;

    private double occupiedArea;

    private double freeArea;

    private double utilizationPercentage;

    private int totalRooms;

    public LayoutMetrics() {
    }

    public double getTotalPlotArea() {
        return totalPlotArea;
    }

    public void setTotalPlotArea(double totalPlotArea) {
        this.totalPlotArea = totalPlotArea;
    }

    public double getOccupiedArea() {
        return occupiedArea;
    }

    public void setOccupiedArea(double occupiedArea) {
        this.occupiedArea = occupiedArea;
    }

    public double getFreeArea() {
        return freeArea;
    }

    public void setFreeArea(double freeArea) {
        this.freeArea = freeArea;
    }

    public double getUtilizationPercentage() {
        return utilizationPercentage;
    }

    public void setUtilizationPercentage(double utilizationPercentage) {
        this.utilizationPercentage = utilizationPercentage;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }
}