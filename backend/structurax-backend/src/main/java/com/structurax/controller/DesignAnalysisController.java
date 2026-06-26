package com.structurax.controller;

import com.structurax.design.model.ValidationResult;
import com.structurax.design.service.DesignValidationEngine;
import com.structurax.entity.*;

import com.structurax.repository.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/design-analysis")
@CrossOrigin(origins = "*")
public class DesignAnalysisController {

    private final DesignProjectRepository projectRepository;
    private final RoomRepository roomRepository;
    private final WallRepository wallRepository;
    private final DoorRepository doorRepository;
    private final WindowRepository windowRepository;
    private final FurnitureRepository furnitureRepository;

    private final DesignValidationEngine validationEngine;

    public DesignAnalysisController(
            DesignProjectRepository projectRepository,
            RoomRepository roomRepository,
            WallRepository wallRepository,
            DoorRepository doorRepository,
            WindowRepository windowRepository,
            FurnitureRepository furnitureRepository,
            DesignValidationEngine validationEngine) {

        this.projectRepository = projectRepository;
        this.roomRepository = roomRepository;
        this.wallRepository = wallRepository;
        this.doorRepository = doorRepository;
        this.windowRepository = windowRepository;
        this.furnitureRepository = furnitureRepository;
        this.validationEngine = validationEngine;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> analyze(@PathVariable Long projectId) {

        DesignProject project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new RuntimeException("Design Project Not Found"));

        List<Room> rooms =
                roomRepository.findByDesignProjectId(projectId);

        List<Wall> walls =
                wallRepository.findByDesignProjectId(projectId);

        List<Door> doors =
                doorRepository.findByDesignProjectId(projectId);

        List<Window> windows =
                windowRepository.findByDesignProjectId(projectId);

        List<Furniture> furniture =
                furnitureRepository.findByDesignProjectId(projectId);

        ValidationResult result =
                validationEngine.validate(
                        project,
                        rooms,
                        walls,
                        doors,
                        windows,
                        furniture
                );

        return ResponseEntity.ok(result);
    }

}