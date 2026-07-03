package com.structurax.design.ai.window;

import com.structurax.entity.Room;
import com.structurax.entity.Window;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WindowOptimizationEngine {

    public List<Window> optimizeWindows(List<Room> rooms) {

        List<Window> windows = new ArrayList<>();

        for (Room room : rooms) {

            windows.addAll(generateForRoom(room));
        }

        return windows;
    }

    private List<Window> generateForRoom(Room room) {

        List<Window> windows = new ArrayList<>();

        String type = room.getRoomType().toUpperCase();

        if (type.equals("BEDROOM") || type.equals("HALL")) {
            windows.add(createWindow(room, "EAST"));
        }

        if (type.equals("KITCHEN")) {
            windows.add(createWindow(room, "NORTH"));
            windows.add(createWindow(room, "WEST"));
        }

        if (type.equals("BATHROOM")) {
            windows.add(createWindow(room, "NORTH"));
        }

        return windows;
    }

    private Window createWindow(Room room, String direction) {

        Window window = new Window();
        window.setWindowType("STANDARD");

        int x = 0;
        int y = 0;

        int px = room.getPositionX();
        int py = room.getPositionY();

        int width = room.getWidth() != null
                ? room.getWidth().intValue()
                : 10;

        if (direction.equals("EAST")) {

            x = px + width;
            y = py + 2;
        }

        if (direction.equals("WEST")) {

            x = px;
            y = py + 2;
        }

        if (direction.equals("NORTH")) {

            x = px + 2;
            y = py;
        }

        window.setPositionX(x);
        window.setPositionY(y);

        window.setWidth(4.0);

        return window;
    }
}