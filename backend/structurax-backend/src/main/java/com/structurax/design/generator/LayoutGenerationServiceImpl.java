package com.structurax.design.generator;

import com.structurax.design.ai.circulation.CirculationPath;
import com.structurax.design.ai.circulation.CirculationPathEngine;
import com.structurax.design.ai.evolution.LayoutEvolutionEngine;
import com.structurax.design.ai.graph.RoomRelationshipGraph;
import com.structurax.design.ai.planner.SpacePlanningEngine;
import com.structurax.design.ai.scoring.LayoutScoringEngine;
import com.structurax.design.ai.validator.LayoutConstraintValidator;
import com.structurax.design.candidate.CandidateLayoutGenerator;
import com.structurax.design.dto.ProjectRequestDTO;
import com.structurax.design.generator.corridor.CorridorGenerationEngine;
import com.structurax.design.generator.door.DoorGenerationEngine;
import com.structurax.design.generator.door.DoorPlacementEngine;
import com.structurax.design.generator.room.RoomGenerationEngine;
import com.structurax.design.generator.wall.WallGenerationEngine;
import com.structurax.design.generator.window.WindowGenerationEngine;
import com.structurax.design.model.GeneratedLayout;
import com.structurax.design.model.LayoutCandidate;
import com.structurax.entity.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LayoutGenerationServiceImpl implements LayoutGenerationService {

    /*
     * ------------------------------------------------
     * CORE ENGINES
     * ------------------------------------------------
     */

    private final RoomGenerationEngine roomGenerationEngine;
    private final SpacePlanningEngine spacePlanningEngine;
    private final WallGenerationEngine wallGenerationEngine;

    /*
     * ------------------------------------------------
     * DOOR ENGINES
     * ------------------------------------------------
     */

    // Legacy (keep for fallback)
    private final DoorGenerationEngine doorGenerationEngine;

    // AI-based (primary)
    private final DoorPlacementEngine doorPlacementEngine;

    /*
     * ------------------------------------------------
     * WINDOW ENGINE
     * ------------------------------------------------
     */

    private final WindowGenerationEngine windowGenerationEngine;

    /*
     * ------------------------------------------------
     * VALIDATION + SCORING
     * ------------------------------------------------
     */

    private final LayoutConstraintValidator validator;
    private final LayoutScoringEngine scoringEngine;

    private final CandidateLayoutGenerator candidateGenerator;
    private final LayoutEvolutionEngine evolutionEngine;

    /*
     * ------------------------------------------------
     * GRAPH + CIRCULATION
     * ------------------------------------------------
     */

    private final RoomRelationshipGraph relationshipGraph;
    private final CirculationPathEngine circulationPathEngine;
    private final CorridorGenerationEngine corridorGenerationEngine;

    /*
     * ------------------------------------------------
     * CONSTRUCTOR
     * ------------------------------------------------
     */

    public LayoutGenerationServiceImpl(

            RoomGenerationEngine roomGenerationEngine,
            SpacePlanningEngine spacePlanningEngine,
            WallGenerationEngine wallGenerationEngine,

            DoorGenerationEngine doorGenerationEngine,
            DoorPlacementEngine doorPlacementEngine,

            WindowGenerationEngine windowGenerationEngine,

            LayoutConstraintValidator validator,
            LayoutScoringEngine scoringEngine,
            CandidateLayoutGenerator candidateGenerator,
            LayoutEvolutionEngine evolutionEngine,

            RoomRelationshipGraph relationshipGraph,
            CirculationPathEngine circulationPathEngine,
            CorridorGenerationEngine corridorGenerationEngine
    ) {

        this.roomGenerationEngine = roomGenerationEngine;
        this.spacePlanningEngine = spacePlanningEngine;
        this.wallGenerationEngine = wallGenerationEngine;

        this.doorGenerationEngine = doorGenerationEngine;
        this.doorPlacementEngine = doorPlacementEngine;

        this.windowGenerationEngine = windowGenerationEngine;

        this.validator = validator;
        this.scoringEngine = scoringEngine;
        this.candidateGenerator = candidateGenerator;
        this.evolutionEngine = evolutionEngine;

        this.relationshipGraph = relationshipGraph;
        this.circulationPathEngine = circulationPathEngine;
        this.corridorGenerationEngine = corridorGenerationEngine;
    }

    /*
     * ------------------------------------------------
     * MAIN GENERATION PIPELINE
     * ------------------------------------------------
     */

    @Override
    public GeneratedLayout generate(ProjectRequestDTO request) {

        GeneratedLayout layout = new GeneratedLayout();

        /*
         * STEP 1: Generate Rooms
         */
        layout.setRooms(
                roomGenerationEngine.generateRooms(request));

        /*
         * STEP 2: Space Planning
         */
        spacePlanningEngine.arrangeRooms(
                request,
                layout.getRooms());

        /*
         * STEP 3: Relationship Graph
         */
        Map<Room, List<Room>> graph =
                relationshipGraph.buildGraph(layout.getRooms());

        /*
         * STEP 4: Circulation Paths
         */
        List<CirculationPath> paths =
                circulationPathEngine.generatePaths(graph);

        /*
         * STEP 5: Corridors
         */
        layout.setCorridors(
                corridorGenerationEngine.generateCorridors(paths));

        /*
         * STEP 6: Walls
         */
        layout.setWalls(
                wallGenerationEngine.generateWalls(layout.getRooms()));

        /*
         * STEP 7: DOORS (AI ENGINE - ACTIVE)
         */
        layout.setDoors(
                doorPlacementEngine.placeDoors(paths));

        /*
         * STEP 8: WINDOWS (current engine)
         */
        layout.setWindows(
                windowGenerationEngine.generateWindows(layout.getRooms()));

        /*
         * STEP 9: VALIDATION
         */
        if (!validator.validate(layout)) {
            throw new IllegalStateException(
                    "Generated layout failed AI validation.");
        }

        /*
         * STEP 10: SCORING
         */
        layout.setLayoutScore(
                scoringEngine.evaluate(layout));

        /*
         * STEP 11: CANDIDATES
         */
        List<LayoutCandidate> candidates =
                candidateGenerator.generateCandidates(layout, 20);

        /*
         * STEP 12: EVOLUTION
         */
        LayoutCandidate bestCandidate =
                evolutionEngine.evolve(candidates);

        /*
         * STEP 13: FINAL OUTPUT
         */
        return convertToGeneratedLayout(bestCandidate);
    }

    /*
     * ------------------------------------------------
     * CONVERSION METHOD
     * ------------------------------------------------
     */

    private GeneratedLayout convertToGeneratedLayout(LayoutCandidate candidate) {

        GeneratedLayout layout = new GeneratedLayout();

        layout.setRooms(candidate.getRooms());
        layout.setWalls(candidate.getWalls());
        layout.setDoors(candidate.getDoors());
        layout.setWindows(candidate.getWindows());
        layout.setCorridors(candidate.getCorridors());
        layout.setLayoutScore(candidate.getLayoutScore());

        return layout;
    }
}