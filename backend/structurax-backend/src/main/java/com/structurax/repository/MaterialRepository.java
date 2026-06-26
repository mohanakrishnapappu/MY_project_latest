package com.structurax.repository;

import com.structurax.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository
        extends JpaRepository<Material, Long> {
}