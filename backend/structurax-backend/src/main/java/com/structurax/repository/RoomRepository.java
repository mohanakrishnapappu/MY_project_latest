package com.structurax.repository;

import com.structurax.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import com.structurax.entity.DesignProject;
import java.util.List;

public interface RoomRepository
        extends JpaRepository<Room, Long> {

    List<Room> findByDesignProjectId(Long designProjectId);

    List<Room> findByDesignProject(DesignProject designProject);
}