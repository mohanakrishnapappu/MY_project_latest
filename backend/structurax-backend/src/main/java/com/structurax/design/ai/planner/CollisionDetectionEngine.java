package com.structurax.design.ai.planner;

import com.structurax.design.dto.ProjectRequestDTO;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollisionDetectionEngine {

    public boolean validate(ProjectRequestDTO request,
                            List<Room> rooms) {

        if (rooms == null || rooms.isEmpty()) {
            return false;
        }

        if (!insidePlot(request, rooms)) {
            return false;
        }

        return !hasCollision(rooms);
    }

    public boolean hasCollision(List<Room> rooms) {

        for (int i = 0; i < rooms.size(); i++) {

            Room r1 = rooms.get(i);

            for (int j = i + 1; j < rooms.size(); j++) {

                Room r2 = rooms.get(j);

                if (intersects(r1, r2)) {
                    return true;
                }

            }

        }

        return false;
    }

    private boolean insidePlot(ProjectRequestDTO request,
                               List<Room> rooms) {

        for (Room room : rooms) {

            double maxX = room.getPositionX() + room.getWidth();
            double maxY = room.getPositionY() + room.getLength();

            if (maxX > request.getPlotWidth()) {
                return false;
            }

            if (maxY > request.getPlotLength()) {
                return false;
            }

        }

        return true;
    }

    private boolean intersects(Room a, Room b) {

        return !(

                a.getPositionX() + a.getWidth() <= b.getPositionX()

                        ||

                        b.getPositionX() + b.getWidth() <= a.getPositionX()

                        ||

                        a.getPositionY() + a.getLength() <= b.getPositionY()

                        ||

                        b.getPositionY() + b.getLength() <= a.getPositionY()

        );
    }
}