package com.structurax.controller;

import com.structurax.dto.FurnitureRequest;
import com.structurax.entity.Furniture;
import com.structurax.service.FurnitureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/furniture")
public class FurnitureController {

    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
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