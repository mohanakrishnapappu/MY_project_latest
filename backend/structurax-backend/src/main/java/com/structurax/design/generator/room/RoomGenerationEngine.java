package com.structurax.design.generator.room;

import com.structurax.design.ai.circulation.CirculationPathEngine;
import com.structurax.design.ai.graph.RoomRelationshipGraph;
import com.structurax.design.ai.planner.ArchitecturalZoningEngine;
import com.structurax.design.ai.planner.CollisionDetectionEngine;
import com.structurax.design.ai.planner.RoomAdjacencyEngine;
import com.structurax.design.ai.planner.RoomPlacementEngine;
import com.structurax.design.ai.rules.RoomRule;
import com.structurax.design.ai.rules.RoomRuleEngine;
import com.structurax.design.dto.ProjectRequestDTO;
import com.structurax.design.optimizer.LayoutRepairEngine;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RoomGenerationEngine {

    private final RoomRuleEngine roomRuleEngine;
    private final RoomAdjacencyEngine adjacencyEngine;
    private final ArchitecturalZoningEngine zoningEngine;
    private final RoomPlacementEngine roomPlacementEngine;
    private final CollisionDetectionEngine collisionDetectionEngine;
    private final LayoutRepairEngine repairEngine;
    private final RoomRelationshipGraph relationshipGraph;
    private final CirculationPathEngine circulationPathEngine;

    public RoomGenerationEngine(
            RoomRuleEngine roomRuleEngine,
            RoomAdjacencyEngine adjacencyEngine,
            ArchitecturalZoningEngine zoningEngine,
            RoomPlacementEngine roomPlacementEngine,
            CollisionDetectionEngine collisionDetectionEngine,
            LayoutRepairEngine repairEngine,
            RoomRelationshipGraph relationshipGraph,
            CirculationPathEngine circulationPathEngine) {

        this.roomRuleEngine = roomRuleEngine;
        this.adjacencyEngine = adjacencyEngine;
        this.zoningEngine = zoningEngine;
        this.roomPlacementEngine = roomPlacementEngine;
        this.collisionDetectionEngine = collisionDetectionEngine;
        this.repairEngine = repairEngine;
        this.relationshipGraph = relationshipGraph;
        this.circulationPathEngine = circulationPathEngine;
    }

    public List<Room> generateRooms(ProjectRequestDTO request) {

        List<Room> rooms = new ArrayList<>();

        /*
         * ---------------------------------
         * Generate Bedrooms
         * ---------------------------------
         */
        RoomRule bedroomRule = roomRuleEngine.getRule("BEDROOM");

        for (int i = 1; i <= request.getNumberOfBedrooms(); i++) {

            Room bedroom = new Room();
            bedroom.setRoomType("BEDROOM");
            bedroom.setLength(bedroomRule.getMinimumLength());
            bedroom.setWidth(bedroomRule.getMinimumWidth());

            rooms.add(bedroom);
        }

        /*
         * ---------------------------------
         * Generate Bathrooms
         * ---------------------------------
         */
        RoomRule bathroomRule = roomRuleEngine.getRule("BATHROOM");

        for (int i = 1; i <= request.getNumberOfBathrooms(); i++) {

            Room bathroom = new Room();
            bathroom.setRoomType("BATHROOM");
            bathroom.setLength(bathroomRule.getMinimumLength());
            bathroom.setWidth(bathroomRule.getMinimumWidth());

            rooms.add(bathroom);
        }

        /*
         * ---------------------------------
         * Generate Hall
         * ---------------------------------
         */
        RoomRule hallRule = roomRuleEngine.getRule("HALL");

        Room hall = new Room();
        hall.setRoomType("HALL");
        hall.setLength(hallRule.getMinimumLength());
        hall.setWidth(hallRule.getMinimumWidth());

        rooms.add(hall);

        /*
         * ---------------------------------
         * Generate Kitchen
         * ---------------------------------
         */
        RoomRule kitchenRule = roomRuleEngine.getRule("KITCHEN");

        Room kitchen = new Room();
        kitchen.setRoomType("KITCHEN");
        kitchen.setLength(kitchenRule.getMinimumLength());
        kitchen.setWidth(kitchenRule.getMinimumWidth());

        rooms.add(kitchen);

        /*
         * ---------------------------------
         * Generate Architectural Zones
         * ---------------------------------
         */
        Map<String, String> zones = zoningEngine.generateZones(rooms);

        /*
         * ---------------------------------
         * AI Room Placement
         * ---------------------------------
         */
        roomPlacementEngine.placeRooms(
                request,
                rooms,
                zones
        );

        /*
         * ---------------------------------
         * Validate Room Adjacency
         * ---------------------------------
         */
        adjacencyEngine.validateAdjacency(rooms);

        /*
         * ---------------------------------
         * Repair Invalid Layout
         * ---------------------------------
         */
        repairEngine.repair(request, rooms);

        /*
         * ---------------------------------
         * Build Relationship Graph
         * ---------------------------------
         */
        Map<Room, List<Room>> graph =
                relationshipGraph.buildGraph(rooms);

        /*
         * ---------------------------------
         * Generate Circulation Paths
         * ---------------------------------
         */
        circulationPathEngine.generatePaths(graph);

        /*
         * ---------------------------------
         * Final Validation
         * ---------------------------------
         */
        if (!collisionDetectionEngine.validate(request, rooms)) {

            throw new IllegalStateException(
                    "Unable to repair generated layout."
            );
        }

        return rooms;
    }
}