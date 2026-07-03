package com.structurax.design.model;

import com.structurax.design.ai.scoring.LayoutScore;
import com.structurax.entity.Corridor;
import com.structurax.entity.Door;
import com.structurax.entity.Room;
import com.structurax.entity.Wall;
import com.structurax.entity.Window;

import java.util.List;

public class GeneratedLayout {

    /*
     ---------------------------------------------
     Generated Rooms
     ---------------------------------------------
     */

    private List<Room> rooms;

    /*
     ---------------------------------------------
     Generated Walls
     ---------------------------------------------
     */

    private List<Wall> walls;

    /*
     ---------------------------------------------
     Generated Doors
     ---------------------------------------------
     */

    private List<Door> doors;

    /*
     ---------------------------------------------
     Generated Windows
     ---------------------------------------------
     */

    private List<Window> windows;

    /*
     ---------------------------------------------
     Generated Corridors
     ---------------------------------------------
     */

    private List<Corridor> corridors;

    /*
     ---------------------------------------------
     AI Evaluation Score
     ---------------------------------------------
     */

    private LayoutScore layoutScore;

    public GeneratedLayout() {
    }

    // Rooms

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    // Walls

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    // Doors

    public List<Door> getDoors() {
        return doors;
    }

    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }

    // Windows

    public List<Window> getWindows() {
        return windows;
    }

    public void setWindows(List<Window> windows) {
        this.windows = windows;
    }

    // Corridors

    public List<Corridor> getCorridors() {
        return corridors;
    }

    public void setCorridors(List<Corridor> corridors) {
        this.corridors = corridors;
    }

    // Score

    public LayoutScore getLayoutScore() {
        return layoutScore;
    }

    public void setLayoutScore(LayoutScore layoutScore) {
        this.layoutScore = layoutScore;
    }

}