package com.structurax.service;

import com.structurax.dto.FurnitureRequest;
import com.structurax.entity.DesignProject;
import com.structurax.entity.Furniture;
import com.structurax.repository.DesignProjectRepository;
import com.structurax.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;
    private final DesignProjectRepository designProjectRepository;

    public FurnitureService(
            FurnitureRepository furnitureRepository,
            DesignProjectRepository designProjectRepository) {

        this.furnitureRepository = furnitureRepository;
        this.designProjectRepository = designProjectRepository;
    }

    public Furniture addFurniture(FurnitureRequest request) {

        DesignProject project =
                designProjectRepository.findById(request.getDesignProjectId())
                        .orElseThrow(() -> new RuntimeException("Project Not Found"));

        Furniture furniture = new Furniture();

        furniture.setFurnitureType(request.getFurnitureType());
        furniture.setPositionX(request.getPositionX());
        furniture.setPositionY(request.getPositionY());
        furniture.setWidth(request.getWidth());
        furniture.setLength(request.getLength());
        furniture.setDesignProject(project);

        return furnitureRepository.save(furniture);
    }

    public List<Furniture> getByProject(Long projectId) {
        return furnitureRepository.findByDesignProjectId(projectId);
    }

    public void delete(Long id) {
        furnitureRepository.deleteById(id);
    }
}