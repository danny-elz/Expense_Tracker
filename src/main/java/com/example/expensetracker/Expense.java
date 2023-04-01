package com.example.expensetracker;
public class Expense {

    private String date;
    private String description;
    private String amount;

    public Expense(String date, String description, String amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
