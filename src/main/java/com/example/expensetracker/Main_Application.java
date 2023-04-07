/**
 The Main_Application class is responsible for creating the main layout and managing the user interface of the Expense Tracker application.
 It includes methods for displaying expenses, adding expenses, removing expenses, filtering expenses, saving expenses to a CSV file, logging in and signing up users.
 This class extends the Application class from JavaFX to create a graphical user interface.
 @author [Danny, Julian]
 */

package com.example.expensetracker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import static com.example.expensetracker.Login_File.addUser_FILE;

public class Main_Application extends Application {
    /**
     * The main method launches the application.
     *
     * @param args - the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method sets up the graphical user interface and defines the actions
     * for each button and text field. It creates the main layout for the application,
     * which includes a table for displaying expenses, text fields for adding expenses,
     * and buttons for filtering expenses, adding expenses, removing expenses, and saving
     * expenses to a CSV file. It also includes a button for logging out and a button for
     * opening the budget page. Additionally, it creates a separate login layout for
     * authenticating users.
     *
     * @param stage - the primary stage for the application
     */
    @Override
    public void start(Stage stage) {
        // Creating the main layout
        GridPane mainRoot = new GridPane();
        Scene scene = new Scene(mainRoot, 700, 700);
        mainRoot.setPadding(new Insets(20.10));

        // Creating the table for displaying expenses
        TableView<Expense> table = new TableView<>();
        // Creating columns for the table
        TableColumn<Expense, Date> date = new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Expense, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Expense, Integer> amount = new TableColumn<>("Amount");
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Expense, String> type = new TableColumn<>("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        // Adding the columns to the table
        table.getColumns().add(date);
        table.getColumns().add(description);
        table.getColumns().add(amount);
        table.getColumns().add(type);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Creating a rectangle to serve as a background for the table
        Rectangle rectangle = new Rectangle(200, 400, Color.WHITE);
        StackPane stackPane = new StackPane(rectangle, table);

        stackPane.setAlignment(Pos.BOTTOM_CENTER);
        mainRoot.add(stackPane, 0, 14, 1, 20);

        // Creating text fields for adding expenses
        TextField _AMOUNT = new TextField();
        TextField _DESCRIPTION = new TextField();
        TextField _DATE = new TextField();
        _DATE.setPromptText("dd/mm/yyyy");

        mainRoot.add(_DATE, 0, 5);
        mainRoot.add(_DESCRIPTION, 3, 5);
        mainRoot.add(_AMOUNT, 1, 5);

        // Creating a combo box for filtering expenses
        ComboBox<String> AMOUNT_CB = new ComboBox<>();
        AMOUNT_CB.setValue("Filter");
        List<String> filterOptions = new ArrayList<>();
        filterOptions.add("Expenses");
        filterOptions.add("Income");
        filterOptions.add("none");
        AMOUNT_CB.getItems().addAll(filterOptions);
        AMOUNT_CB.setPrefWidth(200);

        mainRoot.add(AMOUNT_CB, 3, 7);

        // Creating a button for adding expenses
        Button ADD_EXPENSE = new Button("Add");
        ADD_EXPENSE.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        ADD_EXPENSE.setPrefWidth(200);

        mainRoot.add(ADD_EXPENSE, 0, 7);

        // Creating a button for deleting expenses
        Button DELETE_EXPENSE = new Button("Remove");
        DELETE_EXPENSE.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        DELETE_EXPENSE.setPrefWidth(200);

        mainRoot.add(DELETE_EXPENSE, 0, 11);

        // Creating a button for opening the budget page
        Button BUDGET_PAGE = new Button("Budget");
        BUDGET_PAGE.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        BUDGET_PAGE.setPrefWidth(200);

        mainRoot.add(BUDGET_PAGE, 3, 11);

        // Setting up the action for opening the budget page
        BUDGET_PAGE.setOnAction(e -> {
            // Creating an instance of the budget page and showing the stage
            BudgetPage budgetPage = new BudgetPage();
            budgetPage.show();
        });

        Button SAVE_EXPENSES = new Button("Save Expenses");
        SAVE_EXPENSES.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        SAVE_EXPENSES.setPrefWidth(200);

        mainRoot.add(SAVE_EXPENSES, 3, 9);

        Button LOG_OUT = new Button("Log Out");
        LOG_OUT.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        LOG_OUT.setPrefWidth(80);

        mainRoot.add(LOG_OUT, 0, 1);

        // Setting the layout's horizontal and vertical gap
        mainRoot.setHgap(10);
        mainRoot.setVgap(10);

        // Creating labels for the columns
        Text DATE = new Text();
        DATE.setText("Date");
        mainRoot.add(DATE, 0, 3);

        Text DESCRIPTION = new Text();
        DESCRIPTION.setText("Description");
        mainRoot.add(DESCRIPTION, 3, 3);

        Text AMOUNT = new Text();
        AMOUNT.setText("Amount");
        mainRoot.add(AMOUNT, 1, 3);

        // Setting up the action for adding expenses
        ADD_EXPENSE.setOnAction(e -> {
            String datePicker = _DATE.getText();
            String _description = _DESCRIPTION.getText();
            String _amount = _AMOUNT.getText();

            try {
                LocalDate.parse(datePicker, Expense.DATE_FORMAT);
            } catch (DateTimeParseException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Date");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a date following the format of dd/mm/yyyy.");
                alert.show();
                return;
            }

            try {
                Double.parseDouble(_amount);
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Amount");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid amount.");
                alert.show();
                return;
            }

            // Adding the expense to the table
            if (AMOUNT_CB.getSelectionModel().getSelectedItem().equals("Filter") || (AMOUNT_CB.getSelectionModel().getSelectedItem().equals("none"))) {
                table.getItems().add(new Expense(datePicker, _description, _amount));
            } else {
                table.getItems().add(new Expense(datePicker, _description, _amount, AMOUNT_CB.getSelectionModel().getSelectedItem()));
            }

            // Clearing the text fields
            _DATE.clear();
            _DESCRIPTION.clear();
            _AMOUNT.clear();
        });
        // Action for deleting expenses
        DELETE_EXPENSE.setOnAction(e -> {
            Expense selectedExpense = table.getSelectionModel().getSelectedItem();
            if (selectedExpense != null) {
                table.getItems().remove(selectedExpense);
            } else {
                // Displaying an error message if no expense is selected
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select an expense to delete.");
                alert.show();
            }
        });

        // Action for saving expenses
        SAVE_EXPENSES.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Expenses");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));

            // Showing the dialog
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                try {
                    // Writing the expenses to the file
                    FileWriter writer = new FileWriter(file);
                    for (Expense expense : table.getItems()) {
                        writer.write(expense.getDate() + "," + expense.getDescription() + "," + expense.getAmount() + "," + expense.getType() + "\n");
                    }
                    writer.close();

                    // Displaying a success message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Expenses Saved");
                    alert.setHeaderText(null);
                    alert.setContentText("Your expenses have been saved to: " + file.getAbsolutePath());
                    alert.showAndWait();

                } catch (IOException ex) {
                    // Displaying an error message if there was a problem writing to the file
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("There was a problem saving your expenses.");
                    alert.showAndWait();
                }
            }
        });

        // Spanning some layout elements across multiple columns
        GridPane.setColumnSpan(stackPane, 4);
        GridPane.setColumnSpan(DATE, 2);
        GridPane.setColumnSpan(DESCRIPTION, 2);
        GridPane.setColumnSpan(AMOUNT, 2);

        //login layout
        GridPane loginRoot = new GridPane();
        Scene loginScene = new Scene(loginRoot, 400, 250);
        loginRoot.setPadding(new Insets(20, 10, 10, 10));

        // Creating labels and text fields for the login layout
        loginRoot.add(new Label("Don't have an account? Sign up here"), 0, 10);
        Label blank_2 = new Label("");
        blank_2.setPrefHeight(10);
        loginRoot.add(blank_2, 0, 11);

        // A text field for the user to enter a new username for sign up
        TextField signUpUsernameField = new TextField();
        // A text field for the user to enter a new password for sign up
        TextField signUpPasswordField = new TextField();
        loginRoot.add(new Label("New Username"), 0, 12);
        loginRoot.add(signUpUsernameField, 1, 12);
        loginRoot.add(new Label("New Password"), 0, 13);
        loginRoot.add(signUpPasswordField, 1, 13);

        // A text field for the user to enter their login username
        TextField loginUsernameField = new TextField();

        // A password field for the user to enter their login password
        PasswordField loginPasswordField = new PasswordField();

        // Adding a label for the login username and adding it to the GridPane
        loginRoot.add(new Label("Username:"), 0, 5);
        loginRoot.add(loginUsernameField, 1, 5);

        // Adding a label for the login password and adding it to the GridPane
        loginRoot.add(new Label("Password:"), 0, 6);
        loginRoot.add(loginPasswordField, 1, 6);

        // Creating buttons for logging in and signing up
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            if (isValidUser(loginUsernameField.getText(), loginPasswordField.getText())) {
                // Switching to the main scene if the user is valid
                stage.setScene(scene);
            } else {
                // Displaying an error message if the user is invalid
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Login");
                alert.setHeaderText(null);
                alert.setContentText("Username or password is incorrect.");
                alert.showAndWait();
            }
        });

        Button signupButton = new Button("Sign Up");

        // Sign up button action
        signupButton.setOnAction(e -> {
            String username = signUpUsernameField.getText();
            String password = signUpPasswordField.getText();
            User newUser = new User(username, password);

            // Checking if the username and password fields aren't empty before adding the user
            if (!Objects.equals(username, "") && !Objects.equals(password, "")) {
                try {
                    addUser_FILE(newUser);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Displaying a success message if the user is added
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("User created successfully.");
                alert.showAndWait();
            } else {
                // Displaying an error message if the username or password fields are empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to create user.");
                alert.show();
            }
        });

        // Logout button action
        LOG_OUT.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Log Out");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to log out?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // Switching to the login scene if the user confirms they want to log out
                stage.setScene(loginScene);
            }
        });

        // Adding the login and signup buttons to the login layout
        loginRoot.add(loginButton, 1, 7);
        loginRoot.add(signupButton, 1, 14);
        Label blank_1 = new Label("");
        blank_1.setPrefHeight(10);
        loginRoot.add(blank_1, 1, 8);


        // Setting the stage's title and showing the login scene
        stage.setTitle("Expense Tracker");
        stage.setScene(loginScene);
        stage.show();
    }

    /**
     * Determines if the given username and password are valid credentials for a registered user.
     *
     * @param username The username to check
     * @param password The password to check
     * @return True if the username and password match a registered user, false otherwise
     */
    private boolean isValidUser(String username, String password) {
        try {
            // Reading the list of users from the user file
            List<User> users = Login_File.readUsersFromFile();
            for (User user : users) {
                // Checking if the given username and password match any of the existing users
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}