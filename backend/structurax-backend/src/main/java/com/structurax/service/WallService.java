package com.structurax.service;

import com.structurax.dto.WallRequest;
import com.structurax.entity.DesignProject;
import com.structurax.entity.Wall;
import com.structurax.repository.DesignProjectRepository;
import com.structurax.repository.WallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WallService {

    private final WallRepository wallRepository;
    private final DesignProjectRepository designProjectRepository;

    public WallService(
            WallRepository wallRepository,
            DesignProjectRepository designProjectRepository) {

        this.wallRepository = wallRepository;
        this.designProjectRepository = designProjectRepository;
    }

    public Wall createWall(WallRequest request) {

        DesignProject project =
                designProjectRepository.findById(
                        request.getDesignProjectId())
                        .orElseThrow();

        Wall wall = new Wall();

        wall.setStartX(request.getStartX());
        wall.setStartY(request.getStartY());

        wall.setEndX(request.getEndX());
        wall.setEndY(request.getEndY());

        wall.setDesignProject(project);

        return wallRepository.save(wall);
    }

    public List<Wall> getWalls(Long designProjectId) {
        return wallRepository.findByDesignProjectId(designProjectId);
    }

    public void deleteWall(Long id) {
        wallRepository.deleteById(id);
    }
}