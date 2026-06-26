package com.structurax.dto.design;

import java.util.List;

public class FloorPlanDetailsResponse {

    private Long projectId;

    private String projectName;

    private Double plotArea;

    private Integer floors;

    private Integer bedrooms;

    private Integer bathrooms;

    private Integer kitchens;

    private Integer balconies;

    private Integer parkingSpaces;

    private List<RoomResponse> rooms;

    private List<WallResponse> walls;

    private List<DoorResponse> doors;

    private List<WindowResponse> windows;

    private List<FurnitureResponse> furniture;

    public FloorPlanDetailsResponse() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(Double plotArea) {
        this.plotArea = plotArea;
    }

    public Integer getFloors() {
        return floors;
    }

    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getKitchens() {
        return kitchens;
    }

    public void setKitchens(Integer kitchens) {
        this.kitchens = kitchens;
    }

    public Integer getBalconies() {
        return balconies;
    }

    public void setBalconies(Integer balconies) {
        this.balconies = balconies;
    }

    public Integer getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(Integer parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public List<RoomResponse> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomResponse> rooms) {
        this.rooms = rooms;
    }

    public List<WallResponse> getWalls() {
        return walls;
    }

    public void setWalls(List<WallResponse> walls) {
        this.walls = walls;
    }

    public List<DoorResponse> getDoors() {
        return doors;
    }

    public void setDoors(List<DoorResponse> doors) {
        this.doors = doors;
    }

    public List<WindowResponse> getWindows() {
        return windows;
    }

    public void setWindows(List<WindowResponse> windows) {
        this.windows = windows;
    }

    public List<FurnitureResponse> getFurniture() {
        return furniture;
    }

    public void setFurniture(List<FurnitureResponse> furniture) {
        this.furniture = furniture;
    }
}