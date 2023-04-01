package com.example.expensetracker;

public class ExpenseRow {
    private static int count = 0;

    private int id;
    private Expense expense;

    public ExpenseRow(Expense expense) {
        this.expense = expense;
        this.id = count++;
    }

    public int getId() {
        return id;
    }

    public Expense getExpense() {
        return expense;
    }
}
