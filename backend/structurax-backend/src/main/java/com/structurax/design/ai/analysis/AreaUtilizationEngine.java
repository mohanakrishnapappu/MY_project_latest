package com.structurax.design.ai.analysis;

import com.structurax.design.dto.ProjectRequestDTO;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AreaUtilizationEngine {

    public double calculateScore(
            ProjectRequestDTO request,
            List<Room> rooms) {

        if (request == null || rooms == null || rooms.isEmpty()) {
            return 0;
        }

        double plotArea =
                request.getPlotLength() * request.getPlotWidth();

        if (plotArea <= 0) {
            return 0;
        }

        double roomArea = 0;

        for (Room room : rooms) {

            roomArea +=
                    room.getLength()
                    * room.getWidth();

        }

        double utilization = roomArea / plotArea;

        /*
         * Ideal utilization:
         * 70%–85%
         */

        if (utilization >= 0.70 && utilization <= 0.85) {
            return 100;
        }

        if (utilization < 0.70) {

            return utilization / 0.70 * 100;

        }

        return Math.max(
                40,
                100 - ((utilization - 0.85) * 200));
    }
}