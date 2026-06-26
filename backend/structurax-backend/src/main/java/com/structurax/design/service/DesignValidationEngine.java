package com.structurax.design.service;

import com.structurax.design.model.ValidationResult;
import com.structurax.design.validator.DoorPlacementValidator;
import com.structurax.design.validator.PlotBoundaryValidator;
import com.structurax.design.validator.RoomOverlapValidator;
import com.structurax.design.validator.RoomSizeValidator;
import com.structurax.design.validator.WindowPlacementValidator;
import com.structurax.design.validator.FurnitureCollisionValidator;
import com.structurax.entity.Furniture;
import com.structurax.entity.DesignProject;
import com.structurax.entity.Door;
import com.structurax.entity.Room;
import com.structurax.entity.Wall;
import com.structurax.entity.Window;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignValidationEngine {

    private final PlotBoundaryValidator plotValidator;

    private final RoomOverlapValidator overlapValidator;

    private final RoomSizeValidator roomSizeValidator;

    private final DoorPlacementValidator doorValidator;

    private final WindowPlacementValidator windowValidator;

    private final FurnitureCollisionValidator furnitureValidator;

    public DesignValidationEngine(
            PlotBoundaryValidator plotValidator,
            RoomOverlapValidator overlapValidator,
            RoomSizeValidator roomSizeValidator,
            DoorPlacementValidator doorValidator,
            WindowPlacementValidator windowValidator,
            FurnitureCollisionValidator furnitureValidator) {

        this.plotValidator = plotValidator;
        this.overlapValidator = overlapValidator;
        this.roomSizeValidator = roomSizeValidator;
        this.doorValidator = doorValidator;
        this.windowValidator = windowValidator;
        this.furnitureValidator = furnitureValidator;
    }

    public ValidationResult validate(
            DesignProject project,
            List<Room> rooms,
            List<Wall> walls,
            List<Door> doors,
            List<Window> windows,
            List<Furniture> furnitureList) {

        ValidationResult result = new ValidationResult();

        result.merge(plotValidator.validate(project, rooms));

        result.merge(overlapValidator.validate(rooms));

        result.merge(roomSizeValidator.validate(rooms));

        result.merge(doorValidator.validate(doors, walls));

        result.merge(windowValidator.validate(windows, walls));

        result.merge(furnitureValidator.validate(furnitureList, rooms));

        return result;
    }

}