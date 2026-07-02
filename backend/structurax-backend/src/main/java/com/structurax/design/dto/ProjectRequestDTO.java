package com.structurax.design.dto;

public class ProjectRequestDTO {

    private double plotLength;
    private double plotWidth;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private boolean parkingRequired;
    private boolean gardenRequired;

    public ProjectRequestDTO() {
    }

    public double getPlotLength() {
        return plotLength;
    }

    public void setPlotLength(double plotLength) {
        this.plotLength = plotLength;
    }

    public double getPlotWidth() {
        return plotWidth;
    }

    public void setPlotWidth(double plotWidth) {
        this.plotWidth = plotWidth;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public boolean isParkingRequired() {
        return parkingRequired;
    }

    public void setParkingRequired(boolean parkingRequired) {
        this.parkingRequired = parkingRequired;
    }

    public boolean isGardenRequired() {
        return gardenRequired;
    }

    public void setGardenRequired(boolean gardenRequired) {
        this.gardenRequired = gardenRequired;
    }
}