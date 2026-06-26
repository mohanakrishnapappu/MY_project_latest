package com.structurax.design.optimizer;

import com.structurax.entity.Door;
import com.structurax.entity.Furniture;
import com.structurax.entity.Room;
import com.structurax.entity.Wall;
import com.structurax.entity.Window;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LayoutOptimizer {

public List<LayoutSuggestion> optimize(
        List<Room> rooms,
        List<Wall> walls,
        List<Door> doors,
        List<Window> windows,
        List<Furniture> furnitureList) {

    List<LayoutSuggestion> suggestions = new ArrayList<>();

    checkRoomSizes(rooms, suggestions);

    checkNaturalLighting(rooms, windows, suggestions);

    checkDoorAccessibility(doors, suggestions);

    checkFurnitureDensity(furnitureList, suggestions);

    checkBedroomCount(rooms, suggestions);

    checkKitchenExists(rooms, suggestions);

    checkBathroomExists(rooms, suggestions);

    checkHallExists(rooms, suggestions);

    checkCrossVentilation(windows, suggestions);

    return suggestions;

}

    private void checkRoomSizes(
            List<Room> rooms,
            List<LayoutSuggestion> suggestions) {

        for (Room room : rooms) {

            double area = room.getLength() * room.getWidth();

            if (area < 60) {

                suggestions.add(new LayoutSuggestion(
                        "Small Room",
                        room.getRoomType()
                                + " is smaller than the recommended size.",
                        "WARNING"
                ));
            }
        }
    }

    private void checkNaturalLighting(
            List<Room> rooms,
            List<Window> windows,
            List<LayoutSuggestion> suggestions) {

        if (windows.size() < rooms.size()) {

            suggestions.add(new LayoutSuggestion(
                    "Lighting",
                    "Some rooms may not receive sufficient natural light.",
                    "INFO"
            ));
        }
    }

    private void checkDoorAccessibility(
            List<Door> doors,
            List<LayoutSuggestion> suggestions) {

        for (Door door : doors) {

            if (door.getWidth() < 3.0) {

                suggestions.add(new LayoutSuggestion(
                        "Door Width",
                        "Door width should be at least 3 feet.",
                        "WARNING"
                ));
            }
        }
    }

    private void checkBedroomCount(
        List<Room> rooms,
        List<LayoutSuggestion> suggestions) {

    long bedrooms = rooms.stream()
            .filter(r -> r.getRoomType().equalsIgnoreCase("BEDROOM"))
            .count();

    if (bedrooms == 0) {

        suggestions.add(new LayoutSuggestion(
                "Bedroom",
                "House does not contain any bedroom.",
                "ERROR"
        ));
    }

}

private void checkKitchenExists(
        List<Room> rooms,
        List<LayoutSuggestion> suggestions) {

    boolean found = rooms.stream()
            .anyMatch(r ->
                    r.getRoomType().equalsIgnoreCase("KITCHEN"));

    if (!found) {

        suggestions.add(new LayoutSuggestion(
                "Kitchen",
                "Kitchen is missing.",
                "ERROR"
        ));

    }

}

private void checkBathroomExists(
        List<Room> rooms,
        List<LayoutSuggestion> suggestions) {

    boolean found = rooms.stream()
            .anyMatch(r ->
                    r.getRoomType().equalsIgnoreCase("BATHROOM"));

    if (!found) {

        suggestions.add(new LayoutSuggestion(
                "Bathroom",
                "Bathroom is missing.",
                "ERROR"
        ));

    }

}

private void checkHallExists(
        List<Room> rooms,
        List<LayoutSuggestion> suggestions) {

    boolean found = rooms.stream()
            .anyMatch(r ->
                    r.getRoomType().equalsIgnoreCase("HALL"));

    if (!found) {

        suggestions.add(new LayoutSuggestion(
                "Hall",
                "Hall/Living Room is missing.",
                "WARNING"
        ));

    }

}

private void checkCrossVentilation(
        List<Window> windows,
        List<LayoutSuggestion> suggestions) {

    if (windows.size() < 2) {

        suggestions.add(new LayoutSuggestion(
                "Ventilation",
                "Cross ventilation is insufficient.",
                "INFO"
        ));

    }

}

    private void checkFurnitureDensity(
            List<Furniture> furnitureList,
            List<LayoutSuggestion> suggestions) {

        if (furnitureList.size() > 20) {

            suggestions.add(new LayoutSuggestion(
                    "Furniture",
                    "Furniture density is high. Consider increasing free space.",
                    "INFO"
            ));
        }
    }
}