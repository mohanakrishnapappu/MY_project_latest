package com.structurax.service;

import com.structurax.dto.RoomRequest;
import com.structurax.entity.DesignProject;
import com.structurax.entity.Room;
import com.structurax.repository.DesignProjectRepository;
import com.structurax.repository.RoomRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final DesignProjectRepository designProjectRepository;

    public RoomService(
            RoomRepository roomRepository,
            DesignProjectRepository designProjectRepository) {

        this.roomRepository = roomRepository;
        this.designProjectRepository = designProjectRepository;
    }

    public Room createRoom(
            RoomRequest request) {

        DesignProject designProject =
                designProjectRepository
                        .findById(
                                request.getDesignProjectId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Design Project Not Found"));

        Room room = new Room();

        room.setRoomType(
                request.getRoomType());

        room.setLength(
                request.getLength());

        room.setWidth(
                request.getWidth());

        room.setPositionX(
                request.getPositionX());

        room.setPositionY(
                request.getPositionY());

        room.setDesignProject(
                designProject);

        return roomRepository.save(
                room);
    }

    public List<Room> getRoomsByDesignProject(
            Long designProjectId) {

        return roomRepository
                .findByDesignProjectId(
                        designProjectId);
    }

    public void deleteRoom(
            Long id) {

        roomRepository.deleteById(id);
    }
}