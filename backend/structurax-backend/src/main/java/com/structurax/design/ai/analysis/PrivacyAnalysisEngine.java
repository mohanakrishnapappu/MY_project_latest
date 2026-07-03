package com.structurax.design.ai.analysis;

import com.structurax.design.ai.constraint.AIConstraintEngine;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrivacyAnalysisEngine {

    private final AIConstraintEngine constraintEngine;

    public PrivacyAnalysisEngine(
            AIConstraintEngine constraintEngine) {

        this.constraintEngine = constraintEngine;
    }

    public double calculatePrivacyScore(List<Room> rooms) {

        if (rooms == null || rooms.isEmpty()) {
            return 0;
        }

        double totalScore = 0;

        for (Room room : rooms) {

            totalScore += evaluateRoomPrivacy(room, rooms);

        }

        return totalScore / rooms.size();
    }

    private double evaluateRoomPrivacy(

            Room room,
            List<Room> rooms) {

        double score = 70;

        if (constraintEngine.roomRequiresPrivacy(room.getRoomType())) {

            score += 20;

        }

        for (Room other : rooms) {

            if (room == other) {
                continue;
            }

            double distance = distance(room, other);

            if (distance < 8) {

                score -= 10;

            }

            if ("BEDROOM".equalsIgnoreCase(room.getRoomType())
                    &&
                    "KITCHEN".equalsIgnoreCase(other.getRoomType())
                    &&
                    distance < 12) {

                score -= 15;

            }

            if ("BEDROOM".equalsIgnoreCase(room.getRoomType())
                    &&
                    "HALL".equalsIgnoreCase(other.getRoomType())
                    &&
                    distance < 10) {

                score -= 10;

            }

        }

        return Math.max(0, Math.min(score, 100));
    }

    private double distance(Room a, Room b) {

        int dx = a.getPositionX() - b.getPositionX();

        int dy = a.getPositionY() - b.getPositionY();

        return Math.sqrt(dx * dx + dy * dy);

    }

}