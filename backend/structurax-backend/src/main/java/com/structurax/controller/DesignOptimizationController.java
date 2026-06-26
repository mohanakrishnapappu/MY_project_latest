package com.structurax.controller;

import com.structurax.design.optimizer.LayoutOptimizationService;
import com.structurax.design.optimizer.LayoutSuggestion;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/design-projects")
public class DesignOptimizationController {

    private final LayoutOptimizationService optimizationService;

    public DesignOptimizationController(
            LayoutOptimizationService optimizationService) {

        this.optimizationService = optimizationService;
    }

    @GetMapping("/{projectId}/optimize")
    public List<LayoutSuggestion> optimize(
            @PathVariable Long projectId) {

        return optimizationService.optimize(projectId);

    }

}