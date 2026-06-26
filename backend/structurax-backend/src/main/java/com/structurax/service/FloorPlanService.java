package com.structurax.service;

import com.structurax.dto.FloorPlanResponse;
import com.structurax.entity.DesignProject;
import com.structurax.repository.*;

import org.springframework.stereotype.Service;

@Service
public class FloorPlanService {

    private final DesignProjectRepository designProjectRepository;
    private final RoomRepository roomRepository;
    private final WallRepository wallRepository;
    private final DoorRepository doorRepository;
    private final WindowRepository windowRepository;
    private final FurnitureRepository furnitureRepository;

    public FloorPlanService(
            DesignProjectRepository designProjectRepository,
            RoomRepository roomRepository,
            WallRepository wallRepository,
            DoorRepository doorRepository,
            WindowRepository windowRepository,
            FurnitureRepository furnitureRepository) {

        this.designProjectRepository = designProjectRepository;
        this.roomRepository = roomRepository;
        this.wallRepository = wallRepository;
        this.doorRepository = doorRepository;
        this.windowRepository = windowRepository;
        this.furnitureRepository = furnitureRepository;
    }

    public FloorPlanResponse generateFloorPlan(Long projectId) {

        DesignProject project =
                designProjectRepository.findById(projectId)
                        .orElseThrow(() ->
                                new RuntimeException("Project Not Found"));

        FloorPlanResponse response =
                new FloorPlanResponse();

        response.setDesignProject(project);

        response.setRooms(
                roomRepository.findByDesignProjectId(projectId));

        response.setWalls(
                wallRepository.findByDesignProjectId(projectId));

        response.setDoors(
                doorRepository.findByDesignProjectId(projectId));

        response.setWindows(
                windowRepository.findByDesignProjectId(projectId));

        response.setFurniture(
                furnitureRepository.findByDesignProjectId(projectId));

        return response;
    }
}