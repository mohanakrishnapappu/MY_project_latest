package com.structurax.repository;

import com.structurax.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderRepository
        extends JpaRepository<PurchaseOrder, Long> {

    List<PurchaseOrder> findByProjectId(
            Long projectId);

    List<PurchaseOrder> findByVendorId(
            Long vendorId);
}