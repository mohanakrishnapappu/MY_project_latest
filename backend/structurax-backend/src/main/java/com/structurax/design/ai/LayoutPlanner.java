package com.structurax.design.ai;

import com.structurax.entity.DesignProject;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LayoutPlanner {

    public List<Room> createRooms(DesignProject project) {

        List<Room> rooms = new ArrayList<>();

        int startX = 40;
        int startY = 40;

        for (int i = 0; i < project.getBedrooms(); i++) {

            Room room = new Room();

            room.setRoomType("BEDROOM");
            room.setLength(12.0);
            room.setWidth(10.0);
            room.setPositionX(startX);
            room.setPositionY(startY);
            room.setDesignProject(project);

            rooms.add(room);

            startX += 150;
        }

        Room kitchen = new Room();
        kitchen.setRoomType("KITCHEN");
        kitchen.setLength(10.0);
        kitchen.setWidth(8.0);
        kitchen.setPositionX(40);
        kitchen.setPositionY(220);
        kitchen.setDesignProject(project);

        rooms.add(kitchen);

        Room hall = new Room();
        hall.setRoomType("HALL");
        hall.setLength(20.0);
        hall.setWidth(15.0);
        hall.setPositionX(250);
        hall.setPositionY(220);
        hall.setDesignProject(project);

        rooms.add(hall);

        return rooms;
    }
}