package com.structurax.design.ai.rules;

import java.util.ArrayList;
import java.util.List;

public class RoomRule {

    private String roomType;

    private double minimumWidth;

    private double minimumLength;

    private double preferredArea;

    private boolean sunlightRequired;

    private boolean ventilationRequired;

    private boolean privacyRequired;

    private List<String> preferredAdjacentRooms =
            new ArrayList<>();

    private List<String> forbiddenAdjacentRooms =
            new ArrayList<>();

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getMinimumWidth() {
        return minimumWidth;
    }

    public void setMinimumWidth(double minimumWidth) {
        this.minimumWidth = minimumWidth;
    }

    public double getMinimumLength() {
        return minimumLength;
    }

    public void setMinimumLength(double minimumLength) {
        this.minimumLength = minimumLength;
    }

    public double getPreferredArea() {
        return preferredArea;
    }

    public void setPreferredArea(double preferredArea) {
        this.preferredArea = preferredArea;
    }

    public boolean isSunlightRequired() {
        return sunlightRequired;
    }

    public void setSunlightRequired(boolean sunlightRequired) {
        this.sunlightRequired = sunlightRequired;
    }

    public boolean isVentilationRequired() {
        return ventilationRequired;
    }

    public void setVentilationRequired(boolean ventilationRequired) {
        this.ventilationRequired = ventilationRequired;
    }

    public boolean isPrivacyRequired() {
        return privacyRequired;
    }

    public void setPrivacyRequired(boolean privacyRequired) {
        this.privacyRequired = privacyRequired;
    }

    public List<String> getPreferredAdjacentRooms() {
        return preferredAdjacentRooms;
    }

    public void setPreferredAdjacentRooms(
            List<String> preferredAdjacentRooms) {
        this.preferredAdjacentRooms = preferredAdjacentRooms;
    }

    public List<String> getForbiddenAdjacentRooms() {
        return forbiddenAdjacentRooms;
    }

    public void setForbiddenAdjacentRooms(
            List<String> forbiddenAdjacentRooms) {
        this.forbiddenAdjacentRooms = forbiddenAdjacentRooms;
    }

}