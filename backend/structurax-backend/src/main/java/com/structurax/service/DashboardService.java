package com.structurax.service;

import com.structurax.dto.ProjectDashboardResponse;
import com.structurax.entity.Project;
import com.structurax.repository.ProjectRepository;
import com.structurax.repository.TaskRepository;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public DashboardService(
            ProjectRepository projectRepository,
            TaskRepository taskRepository) {

        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public ProjectDashboardResponse getDashboard(
            Long projectId) {

        Project project =
                projectRepository.findById(projectId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project Not Found"));

        long totalTasks =
                taskRepository.countByProjectId(
                        projectId);

        long completedTasks =
                taskRepository.countByProjectIdAndStatus(
                        projectId,
                        "COMPLETED");

        long inProgressTasks =
                taskRepository.countByProjectIdAndStatus(
                        projectId,
                        "IN_PROGRESS");

        long pendingTasks =
                taskRepository.countByProjectIdAndStatus(
                        projectId,
                        "OPEN");

        double completionPercentage = 0;

        if (totalTasks > 0) {

            completionPercentage =
                    ((double) completedTasks
                            / totalTasks) * 100;
        }

        return new ProjectDashboardResponse(
                project.getId(),
                project.getProjectName(),
                totalTasks,
                completedTasks,
                inProgressTasks,
                pendingTasks,
                completionPercentage
        );
    }
}