package com.structurax.controller;

import com.structurax.dto.ProjectDashboardResponse;
import com.structurax.service.DashboardService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    @GetMapping("/{projectId}")
    public ProjectDashboardResponse getDashboard(
            @PathVariable Long projectId) {

        return dashboardService
                .getDashboard(projectId);
    }
}