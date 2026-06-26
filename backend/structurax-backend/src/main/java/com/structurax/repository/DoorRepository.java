package com.structurax.repository;

import com.structurax.entity.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import com.structurax.entity.DesignProject;
import java.util.List;

public interface DoorRepository
        extends JpaRepository<Door, Long> {

    List<Door> findByDesignProjectId(Long designProjectId);
    List<Door> findByDesignProject(DesignProject designProject);
}