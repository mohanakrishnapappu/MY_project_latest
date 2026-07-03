package com.structurax.design.optimizer;

import com.structurax.design.ai.planner.CollisionDetectionEngine;
import com.structurax.design.dto.ProjectRequestDTO;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LayoutRepairEngine {

    private final CollisionDetectionEngine collisionEngine;

    public LayoutRepairEngine(
            CollisionDetectionEngine collisionEngine) {

        this.collisionEngine = collisionEngine;
    }

    public void repair(ProjectRequestDTO request,
                       List<Room> rooms) {

        int attempts = 0;

        while (!collisionEngine.validate(request, rooms)
                && attempts < 20) {

            repairCollisions(rooms);

            attempts++;
        }

    }

    private void repairCollisions(List<Room> rooms) {

        for (int i = 1; i < rooms.size(); i++) {

            Room room = rooms.get(i);

            room.setPositionX(room.getPositionX() + 5);

            room.setPositionY(room.getPositionY() + 5);

        }

    }

}