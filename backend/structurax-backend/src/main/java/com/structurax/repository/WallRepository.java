package com.structurax.repository;

import com.structurax.entity.Wall;
import org.springframework.data.jpa.repository.JpaRepository;
import com.structurax.entity.DesignProject;
import java.util.List;

public interface WallRepository
        extends JpaRepository<Wall, Long> {

    List<Wall> findByDesignProjectId(Long designProjectId);
    List<Wall> findByDesignProject(DesignProject designProject);
}