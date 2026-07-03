package com.structurax.design.generator.door;

import com.structurax.design.ai.circulation.CirculationPath;
import com.structurax.entity.Door;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoorPlacementEngine {

    public List<Door> placeDoors(List<CirculationPath> paths) {

        List<Door> doors = new ArrayList<>();

        for (CirculationPath path : paths) {

            Room from = path.getFrom();
            Room to = path.getTo();

            Door door = createDoor(from, to);

            if (door != null) {
                doors.add(door);
            }
        }

        return doors;
    }

    private Door createDoor(Room roomA, Room roomB) {

        Door door = new Door();

        door.setDoorType("STANDARD");

        int x =
                (roomA.getPositionX() + roomB.getPositionX()) / 2;

        int y =
                (roomA.getPositionY() + roomB.getPositionY()) / 2;

        door.setPositionX(x);
        door.setPositionY(y);

        door.setWidth(3.0);

        return door;
    }

}