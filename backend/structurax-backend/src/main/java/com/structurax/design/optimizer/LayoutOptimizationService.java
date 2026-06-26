package com.structurax.design.optimizer;

import com.structurax.entity.*;
import com.structurax.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutOptimizationService {

    private final RoomRepository roomRepository;
    private final WallRepository wallRepository;
    private final DoorRepository doorRepository;
    private final WindowRepository windowRepository;
    private final FurnitureRepository furnitureRepository;

    private final LayoutOptimizer optimizer;

    public LayoutOptimizationService(
            RoomRepository roomRepository,
            WallRepository wallRepository,
            DoorRepository doorRepository,
            WindowRepository windowRepository,
            FurnitureRepository furnitureRepository,
            LayoutOptimizer optimizer) {

        this.roomRepository = roomRepository;
        this.wallRepository = wallRepository;
        this.doorRepository = doorRepository;
        this.windowRepository = windowRepository;
        this.furnitureRepository = furnitureRepository;
        this.optimizer = optimizer;
    }

    public List<LayoutSuggestion> optimize(Long projectId) {

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

        return optimizer.optimize(
                rooms,
                walls,
                doors,
                windows,
                furniture
        );
    }

}