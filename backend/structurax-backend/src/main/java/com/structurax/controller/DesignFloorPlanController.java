package com.structurax.controller;

import com.structurax.dto.design.FloorPlanDetailsResponse;
import com.structurax.service.DesignFloorPlanService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/design-projects")
@CrossOrigin(origins = "*")
public class DesignFloorPlanController {

    private final DesignFloorPlanService designFloorPlanService;

    public DesignFloorPlanController(
            DesignFloorPlanService designFloorPlanService) {

        this.designFloorPlanService = designFloorPlanService;
    }

    @GetMapping("/{projectId}/floorplan")
    public ResponseEntity<FloorPlanDetailsResponse> getFloorPlan(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                designFloorPlanService.getCompleteFloorPlan(projectId)
        );
    }

}