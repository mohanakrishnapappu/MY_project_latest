package com.structurax.controller;

import com.structurax.dto.DesignProjectRequest;
import com.structurax.entity.DesignProject;
import com.structurax.service.DesignProjectService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/design-projects")
@CrossOrigin(origins = "*")
public class DesignProjectController {

    private final DesignProjectService service;

    public DesignProjectController(
            DesignProjectService service) {

        this.service = service;
    }

    @PostMapping
    public DesignProject createDesignProject(
            @RequestBody
            DesignProjectRequest request) {

        return service.createDesignProject(
                request);
    }

    @GetMapping("/{id}")
    public DesignProject getDesignProject(
            @PathVariable Long id) {

        return service.getDesignProject(id);
    }

    @GetMapping
    public List<DesignProject> getAllDesignProjects() {

        return service.getAllDesignProjects();
    }

    @DeleteMapping("/{id}")
    public String deleteDesignProject(
            @PathVariable Long id) {

        service.deleteDesignProject(id);

        return "Design Project Deleted Successfully";
    }
}