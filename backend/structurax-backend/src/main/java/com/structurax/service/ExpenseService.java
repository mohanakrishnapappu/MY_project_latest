package com.structurax.service;

import com.structurax.dto.CostAnalysisResponse;
import com.structurax.dto.ExpenseRequest;
import com.structurax.dto.ExpenseSummaryResponse;
import com.structurax.entity.BoqItem;
import com.structurax.entity.Expense;
import com.structurax.entity.Project;
import com.structurax.repository.BoqItemRepository;
import com.structurax.repository.ExpenseRepository;
import com.structurax.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ProjectRepository projectRepository;
    private final BoqItemRepository boqItemRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            ProjectRepository projectRepository,
            BoqItemRepository boqItemRepository) {

        this.expenseRepository = expenseRepository;
        this.projectRepository = projectRepository;
        this.boqItemRepository = boqItemRepository;
    }

    public Expense createExpense(
            ExpenseRequest request) {

        Project project =
                projectRepository.findById(
                                request.getProjectId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project Not Found"));

        Expense expense =
                new Expense();

        expense.setExpenseName(
                request.getExpenseName());

        expense.setCategory(
                request.getCategory());

        expense.setAmount(
                request.getAmount());

        expense.setExpenseDate(
                request.getExpenseDate());

        expense.setProject(
                project);

        return expenseRepository.save(
                expense);
    }

    public Expense getExpense(
            Long id) {

        return expenseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Expense Not Found"));
    }

    public List<Expense> getProjectExpenses(
            Long projectId) {

        return expenseRepository
                .findByProjectId(projectId);
    }

    public void deleteExpense(
            Long id) {

        Expense expense =
                expenseRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Expense Not Found"));

        expenseRepository.delete(expense);
    }

    public ExpenseSummaryResponse getExpenseSummary(
            Long projectId) {

        Project project =
                projectRepository.findById(projectId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project Not Found"));

        List<Expense> expenses =
                expenseRepository.findByProjectId(projectId);

        double totalExpenses = 0.0;

        for (Expense expense : expenses) {

            totalExpenses +=
                    expense.getAmount();
        }

        return new ExpenseSummaryResponse(
                project.getId(),
                project.getProjectName(),
                expenses.size(),
                totalExpenses
        );
    }

    public CostAnalysisResponse getCostAnalysis(
            Long projectId) {

        Project project =
                projectRepository.findById(projectId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project Not Found"));

        List<BoqItem> boqItems =
                boqItemRepository.findByProjectId(projectId);

        List<Expense> expenses =
                expenseRepository.findByProjectId(projectId);

        double estimatedBudget = 0.0;

        for (BoqItem item : boqItems) {

            estimatedBudget +=
                    item.getTotalCost();
        }

        double actualExpenses = 0.0;

        for (Expense expense : expenses) {

            actualExpenses +=
                    expense.getAmount();
        }

        double variance =
                estimatedBudget - actualExpenses;

        return new CostAnalysisResponse(
                project.getId(),
                project.getProjectName(),
                estimatedBudget,
                actualExpenses,
                variance
        );
    }
}