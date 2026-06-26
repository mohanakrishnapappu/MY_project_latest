package com.structurax.controller;

import com.structurax.dto.BoqItemRequest;
import com.structurax.dto.BoqSummaryResponse;
import com.structurax.dto.ProjectBudgetResponse;
import com.structurax.entity.BoqItem;
import com.structurax.service.BoqService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boq")
@CrossOrigin(origins = "*")
public class BoqController {

    private final BoqService boqService;

    public BoqController(
            BoqService boqService) {

        this.boqService = boqService;
    }

    @PostMapping
    public BoqItem createBoq(
            @RequestBody
            BoqItemRequest request) {

        return boqService
                .createBoqItem(request);
    }

    @GetMapping("/{id}")
    public BoqItem getBoq(
            @PathVariable Long id) {

        return boqService
                .getBoqItem(id);
    }

    @GetMapping("/project/{projectId}")
    public List<BoqItem> getProjectBoq(
            @PathVariable Long projectId) {

        return boqService
                .getProjectBoq(projectId);
    }

    @GetMapping("/project/{projectId}/summary")
    public BoqSummaryResponse getProjectSummary(
            @PathVariable Long projectId) {

        return boqService
                .getProjectSummary(projectId);
    }

    @GetMapping("/budget/{projectId}")
    public ProjectBudgetResponse getBudget(
            @PathVariable Long projectId) {

        return boqService
                .getProjectBudget(projectId);
    }

    @DeleteMapping("/{id}")
    public String deleteBoq(
            @PathVariable Long id) {

        boqService.deleteBoqItem(id);

        return "BOQ Item Deleted Successfully";
    }
}