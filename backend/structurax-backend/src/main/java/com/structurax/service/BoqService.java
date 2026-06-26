package com.structurax.service;

import com.structurax.dto.BoqItemRequest;
import com.structurax.dto.BoqSummaryResponse;
import com.structurax.dto.ProjectBudgetResponse;
import com.structurax.entity.BoqItem;
import com.structurax.entity.Project;
import com.structurax.repository.BoqItemRepository;
import com.structurax.repository.ProjectRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoqService {

    private final BoqItemRepository boqItemRepository;
    private final ProjectRepository projectRepository;

    public BoqService(
            BoqItemRepository boqItemRepository,
            ProjectRepository projectRepository) {

        this.boqItemRepository = boqItemRepository;
        this.projectRepository = projectRepository;
    }

    public BoqItem createBoqItem(
            BoqItemRequest request) {

        Project project =
                projectRepository
                        .findById(request.getProjectId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project Not Found"));

        Double totalCost =
                request.getQuantity()
                        * request.getUnitRate();

        BoqItem boqItem =
                new BoqItem();

        boqItem.setItemName(
                request.getItemName());

        boqItem.setCategory(
                request.getCategory());

        boqItem.setQuantity(
                request.getQuantity());

        boqItem.setUnit(
                request.getUnit());

        boqItem.setUnitRate(
                request.getUnitRate());

        boqItem.setTotalCost(
                totalCost);

        boqItem.setProject(
                project);

        return boqItemRepository.save(
                boqItem);
    }

    public BoqItem getBoqItem(
            Long id) {

        return boqItemRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "BOQ Item Not Found"));
    }

    public List<BoqItem> getProjectBoq(
            Long projectId) {

        return boqItemRepository
                .findByProjectId(
                        projectId);
    }

    public void deleteBoqItem(
            Long id) {

        BoqItem boqItem =
                boqItemRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "BOQ Item Not Found"));

        boqItemRepository.delete(
                boqItem);
    }

    public ProjectBudgetResponse getProjectBudget(
            Long projectId) {

        Project project =
                projectRepository
                        .findById(projectId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project Not Found"));

        List<BoqItem> items =
                boqItemRepository
                        .findByProjectId(projectId);

        double materialCost = 0;
        double labourCost = 0;
        double equipmentCost = 0;
        double otherCost = 0;

        for (BoqItem item : items) {

            String category =
                    item.getCategory();

            if (category == null)
                continue;

            switch (category.toUpperCase()) {

                case "MATERIAL":
                    materialCost +=
                            item.getTotalCost();
                    break;

                case "LABOUR":
                    labourCost +=
                            item.getTotalCost();
                    break;

                case "EQUIPMENT":
                    equipmentCost +=
                            item.getTotalCost();
                    break;

                default:
                    otherCost +=
                            item.getTotalCost();
            }
        }

        double totalBudget =
                materialCost
                        + labourCost
                        + equipmentCost
                        + otherCost;

        return new ProjectBudgetResponse(
                project.getId(),
                project.getProjectName(),
                totalBudget,
                materialCost,
                labourCost,
                equipmentCost,
                otherCost
        );
    }

    public BoqSummaryResponse getProjectSummary(
            Long projectId) {

        Project project =
                projectRepository
                        .findById(projectId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project Not Found"));

        List<BoqItem> items =
                boqItemRepository
                        .findByProjectId(projectId);

        double estimatedBudget = 0.0;

        for (BoqItem item : items) {

            estimatedBudget +=
                    item.getTotalCost();
        }

        BoqSummaryResponse response =
                new BoqSummaryResponse();

        response.setProjectId(
                project.getId());

        response.setProjectName(
                project.getProjectName());

        response.setTotalItems(
                items.size());

        response.setEstimatedBudget(
                estimatedBudget);

        return response;
    }
}