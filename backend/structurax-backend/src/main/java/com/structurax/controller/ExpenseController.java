package com.structurax.controller;

import com.structurax.dto.CostAnalysisResponse;
import com.structurax.dto.ExpenseRequest;
import com.structurax.dto.ExpenseSummaryResponse;
import com.structurax.entity.Expense;
import com.structurax.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(
            ExpenseService expenseService) {

        this.expenseService = expenseService;
    }

    @PostMapping
    public Expense createExpense(
            @RequestBody ExpenseRequest request) {

        return expenseService
                .createExpense(request);
    }

    @GetMapping("/{id}")
    public Expense getExpense(
            @PathVariable Long id) {

        return expenseService
                .getExpense(id);
    }

    @GetMapping("/project/{projectId}")
    public List<Expense> getProjectExpenses(
            @PathVariable Long projectId) {

        return expenseService
                .getProjectExpenses(projectId);
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(
            @PathVariable Long id) {

        expenseService.deleteExpense(id);

        return "Expense Deleted Successfully";
    }

    @GetMapping("/project/{projectId}/summary")
    public ExpenseSummaryResponse getSummary(
            @PathVariable Long projectId) {

        return expenseService
                .getExpenseSummary(projectId);
    }

    @GetMapping("/project/{projectId}/analysis")
    public CostAnalysisResponse getAnalysis(
            @PathVariable Long projectId) {

        return expenseService
                .getCostAnalysis(projectId);
    }
}