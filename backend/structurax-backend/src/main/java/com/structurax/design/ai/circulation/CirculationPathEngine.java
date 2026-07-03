package com.structurax.design.ai.circulation;

import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CirculationPathEngine {

    public List<CirculationPath> generatePaths(
            Map<Room, List<Room>> graph) {

        List<CirculationPath> paths = new ArrayList<>();

        for (Room source : graph.keySet()) {

            List<Room> neighbours = graph.get(source);

            if (neighbours == null) {
                continue;
            }

            for (Room destination : neighbours) {

                CirculationPath path = new CirculationPath();

                path.setFrom(source);

                path.setTo(destination);

                path.setDistance(
                        calculateDistance(source, destination));

                paths.add(path);
            }
        }

        return paths;
    }

    private double calculateDistance(Room a, Room b) {

        double dx =
                a.getPositionX() - b.getPositionX();

        double dy =
                a.getPositionY() - b.getPositionY();

        return Math.sqrt(dx * dx + dy * dy);
    }

}