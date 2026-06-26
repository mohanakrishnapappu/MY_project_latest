package com.structurax.controller;

import com.structurax.dto.DoorRequest;
import com.structurax.entity.Door;
import com.structurax.service.DoorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doors")
public class DoorController {

    private final DoorService doorService;

    public DoorController(DoorService doorService) {
        this.doorService = doorService;
    }

    @PostMapping
    public Door createDoor(
            @RequestBody DoorRequest request) {

        return doorService.createDoor(request);
    }

    @GetMapping("/design/{designProjectId}")
    public List<Door> getDoors(
            @PathVariable Long designProjectId) {

        return doorService.getDoors(designProjectId);
    }

    @DeleteMapping("/{id}")
    public String deleteDoor(
            @PathVariable Long id) {

        doorService.deleteDoor(id);

        return "Door Deleted Successfully";
    }
}