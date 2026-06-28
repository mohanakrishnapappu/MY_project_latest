package com.structurax.design.ai.planner;

import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpaceOptimizationEngine {

    public double calculateUsedArea(List<Room> rooms) {

        double area = 0;

        for (Room room : rooms) {
            area += room.getLength() * room.getWidth();
        }

        return area;
    }

    public double calculateEfficiency(double plotArea, List<Room> rooms) {

        if (plotArea <= 0) {
            return 0;
        }

        double usedArea = calculateUsedArea(rooms);

        double efficiency = usedArea / plotArea;

        return Math.min(efficiency, 1.0); // cap at 100%
    }
}