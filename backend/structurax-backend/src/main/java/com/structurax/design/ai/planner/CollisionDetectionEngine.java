package com.structurax.design.ai.planner;

import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollisionDetectionEngine {

    public boolean hasCollision(List<Room> rooms) {

        for (int i = 0; i < rooms.size(); i++) {

            Room r1 = rooms.get(i);

            for (int j = i + 1; j < rooms.size(); j++) {

                Room r2 = rooms.get(j);

                if (intersects(r1, r2))
                    return true;

            }

        }

        return false;

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