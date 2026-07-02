package com.structurax.design.ai;

import com.structurax.entity.Door;
import com.structurax.entity.Room;
import com.structurax.entity.Wall;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("aiDoorGenerationEngine")
public class DoorGenerationEngine {

    /**
     * Generates doors for every room.
     *
     * Enterprise Rule (Current Version)
     * ---------------------------------
     * 1. Every room gets one entrance door.
     * 2. Door is placed on the left wall.
     * 3. Door width = 3 feet.
     * 4. Later we will upgrade this engine to
     *    automatically detect shared walls.
     */

    public List<Door> generateDoors(List<Room> rooms,
                                    List<Wall> walls) {

        List<Door> doors = new ArrayList<>();

        for (Room room : rooms) {

            Door door = new Door();

            door.setDoorType(getDoorType(room));

            /*
             * Place door on left wall
             */

            door.setPositionX(room.getPositionX());

            door.setPositionY(
                    room.getPositionY()
                            + (int)(room.getLength() / 2)
            );

            door.setWidth(3.0);

            door.setDesignProject(room.getDesignProject());

            doors.add(door);

        }

        return doors;

    }

    /**
     * Decide door type based on room.
     */

    private String getDoorType(Room room) {

        if (room.getRoomType() == null) {
            return "STANDARD";
        }

        switch (room.getRoomType().toUpperCase()) {

            case "HALL":
                return "MAIN_DOOR";

            case "BEDROOM":
                return "BEDROOM_DOOR";

            case "KITCHEN":
                return "KITCHEN_DOOR";

            case "BATHROOM":
                return "BATHROOM_DOOR";

            default:
                return "STANDARD";
        }

    }

}