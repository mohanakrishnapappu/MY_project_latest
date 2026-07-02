package com.structurax.design.ai;

import com.structurax.entity.DesignProject;
import com.structurax.entity.Room;
import com.structurax.entity.Wall;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("aiWallGenerationEngine")
public class WallGenerationEngine {

    /*
     * Every room generates
     * Top Wall
     * Right Wall
     * Bottom Wall
     * Left Wall
     */

    public List<Wall> generateWalls(List<Room> rooms) {

        List<Wall> walls = new ArrayList<>();

        for (Room room : rooms) {
            walls.addAll(generateRoomWalls(room));
        }

        return walls;
    }

    /**
     * Generate four walls for one room
     */
    private List<Wall> generateRoomWalls(Room room) {

        List<Wall> roomWalls = new ArrayList<>();

        int x = room.getPositionX();
        int y = room.getPositionY();

        int width = room.getWidth().intValue();
        int length = room.getLength().intValue();

        DesignProject project = room.getDesignProject();

        /*
         * -----------------------------
         * TOP WALL
         * -----------------------------
         */

        Wall top = new Wall();
        top.setStartX(x);
        top.setStartY(y);
        top.setEndX(x + width);
        top.setEndY(y);
        top.setDesignProject(project);
        roomWalls.add(top);

        /*
         * -----------------------------
         * RIGHT WALL
         * -----------------------------
         */

        Wall right = new Wall();
        right.setStartX(x + width);
        right.setStartY(y);
        right.setEndX(x + width);
        right.setEndY(y + length);
        right.setDesignProject(project);
        roomWalls.add(right);

        /*
         * -----------------------------
         * BOTTOM WALL
         * -----------------------------
         */

        Wall bottom = new Wall();
        bottom.setStartX(x);
        bottom.setStartY(y + length);
        bottom.setEndX(x + width);
        bottom.setEndY(y + length);
        bottom.setDesignProject(project);
        roomWalls.add(bottom);

        /*
         * -----------------------------
         * LEFT WALL
         * -----------------------------
         */

        Wall left = new Wall();
        left.setStartX(x);
        left.setStartY(y);
        left.setEndX(x);
        left.setEndY(y + length);
        left.setDesignProject(project);
        roomWalls.add(left);

        return roomWalls;
    }
}