package com.structurax.dto;

import com.structurax.entity.*;

import java.util.List;

public class FloorPlanResponse {

    private DesignProject designProject;

    private List<Room> rooms;

    private List<Wall> walls;

    private List<Door> doors;

    private List<Window> windows;

    private List<Furniture> furniture;

    public FloorPlanResponse() {
    }

    public DesignProject getDesignProject() {
        return designProject;
    }

    public void setDesignProject(DesignProject designProject) {
        this.designProject = designProject;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public List<Door> getDoors() {
        return doors;
    }

    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public void setWindows(List<Window> windows) {
        this.windows = windows;
    }

    public List<Furniture> getFurniture() {
        return furniture;
    }

    public void setFurniture(List<Furniture> furniture) {
        this.furniture = furniture;
    }
}