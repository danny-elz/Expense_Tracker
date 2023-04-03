package com.example.expensetracker;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String date;
    private String description;
    private String amount;

    private SimpleStringProperty type;



    public Expense(String date, String description, String amount, String type) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.type = new SimpleStringProperty(type);
    }

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

    public static DateTimeFormatter getDateFormatter() {
        return DATE_FORMAT;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }


}

