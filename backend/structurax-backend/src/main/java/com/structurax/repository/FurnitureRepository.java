package com.structurax.repository;

import com.structurax.entity.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import com.structurax.entity.DesignProject;
import java.util.List;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {

    List<Furniture> findByDesignProjectId(Long designProjectId);
    List<Furniture> findByDesignProject(DesignProject designProject);
}