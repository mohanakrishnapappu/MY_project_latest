package com.structurax.service;

import com.structurax.dto.ProjectRequest;
import com.structurax.entity.Project;
import com.structurax.entity.ProjectMember;
import com.structurax.exception.ResourceNotFoundException;
import com.structurax.repository.ProjectMemberRepository;
import com.structurax.repository.ProjectRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ProjectMemberRepository memberRepository;

    public ProjectService(
            ProjectRepository repository,
            ProjectMemberRepository memberRepository) {

        this.repository = repository;
        this.memberRepository = memberRepository;
    }

    public List<Project> getAllProjects() {

        return repository.findAll();
    }

    public Project saveProject(Project project) {

        return repository.save(project);
    }

    public Project createProject(ProjectRequest request) {

        Project project = new Project();

        project.setProjectName(
                request.getProjectName());

        project.setLocation(
                request.getLocation());

        project.setPlotArea(
                request.getPlotArea());

        project.setFloors(
                request.getFloors());

        project.setStatus(
                request.getStatus());

        return repository.save(project);
    }

    public Project getProjectById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project Not Found"));
    }

    public Project updateProject(
            Long id,
            ProjectRequest request) {

        Project project =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Project Not Found"));

        project.setProjectName(
                request.getProjectName());

        project.setLocation(
                request.getLocation());

        project.setPlotArea(
                request.getPlotArea());

        project.setFloors(
                request.getFloors());

        project.setStatus(
                request.getStatus());

        return repository.save(project);
    }

    public void deleteProject(Long id) {

        repository.deleteById(id);
    }

    public List<ProjectMember> getMembersByProject(
            Long projectId) {

        return memberRepository
                .findByProjectId(projectId);
    }
}