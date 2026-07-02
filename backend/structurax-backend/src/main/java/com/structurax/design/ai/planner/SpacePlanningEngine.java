package com.structurax.design.ai.planner;

import com.structurax.design.dto.ProjectRequestDTO;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpacePlanningEngine {

    public void arrangeRooms(ProjectRequestDTO request,
                             List<Room> rooms) {

        if (rooms == null || rooms.isEmpty()) {
            return;
        }

        int currentX = 0;
        int currentY = 0;

        int rowHeight = 0;

        int plotWidth = (int) request.getPlotWidth();

        for (Room room : rooms) {

            int roomWidth = room.getWidth().intValue();
            int roomLength = room.getLength().intValue();

            /*
             * Move to next row if room exceeds plot width
             */
            if (currentX + roomWidth > plotWidth) {

                currentX = 0;

                currentY += rowHeight + 2;

                rowHeight = 0;
            }

            room.setPositionX(currentX);
            room.setPositionY(currentY);

            currentX += roomWidth + 2;

            rowHeight = Math.max(rowHeight, roomLength);
        }
    }
}