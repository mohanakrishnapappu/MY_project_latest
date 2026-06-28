// package com.structurax.design.ai;

// import com.structurax.entity.Door;
// import com.structurax.entity.Room;
// import com.structurax.entity.Wall;
// import org.springframework.stereotype.Component;

// import java.util.ArrayList;
// import java.util.List;

// @Component
// public class DoorGenerationEngine {

//     /*
//         AI Rules

//         1. One Main Door
//         2. One Bedroom Door
//         3. One Kitchen Door
//         4. One Bathroom Door
//         5. Hall has Main Door only
//      */

//     public List<Door> generateDoors(
//             List<Room> rooms,
//             List<Wall> walls) {

//         List<Door> doors = new ArrayList<>();

//         boolean mainDoorCreated = false;

//         for (Room room : rooms) {

//             String type = room.getRoomType().toUpperCase();

//             if ("HALL".equals(type)) {

//                 if (!mainDoorCreated) {

//                     doors.add(createMainDoor(room));

//                     mainDoorCreated = true;
//                 }

//             }

//             else if ("BEDROOM".equals(type)) {

//                 doors.add(createInternalDoor(room));

//             }

//             else if ("KITCHEN".equals(type)) {

//                 doors.add(createInternalDoor(room));

//             }

//             else if ("BATHROOM".equals(type)) {

//                 doors.add(createBathroomDoor(room));

//             }

//         }

//         return doors;

//     }

//     /*
//      -----------------------------------------
//             MAIN ENTRANCE
//      -----------------------------------------
//      */

//     private Door createMainDoor(Room room) {

//         Door door = new Door();

//         door.setDoorType("MAIN_DOOR");

//         /*
//             Bottom Center
//          */

//         int x = room.getPositionX()
//                 + room.getWidth().intValue() / 2;

//         int y = room.getPositionY()
//                 + room.getLength().intValue();

//         door.setPositionX(x);
//         door.setPositionY(y);

//         door.setWidth(4.0);

//         door.setDesignProject(room.getDesignProject());

//         return door;

//     }

//     /*
//      -----------------------------------------
//           Internal Door
//      -----------------------------------------
//      */

//     private Door createInternalDoor(Room room) {

//         Door door = new Door();

//         door.setDoorType("ROOM_DOOR");

//         int x = room.getPositionX();

//         int y = room.getPositionY()
//                 + room.getLength().intValue() / 2;

//         door.setPositionX(x);

//         door.setPositionY(y);

//         door.setWidth(3.0);

//         door.setDesignProject(room.getDesignProject());

//         return door;

//     }

//     /*
//      -----------------------------------------
//         Bathroom Door
//      -----------------------------------------
//      */

//     private Door createBathroomDoor(Room room) {

//         Door door = new Door();

//         door.setDoorType("BATHROOM_DOOR");

//         int x = room.getPositionX();

//         int y = room.getPositionY()
//                 + room.getLength().intValue() / 2;

//         door.setPositionX(x);

//         door.setPositionY(y);

//         door.setWidth(2.5);

//         door.setDesignProject(room.getDesignProject());

//         return door;

//     }

// }

package com.structurax.controller;

import com.structurax.design.ai.LayoutGeneratorService;
import com.structurax.design.model.GeneratedLayout;
import com.structurax.entity.DesignProject;
import com.structurax.repository.DesignProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/design-layout")
@CrossOrigin
public class LayoutGenerationController {

    @Autowired
    private LayoutGeneratorService layoutGeneratorService;

    @Autowired
    private DesignProjectRepository designProjectRepository;

    @PostMapping("/{projectId}/generate")
    public GeneratedLayout generateLayout(
            @PathVariable Long projectId) {

        DesignProject project =
                designProjectRepository.findById(projectId)
                        .orElseThrow(() ->
                                new RuntimeException("Project Not Found"));

        return layoutGeneratorService.generateLayout(project);

    }

}