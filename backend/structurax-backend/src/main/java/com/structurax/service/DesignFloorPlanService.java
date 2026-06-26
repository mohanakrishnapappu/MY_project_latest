package com.structurax.service;

import com.structurax.dto.design.*;
import com.structurax.entity.*;
import com.structurax.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignFloorPlanService {

    private final DesignProjectRepository designProjectRepository;
    private final RoomRepository roomRepository;
    private final WallRepository wallRepository;
    private final DoorRepository doorRepository;
    private final WindowRepository windowRepository;
    private final FurnitureRepository furnitureRepository;

    public DesignFloorPlanService(
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

    public FloorPlanDetailsResponse getCompleteFloorPlan(Long projectId) {

    DesignProject project = designProjectRepository.findById(projectId)
            .orElseThrow(() ->
                    new RuntimeException("Design Project Not Found"));

    FloorPlanDetailsResponse response = new FloorPlanDetailsResponse();

    // -----------------------------
    // Project Details
    // -----------------------------

    response.setProjectId(project.getId());
    response.setProjectName(project.getProjectName());

    response.setPlotArea(project.getPlotArea());
    response.setFloors(project.getFloors());

    response.setBedrooms(project.getBedrooms());
    response.setBathrooms(project.getBathrooms());
    response.setKitchens(project.getKitchens());
    response.setBalconies(project.getBalconies());
    response.setParkingSpaces(project.getParkingSpaces());

    // -----------------------------
    // Design Objects
    // -----------------------------

    response.setRooms(loadRooms(project));
    response.setWalls(loadWalls(project));
    response.setDoors(loadDoors(project));
    response.setWindows(loadWindows(project));
    response.setFurniture(loadFurniture(project));

    return response;
}

    private List<RoomResponse> loadRooms(DesignProject project){

        return roomRepository.findByDesignProject(project)
                .stream()
                .map(room->{

                    RoomResponse dto = new RoomResponse();

                    dto.setId(room.getId());
                    dto.setRoomType(room.getRoomType());
                    dto.setLength(room.getLength());
                    dto.setWidth(room.getWidth());
                    dto.setPositionX(room.getPositionX());
                    dto.setPositionY(room.getPositionY());

                    return dto;

                }).collect(Collectors.toList());
    }

    private List<WallResponse> loadWalls(DesignProject project){

        return wallRepository.findByDesignProject(project)
                .stream()
                .map(wall->{

                    WallResponse dto = new WallResponse();

                    dto.setId(wall.getId());
                    dto.setStartX(wall.getStartX());
                    dto.setStartY(wall.getStartY());
                    dto.setEndX(wall.getEndX());
                    dto.setEndY(wall.getEndY());

                    return dto;

                }).collect(Collectors.toList());
    }

    private List<DoorResponse> loadDoors(DesignProject project){

        return doorRepository.findByDesignProject(project)
                .stream()
                .map(door->{

                    DoorResponse dto=new DoorResponse();

                    dto.setId(door.getId());
                    dto.setDoorType(door.getDoorType());
                    dto.setPositionX(door.getPositionX());
                    dto.setPositionY(door.getPositionY());
                    dto.setWidth(door.getWidth());

                    return dto;

                }).collect(Collectors.toList());
    }

    private List<WindowResponse> loadWindows(DesignProject project){

        return windowRepository.findByDesignProject(project)
                .stream()
                .map(window->{

                    WindowResponse dto=new WindowResponse();

                    dto.setId(window.getId());
                    dto.setWindowType(window.getWindowType());
                    dto.setPositionX(window.getPositionX());
                    dto.setPositionY(window.getPositionY());
                    dto.setWidth(window.getWidth());

                    return dto;

                }).collect(Collectors.toList());
    }

    private List<FurnitureResponse> loadFurniture(DesignProject project){

        return furnitureRepository.findByDesignProject(project)
                .stream()
                .map(furniture->{

                    FurnitureResponse dto=new FurnitureResponse();

                    dto.setId(furniture.getId());
                    dto.setFurnitureType(furniture.getFurnitureType());
                    dto.setPositionX(furniture.getPositionX());
                    dto.setPositionY(furniture.getPositionY());
                    dto.setWidth(furniture.getWidth());
                    dto.setLength(furniture.getLength());

                    return dto;

                }).collect(Collectors.toList());
    }

}