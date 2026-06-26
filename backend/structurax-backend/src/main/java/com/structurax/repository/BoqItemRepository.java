package com.structurax.repository;

import com.structurax.entity.BoqItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoqItemRepository
        extends JpaRepository<BoqItem, Long> {

    List<BoqItem> findByProjectId(Long projectId);
}