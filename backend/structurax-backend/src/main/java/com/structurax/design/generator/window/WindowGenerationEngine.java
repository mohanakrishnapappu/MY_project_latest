package com.structurax.design.generator.window;

import com.structurax.entity.Room;
import com.structurax.entity.Window;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("generatorWindowGenerationEngine")
public class WindowGenerationEngine {

    public List<Window> generateWindows(List<Room> rooms) {

        List<Window> windows = new ArrayList<>();

        if (rooms == null || rooms.isEmpty()) {
            return windows;
        }

        for (Room room : rooms) {

            int numberOfWindows = getWindowCount(room.getRoomType());

            for (int i = 0; i < numberOfWindows; i++) {

                Window window = new Window();

                window.setWindowType("SLIDING");

                /*
                 * Temporary placement
                 * Along top wall
                 */

                window.setPositionX(
                        room.getPositionX()
                                + ((i + 1)
                                * room.getWidth().intValue())
                                / (numberOfWindows + 1));

                window.setPositionY(room.getPositionY());

                window.setWidth(4.0);

                windows.add(window);
            }
        }

        return windows;
    }

    private int getWindowCount(String roomType) {

        switch (roomType.toUpperCase()) {

            case "BEDROOM":
                return 2;

            case "HALL":
                return 3;

            case "KITCHEN":
                return 1;

            case "BATHROOM":
                return 1;

            default:
                return 1;
        }
    }
}