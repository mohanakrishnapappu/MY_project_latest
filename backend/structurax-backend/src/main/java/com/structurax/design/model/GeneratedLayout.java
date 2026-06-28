// package com.structurax.design.model;

// import com.structurax.entity.*;

// import java.util.ArrayList;
// import java.util.List;

// public class GeneratedLayout {

//     private List<Room> rooms = new ArrayList<>();

//     private List<Wall> walls = new ArrayList<>();

//     private List<Door> doors = new ArrayList<>();

//     private List<Window> windows = new ArrayList<>();

//     public List<Room> getRooms() {
//         return rooms;
//     }

//     public void setRooms(List<Room> rooms) {
//         this.rooms = rooms;
//     }

//     public List<Wall> getWalls() {
//         return walls;
//     }

//     public void setWalls(List<Wall> walls) {
//         this.walls = walls;
//     }

//     public List<Door> getDoors() {
//         return doors;
//     }

//     public void setDoors(List<Door> doors) {
//         this.doors = doors;
//     }

//     public List<Window> getWindows() {
//         return windows;
//     }

//     public void setWindows(List<Window> windows) {
//         this.windows = windows;
//     }
// }

package com.structurax.design.model;

import com.structurax.entity.Door;
import com.structurax.entity.Room;
import com.structurax.entity.Wall;
import com.structurax.entity.Window;

import java.util.List;

public class GeneratedLayout {

    private List<Room> rooms;

    private List<Wall> walls;

    private List<Door> doors;

    private List<Window> windows;

    public GeneratedLayout() {
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

}