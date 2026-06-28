package com.structurax.design.ai;

import com.structurax.entity.Room;
import com.structurax.entity.Window;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WindowGenerationEngine {

    public List<Window> generateWindows(List<Room> rooms) {

        List<Window> windows = new ArrayList<>();

        for (Room room : rooms) {

            Window window = new Window();

            window.setWindowType("SLIDING");

            window.setWidth(5.0);

            /*
             Place window at top wall
             */

            window.setPositionX(
                    room.getPositionX() + (int)(room.getWidth() / 2)
            );

            window.setPositionY(
                    room.getPositionY()
            );

            windows.add(window);
        }

        return windows;
    }
}