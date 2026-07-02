package com.structurax.design.generator.door;

import com.structurax.entity.Door;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoorGenerationEngine {

    public List<Door> generateDoors(List<Room> rooms) {

        List<Door> doors = new ArrayList<>();

        if (rooms == null || rooms.isEmpty()) {
            return doors;
        }

        for (Room room : rooms) {

            String type = room.getRoomType();

            if ("HALL".equalsIgnoreCase(type)) {
                continue;
            }

            Door door = new Door();

            door.setDoorType("SINGLE");

            /*
             * Temporary placement:
             * Middle of the left wall.
             */

            door.setPositionX(room.getPositionX());

            door.setPositionY(
                    room.getPositionY()
                            + room.getLength().intValue() / 2
            );

            door.setWidth(3.0);

            doors.add(door);
        }

        return doors;
    }
}