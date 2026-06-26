package com.structurax.repository;

import com.structurax.entity.DesignProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignProjectRepository
        extends JpaRepository<DesignProject, Long> {
}