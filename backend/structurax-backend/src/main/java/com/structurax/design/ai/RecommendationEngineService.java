package com.structurax.design.ai;

import com.structurax.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationEngineService {

    public List<DesignSuggestion> generate(
            DesignProject project,
            List<Room> rooms,
            List<Wall> walls,
            List<Door> doors,
            List<Window> windows,
            List<Furniture> furniture) {

        List<DesignSuggestion> recommendations = new ArrayList<>();

        checkRoomFlow(rooms, recommendations);
        checkKitchenPlacement(rooms, recommendations);
        checkBathroomPlacement(rooms, recommendations);
        checkLighting(windows, rooms, recommendations);
        checkFurnitureSpacing(furniture, recommendations);
        checkEntranceDesign(doors, recommendations);
        checkPlotUtilization(project, rooms, recommendations);

        return recommendations;
    }

    // -------------------------------
    // 1. Room Flow Analysis
    // -------------------------------
    private void checkRoomFlow(List<Room> rooms,
                               List<DesignSuggestion> recommendations) {

        if (rooms.size() < 3) {
            recommendations.add(new DesignSuggestion(
                    "Poor Layout Flow",
                    "Design has very few rooms, layout may not be functional.",
                    "WARNING",
                    "ROOM_FLOW_ANALYSIS"
            ));
        }
    }

    // -------------------------------
    // 2. Kitchen Placement
    // -------------------------------
    private void checkKitchenPlacement(List<Room> rooms,
                                       List<DesignSuggestion> recommendations) {

        boolean kitchenExists = rooms.stream()
                .anyMatch(r -> r.getRoomType().equalsIgnoreCase("KITCHEN"));

        if (!kitchenExists) {
            recommendations.add(new DesignSuggestion(
                    "Kitchen Missing",
                    "Add a kitchen near dining area for better usability.",
                    "HIGH",
                    "KITCHEN_PLACEMENT"
            ));
        }
    }

    // -------------------------------
    // 3. Bathroom Placement
    // -------------------------------
    private void checkBathroomPlacement(List<Room> rooms,
                                        List<DesignSuggestion> recommendations) {

        long bathrooms = rooms.stream()
                .filter(r -> r.getRoomType().equalsIgnoreCase("BATHROOM"))
                .count();

        if (bathrooms == 0) {
            recommendations.add(new DesignSuggestion(
                    "No Bathroom Found",
                    "Every house must include at least one bathroom.",
                    "HIGH",
                    "BATHROOM_PLACEMENT"
            ));
        }
    }

    // -------------------------------
    // 4. Lighting Analysis
    // -------------------------------
    private void checkLighting(List<Window> windows,
                               List<Room> rooms,
                               List<DesignSuggestion> recommendations) {

        if (windows.size() < rooms.size()) {
            recommendations.add(new DesignSuggestion(
                    "Poor Lighting",
                    "Add more windows for natural lighting in each room.",
                    "MEDIUM",
                    "LIGHTING_ANALYSIS"
            ));
        }
    }

    // -------------------------------
    // 5. Furniture Spacing
    // -------------------------------
    private void checkFurnitureSpacing(List<Furniture> furniture,
                                       List<DesignSuggestion> recommendations) {

        if (furniture.isEmpty()) {
            recommendations.add(new DesignSuggestion(
                    "Empty Interior",
                    "Add basic furniture to improve usability and realism.",
                    "INFO",
                    "FURNITURE_SPACING"
            ));
        }
    }

    // -------------------------------
    // 6. Entrance Design
    // -------------------------------
    private void checkEntranceDesign(List<Door> doors,
                                     List<DesignSuggestion> recommendations) {

        if (doors.isEmpty()) {
            recommendations.add(new DesignSuggestion(
                    "No Entry Point",
                    "Add a main entrance door for accessibility.",
                    "HIGH",
                    "ENTRANCE_DESIGN"
            ));
        }
    }

    // -------------------------------
    // 7. Plot Utilization
    // -------------------------------
    private void checkPlotUtilization(DesignProject project,
                                      List<Room> rooms,
                                      List<DesignSuggestion> recommendations) {

        double totalArea = 0;

        for (Room r : rooms) {
            totalArea += r.getLength() * r.getWidth();
        }

        double utilization = totalArea / project.getPlotArea();

        if (utilization < 0.4) {
            recommendations.add(new DesignSuggestion(
                    "Under Utilized Space",
                    "Large empty plot detected. Add more rooms or expand layout.",
                    "INFO",
                    "SPACE_UTILIZATION"
            ));
        }

        if (utilization > 0.9) {
            recommendations.add(new DesignSuggestion(
                    "Over Utilization",
                    "Design is too dense. Reduce room sizes for better comfort.",
                    "WARNING",
                    "SPACE_UTILIZATION"
            ));
        }
    }
}