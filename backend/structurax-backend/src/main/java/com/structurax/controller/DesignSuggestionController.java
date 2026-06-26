package com.structurax.controller;

import com.structurax.design.ai.RecommendationEngineService;
import com.structurax.design.ai.DesignSuggestion;
import com.structurax.entity.*;
import com.structurax.repository.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/design-analysis")
public class DesignSuggestionController {

    private final DesignProjectRepository projectRepository;
    private final RoomRepository roomRepository;
    private final WallRepository wallRepository;
    private final DoorRepository doorRepository;
    private final WindowRepository windowRepository;
    private final FurnitureRepository furnitureRepository;

    private final RecommendationEngineService engineService;

    public DesignSuggestionController(
            DesignProjectRepository projectRepository,
            RoomRepository roomRepository,
            WallRepository wallRepository,
            DoorRepository doorRepository,
            WindowRepository windowRepository,
            FurnitureRepository furnitureRepository,
            RecommendationEngineService engineService) {

        this.projectRepository = projectRepository;
        this.roomRepository = roomRepository;
        this.wallRepository = wallRepository;
        this.doorRepository = doorRepository;
        this.windowRepository = windowRepository;
        this.furnitureRepository = furnitureRepository;
        this.engineService = engineService;
    }

    @GetMapping("/{projectId}/recommendations")
    public List<DesignSuggestion> getRecommendations(
            @PathVariable Long projectId) {

        DesignProject project = projectRepository.findById(projectId)
                .orElseThrow();

        List<Room> rooms = roomRepository.findByDesignProjectId(projectId);
        List<Wall> walls = wallRepository.findByDesignProjectId(projectId);
        List<Door> doors = doorRepository.findByDesignProjectId(projectId);
        List<Window> windows = windowRepository.findByDesignProjectId(projectId);
        List<Furniture> furniture = furnitureRepository.findByDesignProjectId(projectId);

        return engineService.generate(
                project,
                rooms,
                walls,
                doors,
                windows,
                furniture
        );
    }
}