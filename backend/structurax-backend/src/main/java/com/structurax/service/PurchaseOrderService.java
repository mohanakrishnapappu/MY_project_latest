package com.structurax.service;

import com.structurax.dto.PurchaseOrderRequest;
import com.structurax.entity.Project;
import com.structurax.entity.PurchaseOrder;
import com.structurax.entity.Vendor;
import com.structurax.repository.ProjectRepository;
import com.structurax.repository.PurchaseOrderRepository;
import com.structurax.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProjectRepository projectRepository;
    private final VendorRepository vendorRepository;

    public PurchaseOrderService(
            PurchaseOrderRepository purchaseOrderRepository,
            ProjectRepository projectRepository,
            VendorRepository vendorRepository) {

        this.purchaseOrderRepository = purchaseOrderRepository;
        this.projectRepository = projectRepository;
        this.vendorRepository = vendorRepository;
    }

    public PurchaseOrder createPurchaseOrder(
            PurchaseOrderRequest request) {

        Project project =
                projectRepository.findById(
                        request.getProjectId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project Not Found"));

        Vendor vendor =
                vendorRepository.findById(
                        request.getVendorId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Vendor Not Found"));

        PurchaseOrder purchaseOrder =
                new PurchaseOrder();

        purchaseOrder.setPoNumber(
                request.getPoNumber());

        purchaseOrder.setMaterialName(
                request.getMaterialName());

        purchaseOrder.setQuantity(
                request.getQuantity());

        purchaseOrder.setUnitRate(
                request.getUnitRate());

        purchaseOrder.setStatus(
                request.getStatus());

        purchaseOrder.setTotalAmount(
                request.getQuantity()
                        * request.getUnitRate());

        purchaseOrder.setProject(
                project);

        purchaseOrder.setVendor(
                vendor);

        return purchaseOrderRepository.save(
                purchaseOrder);
    }

    public PurchaseOrder getPurchaseOrder(
            Long id) {

        return purchaseOrderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Purchase Order Not Found"));
    }

    public List<PurchaseOrder> getProjectOrders(
            Long projectId) {

        return purchaseOrderRepository
                .findByProjectId(projectId);
    }

    public List<PurchaseOrder> getVendorOrders(
            Long vendorId) {

        return purchaseOrderRepository
                .findByVendorId(vendorId);
    }

    public void deletePurchaseOrder(
            Long id) {

        PurchaseOrder purchaseOrder =
                purchaseOrderRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Purchase Order Not Found"));

        purchaseOrderRepository.delete(
                purchaseOrder);
    }
}