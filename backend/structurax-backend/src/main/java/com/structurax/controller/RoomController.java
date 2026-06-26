package com.structurax.controller;

import com.structurax.dto.RoomRequest;
import com.structurax.entity.Room;
import com.structurax.service.RoomService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    private final RoomService roomService;

    public RoomController(
            RoomService roomService) {

        this.roomService = roomService;
    }

    @PostMapping
    public Room createRoom(
            @RequestBody RoomRequest request) {

        return roomService.createRoom(
                request);
    }

    @GetMapping("/design/{designProjectId}")
    public List<Room> getRooms(
            @PathVariable Long designProjectId) {

        return roomService
                .getRoomsByDesignProject(
                        designProjectId);
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(
            @PathVariable Long id) {

        roomService.deleteRoom(id);

        return "Room Deleted Successfully";
    }
}