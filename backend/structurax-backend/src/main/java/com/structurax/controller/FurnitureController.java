package com.structurax.controller;

import com.structurax.dto.FurnitureRequest;
import com.structurax.entity.Furniture;
import com.structurax.service.FurnitureService;
import org.springframework.web.bind.annotation.*;
import com.structurax.design.validator.FurnitureCollisionValidator;
import java.util.List;

@RestController
@RequestMapping("/api/furniture")
public class FurnitureController {

    private final FurnitureService furnitureService;
    private final FurnitureCollisionValidator furnitureValidator;
    public FurnitureController(FurnitureService furnitureService, FurnitureCollisionValidator furnitureValidator) {
        this.furnitureService = furnitureService;
        this.furnitureValidator = furnitureValidator;
    }

    @PostMapping
    public Furniture addFurniture(
            @RequestBody FurnitureRequest request) {

        return furnitureService.addFurniture(request);
    }

    @GetMapping("/design/{projectId}")
    public List<Furniture> getFurniture(
            @PathVariable Long projectId) {

        return furnitureService.getByProject(projectId);
    }

    @DeleteMapping("/{id}")
    public String deleteFurniture(
            @PathVariable Long id) {

        furnitureService.delete(id);

        return "Furniture Deleted Successfully";
    }
}