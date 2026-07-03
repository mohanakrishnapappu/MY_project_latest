package com.structurax.design.ai;

import com.structurax.design.ai.planner.CollisionDetectionEngine;
import com.structurax.design.ai.planner.SpaceOptimizationEngine;
import com.structurax.design.ai.scoring.LayoutScore;
import com.structurax.design.ai.scoring.LayoutScoreEngine;
import com.structurax.design.candidate.CandidateLayoutGenerator;
import com.structurax.design.candidate.LayoutMutationEngine;
import com.structurax.design.dto.ProjectRequestDTO;
import com.structurax.design.generator.room.RoomGenerationEngine;
import com.structurax.design.model.GeneratedLayout;
import com.structurax.design.model.LayoutCandidate;
import com.structurax.design.optimizer.BestLayoutSelector;
import com.structurax.entity.DesignProject;
import com.structurax.entity.Door;
import com.structurax.entity.Room;
import com.structurax.entity.Wall;
import com.structurax.entity.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutGeneratorService {

    private static final Logger logger =
            LoggerFactory.getLogger(LayoutGeneratorService.class);

    private final RoomGenerationEngine roomGenerationEngine;
    private final WallGenerationEngine wallGenerationEngine;
    private final DoorGenerationEngine doorGenerationEngine;
    private final WindowGenerationEngine windowGenerationEngine;

    private final CollisionDetectionEngine collisionDetectionEngine;
    private final SpaceOptimizationEngine spaceOptimizationEngine;
    private final LayoutScoreEngine layoutScoreEngine;

    private final CandidateLayoutGenerator candidateLayoutGenerator;
    private final LayoutMutationEngine layoutMutationEngine;
    private final BestLayoutSelector bestLayoutSelector;

    public LayoutGeneratorService(
            RoomGenerationEngine roomGenerationEngine,
            WallGenerationEngine wallGenerationEngine,
            DoorGenerationEngine doorGenerationEngine,
            WindowGenerationEngine windowGenerationEngine,
            CollisionDetectionEngine collisionDetectionEngine,
            SpaceOptimizationEngine spaceOptimizationEngine,
            LayoutScoreEngine layoutScoreEngine,
            CandidateLayoutGenerator candidateLayoutGenerator,
            LayoutMutationEngine layoutMutationEngine,
            BestLayoutSelector bestLayoutSelector) {

        this.roomGenerationEngine = roomGenerationEngine;
        this.wallGenerationEngine = wallGenerationEngine;
        this.doorGenerationEngine = doorGenerationEngine;
        this.windowGenerationEngine = windowGenerationEngine;
        this.collisionDetectionEngine = collisionDetectionEngine;
        this.spaceOptimizationEngine = spaceOptimizationEngine;
        this.layoutScoreEngine = layoutScoreEngine;
        this.candidateLayoutGenerator = candidateLayoutGenerator;
        this.layoutMutationEngine = layoutMutationEngine;
        this.bestLayoutSelector = bestLayoutSelector;
    }

    public GeneratedLayout generateLayout(DesignProject project) {

        logger.info("========== AI Layout Generation Started ==========");

        ProjectRequestDTO request = new ProjectRequestDTO();
        request.setNumberOfBedrooms(project.getBedrooms());
        request.setNumberOfBathrooms(project.getBathrooms());

        List<Room> rooms = roomGenerationEngine.generateRooms(request);

        if (collisionDetectionEngine.hasCollision(rooms)) {
            throw new IllegalStateException("AI detected overlapping rooms.");
        }

        double efficiency = spaceOptimizationEngine.calculateEfficiency(
                project.getPlotArea(),
                rooms
        );

        List<Wall> walls = wallGenerationEngine.generateWalls(rooms);
        List<Door> doors = doorGenerationEngine.generateDoors(rooms, walls);
        List<Window> windows = windowGenerationEngine.generateWindows(rooms);

        LayoutScore score = layoutScoreEngine.evaluate(
                rooms,
                doors,
                windows,
                efficiency
        );

        GeneratedLayout baseLayout = new GeneratedLayout();
        baseLayout.setRooms(rooms);
        baseLayout.setWalls(walls);
        baseLayout.setDoors(doors);
        baseLayout.setWindows(windows);
        baseLayout.setLayoutScore(score);

        logger.info("Base Layout Score : {}", score.getFinalScore());

        List<LayoutCandidate> candidates =
                candidateLayoutGenerator.generateCandidates(baseLayout, 5);

        for (LayoutCandidate candidate : candidates) {

            layoutMutationEngine.mutate(candidate);

            LayoutScore candidateScore = layoutScoreEngine.evaluate(
                    candidate.getRooms(),
                    candidate.getDoors(),
                    candidate.getWindows(),
                    efficiency
            );

            candidate.setLayoutScore(candidateScore);

            logger.info("Candidate Score : {}", candidateScore.getFinalScore());
        }

        LayoutCandidate bestCandidate =
                bestLayoutSelector.selectBest(candidates);

        GeneratedLayout finalLayout = new GeneratedLayout();
        finalLayout.setRooms(bestCandidate.getRooms());
        finalLayout.setWalls(bestCandidate.getWalls());
        finalLayout.setDoors(bestCandidate.getDoors());
        finalLayout.setWindows(bestCandidate.getWindows());
        finalLayout.setLayoutScore(bestCandidate.getLayoutScore());

        logger.info("Best Layout Score : {}",
                bestCandidate.getLayoutScore().getFinalScore());

        logger.info("========== AI Layout Generation Completed ==========");

        return finalLayout;
    }
}