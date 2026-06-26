package com.structurax.dto;

public class ProjectBudgetResponse {

    private Long projectId;

    private String projectName;

    private Double totalBudget;

    private Double materialCost;

    private Double labourCost;

    private Double equipmentCost;

    private Double otherCost;

    public ProjectBudgetResponse() {
    }

    public ProjectBudgetResponse(
            Long projectId,
            String projectName,
            Double totalBudget,
            Double materialCost,
            Double labourCost,
            Double equipmentCost,
            Double otherCost) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.totalBudget = totalBudget;
        this.materialCost = materialCost;
        this.labourCost = labourCost;
        this.equipmentCost = equipmentCost;
        this.otherCost = otherCost;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(Double materialCost) {
        this.materialCost = materialCost;
    }

    public Double getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(Double labourCost) {
        this.labourCost = labourCost;
    }

    public Double getEquipmentCost() {
        return equipmentCost;
    }

    public void setEquipmentCost(Double equipmentCost) {
        this.equipmentCost = equipmentCost;
    }

    public Double getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(Double otherCost) {
        this.otherCost = otherCost;
    }
}