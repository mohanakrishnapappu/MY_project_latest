package com.structurax.design.ai.planner;

import com.structurax.design.ai.constraint.AIConstraintEngine;
import com.structurax.design.ai.rules.RoomRule;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomAdjacencyEngine {

    private final AIConstraintEngine constraintEngine;

    public RoomAdjacencyEngine(AIConstraintEngine constraintEngine) {
        this.constraintEngine = constraintEngine;
    }

    public boolean validateAdjacency(List<Room> rooms) {

        for (Room room : rooms) {

            RoomRule rule =
                    constraintEngine.getRule(room.getRoomType());

            if (rule == null)
                continue;

            for (String preferred :
                    rule.getPreferredAdjacentRooms()) {

                boolean found = rooms.stream()
                        .anyMatch(r ->
                                preferred.equalsIgnoreCase(
                                        r.getRoomType()));

                if (!found) {

                    System.out.println(
                            room.getRoomType()
                                    + " should be adjacent to "
                                    + preferred);

                }

            }

        }

        return true;

    }

}