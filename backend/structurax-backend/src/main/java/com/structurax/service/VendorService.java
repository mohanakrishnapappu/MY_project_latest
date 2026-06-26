package com.structurax.service;

import com.structurax.dto.VendorRequest;
import com.structurax.entity.Vendor;
import com.structurax.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(
            VendorRepository vendorRepository) {

        this.vendorRepository = vendorRepository;
    }

    public Vendor createVendor(
            VendorRequest request) {

        Vendor vendor = new Vendor();

        vendor.setVendorName(
                request.getVendorName());

        vendor.setCompanyName(
                request.getCompanyName());

        vendor.setPhone(
                request.getPhone());

        vendor.setEmail(
                request.getEmail());

        vendor.setAddress(
                request.getAddress());

        return vendorRepository.save(
                vendor);
    }

    public Vendor getVendor(
            Long id) {

        return vendorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Vendor Not Found"));
    }

    public List<Vendor> getAllVendors() {

        return vendorRepository.findAll();
    }

    public void deleteVendor(
            Long id) {

        Vendor vendor =
                vendorRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Vendor Not Found"));

        vendorRepository.delete(vendor);
    }
}