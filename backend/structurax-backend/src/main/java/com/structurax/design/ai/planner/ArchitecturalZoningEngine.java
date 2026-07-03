package com.structurax.design.ai.planner;

import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ArchitecturalZoningEngine {

    public Map<String, String> generateZones(List<Room> rooms) {

        Map<String, String> zoning = new HashMap<>();

        if (rooms == null) {
            return zoning;
        }

        for (Room room : rooms) {

            zoning.put(

                    room.getRoomType(),

                    determineZone(room.getRoomType())

            );

        }

        return zoning;
    }

    private String determineZone(String roomType) {

        switch (roomType.toUpperCase()) {

            case "HALL":

            case "LIVING":

            case "DINING":

                return "PUBLIC";

            case "KITCHEN":

            case "UTILITY":

                return "SEMI_PRIVATE";

            case "BEDROOM":

                return "PRIVATE";

            case "BATHROOM":

            case "STORE":

            case "LAUNDRY":

                return "SERVICE";

            default:

                return "OTHER";
        }

    }

}