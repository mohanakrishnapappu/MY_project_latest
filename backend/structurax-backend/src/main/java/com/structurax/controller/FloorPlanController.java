package com.structurax.controller;

import com.structurax.dto.FloorPlanResponse;
import com.structurax.service.FloorPlanService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/floor-plans")
public class FloorPlanController {

    private final FloorPlanService floorPlanService;

    public FloorPlanController(
            FloorPlanService floorPlanService) {

        this.floorPlanService = floorPlanService;
    }

    @GetMapping("/{projectId}")
    public FloorPlanResponse getFloorPlan(
            @PathVariable Long projectId) {

        return floorPlanService.generateFloorPlan(projectId);
    }
}