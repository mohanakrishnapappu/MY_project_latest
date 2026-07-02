package com.structurax.design.ai.analysis;

import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CirculationAnalysisEngine {

    public double calculateCirculationScore(List<Room> rooms) {

        if (rooms == null || rooms.isEmpty()) {
            return 0;
        }

        Room hall = findRoom(rooms, "HALL");

        if (hall == null) {
            return 20;
        }

        double totalDistance = 0;

        int connections = 0;

        for (Room room : rooms) {

            if ("HALL".equalsIgnoreCase(room.getRoomType())) {
                continue;
            }

            totalDistance += distance(hall, room);

            connections++;
        }

        if (connections == 0) {
            return 0;
        }

        double averageDistance = totalDistance / connections;

        /*
         * Lower distance = Better circulation
         */

        double score = 100 - averageDistance;

        return Math.max(0, Math.min(100, score));

    }

    private Room findRoom(List<Room> rooms, String type) {

        return rooms.stream()

                .filter(r ->
                        type.equalsIgnoreCase(r.getRoomType()))

                .findFirst()

                .orElse(null);

    }

    private double distance(Room a, Room b) {

        double dx = a.getPositionX() - b.getPositionX();

        double dy = a.getPositionY() - b.getPositionY();

        return Math.sqrt(dx * dx + dy * dy);

    }

}