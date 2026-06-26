package com.structurax.controller;

import com.structurax.dto.VendorRequest;
import com.structurax.entity.Vendor;
import com.structurax.service.VendorService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin(origins = "*")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(
            VendorService vendorService) {

        this.vendorService = vendorService;
    }

    @PostMapping
    public Vendor createVendor(
            @RequestBody
            VendorRequest request) {

        return vendorService.createVendor(
                request);
    }

    @GetMapping("/{id}")
    public Vendor getVendor(
            @PathVariable Long id) {

        return vendorService.getVendor(id);
    }

    @GetMapping
    public List<Vendor> getAllVendors() {

        return vendorService.getAllVendors();
    }

    @DeleteMapping("/{id}")
    public String deleteVendor(
            @PathVariable Long id) {

        vendorService.deleteVendor(id);

        return "Vendor Deleted Successfully";
    }
}