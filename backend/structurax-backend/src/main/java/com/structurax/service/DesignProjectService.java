package com.structurax.service;

import com.structurax.dto.DesignProjectRequest;
import com.structurax.entity.DesignProject;
import com.structurax.repository.DesignProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignProjectService {

    private final DesignProjectRepository repository;

    public DesignProjectService(
            DesignProjectRepository repository) {

        this.repository = repository;
    }

    public DesignProject createDesignProject(
            DesignProjectRequest request) {

        DesignProject designProject =
                new DesignProject();

        designProject.setProjectName(
                request.getProjectName());

        designProject.setPlotArea(
                request.getPlotArea());

        designProject.setFloors(
                request.getFloors());

        designProject.setBedrooms(
                request.getBedrooms());

        designProject.setBathrooms(
                request.getBathrooms());

        designProject.setKitchens(
                request.getKitchens());

        designProject.setBalconies(
                request.getBalconies());

        designProject.setParkingSpaces(
                request.getParkingSpaces());

        return repository.save(
                designProject);
    }

    public DesignProject getDesignProject(
            Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Design Project Not Found"));
    }

    public List<DesignProject> getAllDesignProjects() {

        return repository.findAll();
    }

    public void deleteDesignProject(
            Long id) {

        repository.deleteById(id);
    }
}