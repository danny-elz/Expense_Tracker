/**
 The BudgetPage class is used to create a JavaFX stage for tracking a user's budget.
 It contains a pie chart displaying the user's budget progress for food, entertainment, and transportation,
 and fields for inputting and saving new budget values.
 @author [Danny]
 */
package com.example.expensetracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BudgetPage extends Stage {
    /**
     * The layout for the BudgetPage stage.
     */
    private GridPane layout;
    /**
     * The PieChart for displaying the user's budget progress.
     */
    private PieChart chart;
    /**
     * The TextField for inputting the user's food budget.
     */
    private TextField foodBudgetField;
    /**
     * The TextField for inputting the user's entertainment budget.
     */
    private TextField entertainmentBudgetField;
    /**
     * The TextField for inputting the user's transportation budget.
     */
    private TextField transportationBudgetField;
    /**
     * The Label for displaying the food budget field's purpose.
     */
    private Label foodBudgetLabel;
    /**
     * The Label for displaying the entertainment budget field's purpose.
     */
    private Label entertainmentBudgetLabel;
    /**
     * The Label for displaying the transportation budget field's purpose.
     */
    private Label transportationBudgetLabel;
    /**
     * The Button for saving the user's budget and updating the chart.
     */
    private Button saveButton;
    /**
     * Creates a new BudgetPage instance and initializes its components.
     */
    public BudgetPage() {
        layout = new GridPane();
        Scene scene = new Scene(layout, 500, 500);

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
            try {
                // Parse user input to doubles and update chart data
                double foodBudget = Double.parseDouble(foodBudgetField.getText());
                double entertainmentBudget = Double.parseDouble(entertainmentBudgetField.getText());
                double transportationBudget = Double.parseDouble(transportationBudgetField.getText());

                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Food", foodBudget),
                        new PieChart.Data("Entertainment", entertainmentBudget),
                        new PieChart.Data("Transportation", transportationBudget)
                );

                chart.setData(pieChartData);

            } catch (NumberFormatException ex) {
                // If user enters anything but a number, an error message is shown
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid number.");
                alert.showAndWait();
            }
        });

        layout.setPadding(new Insets(20));
        layout.setVgap(10);

        // Add components to the layout
        layout.add(chart, 0, 0, 4, 4);
        layout.add(foodBudgetLabel, 0, 5);
        layout.add(foodBudgetField, 2, 5);
        layout.add(entertainmentBudgetLabel, 0, 6);
        layout.add(entertainmentBudgetField, 2, 6);
        layout.add(transportationBudgetLabel, 0, 7);
        layout.add(transportationBudgetField, 2, 7);
        layout.add(saveButton, 0, 8, 2, 1);

        setScene(scene);
        setTitle("Budget");
    }
}