package com.structurax.dto;

public class CostAnalysisResponse {

    private Long projectId;
    private String projectName;
    private Double estimatedBudget;
    private Double actualExpenses;
    private Double variance;

    public CostAnalysisResponse() {
    }

    public CostAnalysisResponse(
            Long projectId,
            String projectName,
            Double estimatedBudget,
            Double actualExpenses,
            Double variance) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.estimatedBudget = estimatedBudget;
        this.actualExpenses = actualExpenses;
        this.variance = variance;
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

    public Double getEstimatedBudget() {
        return estimatedBudget;
    }

    public void setEstimatedBudget(Double estimatedBudget) {
        this.estimatedBudget = estimatedBudget;
    }

    public Double getActualExpenses() {
        return actualExpenses;
    }

    public void setActualExpenses(Double actualExpenses) {
        this.actualExpenses = actualExpenses;
    }

    public Double getVariance() {
        return variance;
    }

    public void setVariance(Double variance) {
        this.variance = variance;
    }
}