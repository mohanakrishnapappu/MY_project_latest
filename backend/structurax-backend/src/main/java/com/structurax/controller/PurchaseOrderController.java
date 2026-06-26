package com.structurax.controller;

import com.structurax.dto.PurchaseOrderRequest;
import com.structurax.entity.PurchaseOrder;
import com.structurax.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
@CrossOrigin(origins = "*")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(
            PurchaseOrderService purchaseOrderService) {

        this.purchaseOrderService =
                purchaseOrderService;
    }

    @PostMapping
    public PurchaseOrder createOrder(
            @RequestBody
            PurchaseOrderRequest request) {

        return purchaseOrderService
                .createPurchaseOrder(
                        request);
    }

    @GetMapping("/{id}")
    public PurchaseOrder getOrder(
            @PathVariable Long id) {

        return purchaseOrderService
                .getPurchaseOrder(id);
    }

    @GetMapping("/project/{projectId}")
    public List<PurchaseOrder> getProjectOrders(
            @PathVariable Long projectId) {

        return purchaseOrderService
                .getProjectOrders(
                        projectId);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<PurchaseOrder> getVendorOrders(
            @PathVariable Long vendorId) {

        return purchaseOrderService
                .getVendorOrders(
                        vendorId);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(
            @PathVariable Long id) {

        purchaseOrderService
                .deletePurchaseOrder(id);

        return "Purchase Order Deleted Successfully";
    }
}