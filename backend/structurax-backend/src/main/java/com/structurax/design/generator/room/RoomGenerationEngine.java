package com.structurax.design.generator.room;

import com.structurax.design.ai.planner.RoomAdjacencyEngine;
import com.structurax.design.ai.rules.RoomRule;
import com.structurax.design.ai.rules.RoomRuleEngine;
import com.structurax.design.dto.ProjectRequestDTO;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomGenerationEngine {

    private final RoomRuleEngine roomRuleEngine;
    private final RoomAdjacencyEngine adjacencyEngine;

    public RoomGenerationEngine(
            RoomRuleEngine roomRuleEngine,
            RoomAdjacencyEngine adjacencyEngine) {

        this.roomRuleEngine = roomRuleEngine;
        this.adjacencyEngine = adjacencyEngine;
    }

    public List<Room> generateRooms(ProjectRequestDTO request) {

        List<Room> rooms = new ArrayList<>();

        int currentY = 0;

        /*
         * Bedrooms
         */
        RoomRule bedroomRule = roomRuleEngine.getRule("BEDROOM");
        int bedroomLength = (int) bedroomRule.getMinimumLength();

        for (int i = 1; i <= request.getNumberOfBedrooms(); i++) {

            Room bedroom = new Room();

            bedroom.setRoomType("BEDROOM");
            bedroom.setLength(bedroomRule.getMinimumLength());
            bedroom.setWidth(bedroomRule.getMinimumWidth());

            bedroom.setPositionX(0);
            bedroom.setPositionY(currentY);

            currentY += bedroomLength;

            rooms.add(bedroom);
        }

        /*
         * Bathrooms
         */
        RoomRule bathroomRule = roomRuleEngine.getRule("BATHROOM");
        int bathroomLength = (int) bathroomRule.getMinimumLength();

        for (int i = 1; i <= request.getNumberOfBathrooms(); i++) {

            Room bathroom = new Room();

            bathroom.setRoomType("BATHROOM");
            bathroom.setLength(bathroomRule.getMinimumLength());
            bathroom.setWidth(bathroomRule.getMinimumWidth());

            bathroom.setPositionX(15);
            bathroom.setPositionY((i - 1) * bathroomLength);

            rooms.add(bathroom);
        }

        /*
         * Hall
         */
        RoomRule hallRule = roomRuleEngine.getRule("HALL");
        int hallLength = (int) hallRule.getMinimumLength();

        Room hall = new Room();

        hall.setRoomType("HALL");
        hall.setLength(hallRule.getMinimumLength());
        hall.setWidth(hallRule.getMinimumWidth());

        hall.setPositionX(30);
        hall.setPositionY(0);

        rooms.add(hall);

        /*
         * Kitchen
         */
        RoomRule kitchenRule = roomRuleEngine.getRule("KITCHEN");

        Room kitchen = new Room();

        kitchen.setRoomType("KITCHEN");
        kitchen.setLength(kitchenRule.getMinimumLength());
        kitchen.setWidth(kitchenRule.getMinimumWidth());

        kitchen.setPositionX(30);
        kitchen.setPositionY(hallLength);

        rooms.add(kitchen);

        /*
         * Validate architectural adjacency
         */
        adjacencyEngine.validateAdjacency(rooms);

        return rooms;
    }
}