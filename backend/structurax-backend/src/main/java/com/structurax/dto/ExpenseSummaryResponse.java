package com.structurax.dto;

public class ExpenseSummaryResponse {

    private Long projectId;
    private String projectName;
    private Integer expenseCount;
    private Double totalExpenses;

    public ExpenseSummaryResponse() {
    }

    public ExpenseSummaryResponse(
            Long projectId,
            String projectName,
            Integer expenseCount,
            Double totalExpenses) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.expenseCount = expenseCount;
        this.totalExpenses = totalExpenses;
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

    public Integer getExpenseCount() {
        return expenseCount;
    }

    public void setExpenseCount(Integer expenseCount) {
        this.expenseCount = expenseCount;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
}