package com.structurax.design.ai;

import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomPlacementEngine {

    /*
        ---------------------------------------------
        CONFIGURATION
        ---------------------------------------------
     */

    private static final int START_X = 60;
    private static final int START_Y = 60;

    private static final int HORIZONTAL_GAP = 40;
    private static final int VERTICAL_GAP = 50;

    /*
        ---------------------------------------------
        MAIN ALGORITHM
        ---------------------------------------------
     */

    public void placeRooms(List<Room> rooms) {

        int currentX = START_X;
        int currentY = START_Y;

        double tallestRoom = 0;

        int roomsInCurrentRow = 0;

        for (Room room : rooms) {

            room.setPositionX(currentX);
            room.setPositionY(currentY);

            currentX += room.getWidth().intValue()
                    + HORIZONTAL_GAP;

            tallestRoom = Math.max(
                    tallestRoom,
                    room.getLength());

            roomsInCurrentRow++;

            /*
             Every three rooms
             move to next row
             */

            if (roomsInCurrentRow == 3) {

                currentX = START_X;

                currentY += tallestRoom
                        + VERTICAL_GAP;

                tallestRoom = 0;

                roomsInCurrentRow = 0;

            }

        }

        centerHall(rooms);

    }

    /*
        ---------------------------------------------
        CENTER HALL
        ---------------------------------------------
     */

    private void centerHall(List<Room> rooms) {

        Room hall = null;

        double totalX = 0;
        double totalY = 0;

        int count = 0;

        for (Room room : rooms) {

            if ("HALL".equalsIgnoreCase(room.getRoomType())) {

                hall = room;

            } else {

                totalX += room.getPositionX();
                totalY += room.getPositionY();

                count++;

            }

        }

        if (hall == null || count == 0)
            return;

        hall.setPositionX((int) (totalX / count));

        hall.setPositionY((int) (totalY / count));

    }

}