package com.structurax.design.ai;

import com.structurax.design.model.GeneratedLayout;
import com.structurax.entity.DesignProject;
import com.structurax.entity.Door;
import com.structurax.entity.Room;
import com.structurax.entity.Wall;
import com.structurax.entity.Window;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutGeneratorService {

    private final LayoutPlanner layoutPlanner;

    private final RoomPlacementEngine roomPlacementEngine;

    private final WallGenerationEngine wallGenerationEngine;

    private final DoorGenerationEngine doorGenerationEngine;

    private final WindowGenerationEngine windowGenerationEngine;

    public LayoutGeneratorService(
            LayoutPlanner layoutPlanner,
            RoomPlacementEngine roomPlacementEngine,
            WallGenerationEngine wallGenerationEngine,
            DoorGenerationEngine doorGenerationEngine,
            WindowGenerationEngine windowGenerationEngine) {

        this.layoutPlanner = layoutPlanner;
        this.roomPlacementEngine = roomPlacementEngine;
        this.wallGenerationEngine = wallGenerationEngine;
        this.doorGenerationEngine = doorGenerationEngine;
        this.windowGenerationEngine = windowGenerationEngine;
    }

    /**
     * Main AI Pipeline
     */

    public GeneratedLayout generateLayout(DesignProject project) {

        /*
         -----------------------------------------
         STEP 1
         Generate Rooms
         -----------------------------------------
         */

        List<Room> rooms =
                layoutPlanner.createRooms(project);

        /*
         -----------------------------------------
         STEP 2
         Place Rooms
         -----------------------------------------
         */

        roomPlacementEngine.placeRooms(rooms);

        /*
         -----------------------------------------
         STEP 3
         Generate Walls
         -----------------------------------------
         */

        List<Wall> walls =
                wallGenerationEngine.generateWalls(rooms);

        /*
         -----------------------------------------
         STEP 4
         Generate Doors
         -----------------------------------------
         */

        List<Door> doors =
                doorGenerationEngine.generateDoors(
                        rooms,
                        walls
                );

        /*
         -----------------------------------------
         STEP 5
         Generate Windows
         -----------------------------------------
         */

        List<Window> windows =
                windowGenerationEngine.generateWindows(
                        rooms
                );

        /*
         -----------------------------------------
         Build Final Layout
         -----------------------------------------
         */

        GeneratedLayout layout =
                new GeneratedLayout();

        layout.setRooms(rooms);

        layout.setWalls(walls);

        layout.setDoors(doors);

        layout.setWindows(windows);

        return layout;

    }

}