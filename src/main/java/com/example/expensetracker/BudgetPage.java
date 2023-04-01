package com.example.expensetracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BudgetPage extends Stage {

    private GridPane layout;
    private PieChart chart;
    private TextField foodBudgetField;
    private TextField entertainmentBudgetField;
    private TextField transportationBudgetField;
    private Label foodBudgetLabel;
    private Label entertainmentBudgetLabel;
    private Label transportationBudgetLabel;
    private Button saveButton;

    public BudgetPage() {
        layout = new GridPane();
        Scene scene = new Scene(layout, 700, 800);

        chart = new PieChart();
        chart.setTitle("Budget Progress");

        foodBudgetLabel = new Label("Food Budget:");
        foodBudgetField = new TextField();

        entertainmentBudgetLabel = new Label("Entertainment Budget:");
        entertainmentBudgetField = new TextField();

        transportationBudgetLabel = new Label("Transportation Budget:");
        transportationBudgetField = new TextField();

        // Save the user's budget and update the chart
        saveButton = new Button("Save Budget");
        saveButton.setOnAction(e -> {
            double foodBudget = Double.parseDouble(foodBudgetField.getText());
            double entertainmentBudget = Double.parseDouble(entertainmentBudgetField.getText());
            double transportationBudget = Double.parseDouble(transportationBudgetField.getText());

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Food", foodBudget),
                    new PieChart.Data("Entertainment", entertainmentBudget),
                    new PieChart.Data("Transportation", transportationBudget)
            );
            chart.setData(pieChartData);
        });

        layout.add(chart, 0, 0, 4, 4);
        layout.add(foodBudgetLabel, 0, 5);
        layout.add(foodBudgetField, 1, 5);
        layout.add(entertainmentBudgetLabel, 0, 6);
        layout.add(entertainmentBudgetField, 1, 6);
        layout.add(transportationBudgetLabel, 0, 7);
        layout.add(transportationBudgetField, 1, 7);
        layout.add(saveButton, 0, 8, 2, 1);

        setScene(scene);
        setTitle("Budget");
    }
}