package com.structurax.service;

import com.structurax.dto.DoorRequest;
import com.structurax.entity.DesignProject;
import com.structurax.entity.Door;
import com.structurax.repository.DesignProjectRepository;
import com.structurax.repository.DoorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoorService {

    private final DoorRepository doorRepository;
    private final DesignProjectRepository designProjectRepository;

    public DoorService(
            DoorRepository doorRepository,
            DesignProjectRepository designProjectRepository) {

        this.doorRepository = doorRepository;
        this.designProjectRepository = designProjectRepository;
    }

    public Door createDoor(DoorRequest request) {

        DesignProject project =
                designProjectRepository.findById(
                        request.getDesignProjectId())
                        .orElseThrow();

        Door door = new Door();

        door.setDoorType(request.getDoorType());
        door.setPositionX(request.getPositionX());
        door.setPositionY(request.getPositionY());
        door.setWidth(request.getWidth());

        door.setDesignProject(project);

        return doorRepository.save(door);
    }

    public List<Door> getDoors(Long designProjectId) {
        return doorRepository.findByDesignProjectId(designProjectId);
    }

    public void deleteDoor(Long id) {
        doorRepository.deleteById(id);
    }
}