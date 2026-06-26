package com.structurax.controller;

import com.structurax.dto.ProjectRequest;
import com.structurax.entity.Project;
import com.structurax.entity.ProjectMember;
import com.structurax.service.ProjectService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(
            @PathVariable Long id) {

        return projectService.getProjectById(id);
    }

    @PostMapping
    public Project createProject(
            @Valid @RequestBody ProjectRequest request) {

        return projectService.createProject(request);
    }

    @PutMapping("/{id}")
    public Project updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request) {

        return projectService.updateProject(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);

        return "Project Deleted Successfully";
    }

    @GetMapping("/{projectId}/members")
    public List<ProjectMember> getMembersByProject(
            @PathVariable Long projectId) {

        return projectService.getMembersByProject(projectId);
    }
}