package com.structurax.repository;

import com.structurax.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository
        extends JpaRepository<Task, Long> {

    List<Task> findByAssignedUserId(Long userId);

    List<Task> findByProjectId(Long projectId);

    long countByStatus(String status);

    long countByProjectId(Long projectId);

    long countByProjectIdAndStatus(
            Long projectId,
            String status);
}