package com.structurax.design.algorithm;

import com.structurax.design.model.LayoutMetrics;
import com.structurax.entity.DesignProject;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpaceUtilizationCalculator {

    public LayoutMetrics calculate(
            DesignProject project,
            List<Room> rooms) {

        LayoutMetrics metrics = new LayoutMetrics();

        double occupied = 0;

        for (Room room : rooms) {

            occupied += room.getLength() * room.getWidth();

        }

        double total = project.getPlotArea();

        double free = total - occupied;

        double percentage = (occupied / total) * 100;

        metrics.setTotalPlotArea(total);
        metrics.setOccupiedArea(occupied);
        metrics.setFreeArea(free);
        metrics.setUtilizationPercentage(percentage);
        metrics.setTotalRooms(rooms.size());

        return metrics;

    }

}