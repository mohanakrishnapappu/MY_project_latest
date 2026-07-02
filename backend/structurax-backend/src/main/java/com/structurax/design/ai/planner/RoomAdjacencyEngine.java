package com.structurax.design.ai.planner;

import com.structurax.design.ai.constraint.AIConstraintEngine;
import com.structurax.design.ai.rules.RoomRule;
import com.structurax.entity.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomAdjacencyEngine {

    private static final Logger logger =
            LoggerFactory.getLogger(RoomAdjacencyEngine.class);

    private final AIConstraintEngine constraintEngine;

    public RoomAdjacencyEngine(AIConstraintEngine constraintEngine) {
        this.constraintEngine = constraintEngine;
    }

    public boolean validateAdjacency(List<Room> rooms) {

        boolean valid = true;

        for (Room room : rooms) {

            RoomRule rule = constraintEngine.getRule(room.getRoomType());

            if (rule == null) {
                continue;
            }

            for (String preferred : rule.getPreferredAdjacentRooms()) {

                boolean found = rooms.stream()
                        .anyMatch(r ->
                                preferred.equalsIgnoreCase(r.getRoomType()));

                if (!found) {

                    logger.warn(
                            "{} should be adjacent to {}",
                            room.getRoomType(),
                            preferred);

                    valid = false;
                }
            }
        }

        return valid;
    }
}