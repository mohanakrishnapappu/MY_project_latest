package com.structurax.design.generator.wall;

import com.structurax.entity.Room;
import com.structurax.entity.Wall;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WallGenerationEngine {

    public List<Wall> generateWalls(List<Room> rooms) {

        List<Wall> walls = new ArrayList<>();

        if (rooms == null || rooms.isEmpty()) {
            return walls;
        }

        for (Room room : rooms) {

            int x = room.getPositionX();
            int y = room.getPositionY();

            int width = room.getWidth().intValue();
            int length = room.getLength().intValue();

            // Top Wall
            walls.add(createWall(
                    x,
                    y,
                    x + width,
                    y));

            // Right Wall
            walls.add(createWall(
                    x + width,
                    y,
                    x + width,
                    y + length));

            // Bottom Wall
            walls.add(createWall(
                    x,
                    y + length,
                    x + width,
                    y + length));

            // Left Wall
            walls.add(createWall(
                    x,
                    y,
                    x,
                    y + length));
        }

        return walls;
    }

    private Wall createWall(
            int startX,
            int startY,
            int endX,
            int endY) {

        Wall wall = new Wall();

        wall.setStartX(startX);
        wall.setStartY(startY);

        wall.setEndX(endX);
        wall.setEndY(endY);

        return wall;
    }
}