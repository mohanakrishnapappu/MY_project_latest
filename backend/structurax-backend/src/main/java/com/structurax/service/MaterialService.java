package com.structurax.service;

import com.structurax.dto.MaterialRequest;
import com.structurax.entity.Material;
import com.structurax.exception.ResourceNotFoundException;
import com.structurax.repository.MaterialRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository repository;

    public MaterialService(
            MaterialRepository repository) {

        this.repository = repository;
    }

    public Material createMaterial(
            MaterialRequest request) {

        Material material = new Material();

        material.setMaterialName(
                request.getMaterialName());

        material.setCategory(
                request.getCategory());

        material.setQuantity(
                request.getQuantity());

        material.setUnit(
                request.getUnit());

        material.setUnitPrice(
                request.getUnitPrice());

        return repository.save(material);
    }

    public List<Material> getAllMaterials() {

        return repository.findAll();
    }

    public Material getMaterialById(
            Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Material Not Found"));
    }

    public Material updateMaterial(
            Long id,
            MaterialRequest request) {

        Material material =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Material Not Found"));

        material.setMaterialName(
                request.getMaterialName());

        material.setCategory(
                request.getCategory());

        material.setQuantity(
                request.getQuantity());

        material.setUnit(
                request.getUnit());

        material.setUnitPrice(
                request.getUnitPrice());

        return repository.save(material);
    }

    public void deleteMaterial(
            Long id) {

        Material material =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Material Not Found"));

        repository.delete(material);
    }
}