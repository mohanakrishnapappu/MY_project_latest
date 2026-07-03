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
     * Engines
     * ------------------------------------------------
     */

    private final RoomGenerationEngine roomGenerationEngine;
    private final SpacePlanningEngine spacePlanningEngine;
    private final WallGenerationEngine wallGenerationEngine;

    private final DoorGenerationEngine doorGenerationEngine; // legacy
    private final DoorPlacementEngine doorPlacementEngine;   // AI (NEW)

    private final WindowGenerationEngine windowGenerationEngine;

    private final LayoutConstraintValidator validator;
    private final LayoutScoringEngine scoringEngine;
    private final CandidateLayoutGenerator candidateGenerator;
    private final LayoutEvolutionEngine evolutionEngine;

    /*
     * ------------------------------------------------
     * AI Graph & Circulation
     * ------------------------------------------------
     */

    private final RoomRelationshipGraph relationshipGraph;
    private final CirculationPathEngine circulationPathEngine;
    private final CorridorGenerationEngine corridorGenerationEngine;

    /*
     * ------------------------------------------------
     * Constructor
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
         * STEP 1 - ROOMS
         */
        layout.setRooms(
                roomGenerationEngine.generateRooms(request));

        /*
         * STEP 2 - SPACE PLANNING
         */
        spacePlanningEngine.arrangeRooms(
                request,
                layout.getRooms());

        /*
         * STEP 3 - ROOM RELATIONSHIP GRAPH
         */
        Map<Room, List<Room>> graph =
                relationshipGraph.buildGraph(layout.getRooms());

        /*
         * STEP 4 - CIRCULATION PATHS
         */
        List<CirculationPath> paths =
                circulationPathEngine.generatePaths(graph);

        /*
         * STEP 5 - CORRIDORS
         */
        layout.setCorridors(
                corridorGenerationEngine.generateCorridors(paths));

        /*
         * STEP 6 - WALLS
         */
        layout.setWalls(
                wallGenerationEngine.generateWalls(layout.getRooms()));

        /*
         * STEP 7 - DOORS (UPDATED AI VERSION)
         */
        layout.setDoors(
                doorPlacementEngine.placeDoors(paths));

        /*
         * STEP 8 - WINDOWS
         */
        layout.setWindows(
                windowGenerationEngine.generateWindows(layout.getRooms()));

        /*
         * STEP 9 - VALIDATION
         */
        if (!validator.validate(layout)) {
            throw new IllegalStateException(
                    "Generated layout failed AI validation.");
        }

        /*
         * STEP 10 - SCORING
         */
        layout.setLayoutScore(
                scoringEngine.evaluate(layout));

        /*
         * STEP 11 - CANDIDATES
         */
        List<LayoutCandidate> candidates =
                candidateGenerator.generateCandidates(layout, 20);

        /*
         * STEP 12 - EVOLUTION
         */
        LayoutCandidate bestCandidate =
                evolutionEngine.evolve(candidates);

        /*
         * STEP 13 - FINAL OUTPUT
         */
        return convertToGeneratedLayout(bestCandidate);
    }

    /*
     * ------------------------------------------------
     * CONVERSION
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