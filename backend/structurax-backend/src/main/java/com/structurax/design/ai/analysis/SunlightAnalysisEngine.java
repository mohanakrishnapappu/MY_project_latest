package com.structurax.design.ai.analysis;

import com.structurax.design.ai.constraint.AIConstraintEngine;
import com.structurax.entity.Room;
import com.structurax.entity.Window;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SunlightAnalysisEngine {

    private final AIConstraintEngine constraintEngine;

    public SunlightAnalysisEngine(
            AIConstraintEngine constraintEngine) {

        this.constraintEngine = constraintEngine;

    }

    public double calculateSunlightScore(
            List<Room> rooms,
            List<Window> windows) {

        if (rooms == null || rooms.isEmpty()) {
            return 0;
        }

        if (windows == null) {
            windows = List.of();
        }

        double totalScore = 0;

        for (Room room : rooms) {

            double roomScore = calculateRoomScore(
                    room,
                    windows);

            totalScore += roomScore;

        }

        return totalScore / rooms.size();

    }

    private double calculateRoomScore(
            Room room,
            List<Window> windows) {

        double score = 40;

        if (constraintEngine.roomRequiresSunlight(
                room.getRoomType())) {

            score += 20;

        }

        long nearbyWindows = windows.stream()

                .filter(window ->

                        distance(

                                room.getPositionX(),
                                room.getPositionY(),

                                window.getPositionX(),
                                window.getPositionY()

                        ) < 15

                )

                .count();

        score += nearbyWindows * 15;

        if (room.getPositionX() > 20) {

            score += 10;

        }

        return Math.min(score, 100);

    }

    private double distance(

            int x1,
            int y1,

            int x2,
            int y2) {

        int dx = x1 - x2;

        int dy = y1 - y2;

        return Math.sqrt(dx * dx + dy * dy);

    }

}