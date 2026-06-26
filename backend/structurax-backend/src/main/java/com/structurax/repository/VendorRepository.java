package com.structurax.repository;

import com.structurax.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository
        extends JpaRepository<Vendor, Long> {
}