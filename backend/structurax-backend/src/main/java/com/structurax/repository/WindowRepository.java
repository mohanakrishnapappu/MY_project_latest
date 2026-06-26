package com.structurax.repository;

import com.structurax.entity.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import com.structurax.entity.DesignProject;
import java.util.List;

public interface WindowRepository
        extends JpaRepository<Window, Long> {

    List<Window> findByDesignProjectId(Long designProjectId);
    List<Window> findByDesignProject(DesignProject designProject);

}   