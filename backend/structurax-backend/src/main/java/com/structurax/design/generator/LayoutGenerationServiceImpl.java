package com.structurax.design.generator;

import com.structurax.design.ai.evolution.LayoutEvolutionEngine;
import com.structurax.design.ai.planner.SpacePlanningEngine;
import com.structurax.design.ai.scoring.LayoutScoringEngine;
import com.structurax.design.ai.validator.LayoutConstraintValidator;
import com.structurax.design.candidate.CandidateLayoutGenerator;
import com.structurax.design.dto.ProjectRequestDTO;
import com.structurax.design.generator.door.DoorGenerationEngine;
import com.structurax.design.generator.room.RoomGenerationEngine;
import com.structurax.design.generator.wall.WallGenerationEngine;
import com.structurax.design.generator.window.WindowGenerationEngine;
import com.structurax.design.model.GeneratedLayout;
import com.structurax.design.model.LayoutCandidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutGenerationServiceImpl implements LayoutGenerationService {

    private final RoomGenerationEngine roomGenerationEngine;
    private final SpacePlanningEngine spacePlanningEngine;
    private final WallGenerationEngine wallGenerationEngine;
    private final DoorGenerationEngine doorGenerationEngine;
    private final WindowGenerationEngine windowGenerationEngine;
    private final LayoutConstraintValidator validator;
    private final LayoutScoringEngine scoringEngine;
    private final CandidateLayoutGenerator candidateGenerator;
    private final LayoutEvolutionEngine evolutionEngine;

    public LayoutGenerationServiceImpl(
            RoomGenerationEngine roomGenerationEngine,
            SpacePlanningEngine spacePlanningEngine,
            WallGenerationEngine wallGenerationEngine,
            DoorGenerationEngine doorGenerationEngine,
            WindowGenerationEngine windowGenerationEngine,
            LayoutConstraintValidator validator,
            LayoutScoringEngine scoringEngine,
            CandidateLayoutGenerator candidateGenerator,
            LayoutEvolutionEngine evolutionEngine) {

        this.roomGenerationEngine = roomGenerationEngine;
        this.spacePlanningEngine = spacePlanningEngine;
        this.wallGenerationEngine = wallGenerationEngine;
        this.doorGenerationEngine = doorGenerationEngine;
        this.windowGenerationEngine = windowGenerationEngine;
        this.validator = validator;
        this.scoringEngine = scoringEngine;
        this.candidateGenerator = candidateGenerator;
        this.evolutionEngine = evolutionEngine;
    }

    @Override
    public GeneratedLayout generate(ProjectRequestDTO request) {

        // Step 1 - Generate the initial layout
        GeneratedLayout layout = new GeneratedLayout();

        // Generate rooms
        layout.setRooms(
                roomGenerationEngine.generateRooms(request));

        // Arrange rooms
        spacePlanningEngine.arrangeRooms(
                request,
                layout.getRooms());

        // Generate walls
        layout.setWalls(
                wallGenerationEngine.generateWalls(layout.getRooms()));

        // Generate doors
        layout.setDoors(
                doorGenerationEngine.generateDoors(layout.getRooms()));

        // Generate windows
        layout.setWindows(
                windowGenerationEngine.generateWindows(layout.getRooms()));

        // Validate generated layout
        if (!validator.validate(layout)) {
            throw new IllegalStateException(
                    "Generated layout failed AI validation.");
        }

        // Score the layout
        layout.setLayoutScore(
                scoringEngine.evaluate(layout));

        // Generate candidate layouts
        List<LayoutCandidate> candidates =
                candidateGenerator.generateCandidates(layout, 20);

        // Evolve and select the best candidate
        LayoutCandidate bestCandidate =
                evolutionEngine.evolve(candidates);

        // Convert the best candidate back to GeneratedLayout
        return convertToGeneratedLayout(bestCandidate);
    }

    private GeneratedLayout convertToGeneratedLayout(LayoutCandidate candidate) {

        GeneratedLayout layout = new GeneratedLayout();

        layout.setRooms(candidate.getRooms());
        layout.setWalls(candidate.getWalls());
        layout.setDoors(candidate.getDoors());
        layout.setWindows(candidate.getWindows());
        layout.setLayoutScore(candidate.getLayoutScore());

        return layout;
    }
}