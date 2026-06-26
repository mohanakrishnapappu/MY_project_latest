package com.structurax.controller;

import com.structurax.dto.MaterialRequest;
import com.structurax.entity.Material;
import com.structurax.service.MaterialService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@CrossOrigin(origins = "*")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(
            MaterialService materialService) {

        this.materialService = materialService;
    }

    @PostMapping
    public Material createMaterial(
            @Valid @RequestBody
            MaterialRequest request) {

        return materialService
                .createMaterial(request);
    }

    @GetMapping
    public List<Material> getAllMaterials() {

        return materialService
                .getAllMaterials();
    }

    @GetMapping("/{id}")
    public Material getMaterialById(
            @PathVariable Long id) {

        return materialService
                .getMaterialById(id);
    }

    @PutMapping("/{id}")
    public Material updateMaterial(
            @PathVariable Long id,
            @Valid @RequestBody
            MaterialRequest request) {

        return materialService
                .updateMaterial(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteMaterial(
            @PathVariable Long id) {

        materialService.deleteMaterial(id);

        return "Material Deleted Successfully";
    }
}