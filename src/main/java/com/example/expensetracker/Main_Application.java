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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.example.expensetracker.Login_File.addUser_FILE;

public class Main_Application extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // Creating the main layout
        GridPane mainRoot = new GridPane();
        Scene scene = new Scene(mainRoot, 700, 800);
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

        // Adding the columns to the table
        table.getColumns().add(date);
        table.getColumns().add(description);
        table.getColumns().add(amount);

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

        mainRoot.add(_DATE, 0, 3);
        mainRoot.add(_DESCRIPTION, 3, 3);
        mainRoot.add(_AMOUNT, 1, 3);

        // Creating a combo box for filtering expenses
        ComboBox<String> AMOUNT_CB = new ComboBox<>();
        AMOUNT_CB.setValue("Filter");
        List<String> filterOptions = new ArrayList<>();
        filterOptions.add("Expenses");
        filterOptions.add("Income");
        AMOUNT_CB.getItems().addAll(filterOptions);
        AMOUNT_CB.setPrefWidth(200);

        mainRoot.add(AMOUNT_CB, 3, 5);

        // Creating a button for adding expenses
        Button ADD_EXPENSE = new Button("Add");
        ADD_EXPENSE.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        ADD_EXPENSE.setPrefWidth(200);

        mainRoot.add(ADD_EXPENSE, 0, 5);

        // Creating a button for deleting expenses

        Button DELETE_EXPENSE = new Button("Remove");
        DELETE_EXPENSE.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        DELETE_EXPENSE.setPrefWidth(200);

        mainRoot.add(DELETE_EXPENSE ,0,12);

        // Setting the layout's horizontal and vertical gap
        mainRoot.setHgap(10);
        mainRoot.setVgap(10);

        // Creating labels for the columns
        Text DATE = new Text();
        DATE.setText("Date");
        mainRoot.add(DATE, 0, 1);

        Text DESCRIPTION = new Text();
        DESCRIPTION.setText("Description");
        mainRoot.add(DESCRIPTION, 3, 1);

        Text AMOUNT = new Text();
        AMOUNT.setText("Amount");
        mainRoot.add(AMOUNT, 1, 1);

        // Setting up the action for adding expenses
        ADD_EXPENSE.setOnAction(e -> {
            String datePicker = _DATE.getText();
            String _description = _DESCRIPTION.getText();
            String _amount = _AMOUNT.getText();

            // Adding the expense to the table
            table.getItems().add(new Expense(datePicker, _description, _amount));

            // Clearing the text fields
            _DATE.clear();
            _DESCRIPTION.clear();
            _AMOUNT.clear();
        });

        // Spanning some layout elements across multiple columns
        GridPane.setColumnSpan(stackPane, 4);
        GridPane.setColumnSpan(DATE, 2);
        GridPane.setColumnSpan(DESCRIPTION, 2);
        GridPane.setColumnSpan(AMOUNT, 2);

        // Creating the login layout
        GridPane loginRoot = new GridPane();
        Scene loginScene = new Scene(loginRoot, 400, 250);
        loginRoot.setPadding(new Insets(20, 10, 10, 10));

        // Creating labels and text fields for the login layout
        loginRoot.add(new Label("Don't have an account? Sign up here"), 0, 10);
        Label blank_2 = new Label("");
        blank_2.setPrefHeight(10);
        loginRoot.add(blank_2, 0, 11);

        TextField signUpUsernameField = new TextField();
        TextField signUpPasswordField = new TextField();
        loginRoot.add(new Label("New Username"), 0, 12);
        loginRoot.add(signUpUsernameField, 1, 12);
        loginRoot.add(new Label("New Password"), 0, 13);
        loginRoot.add(signUpPasswordField, 1, 13);

        TextField loginUsernameField = new TextField();
        PasswordField loginPasswordField = new PasswordField();
        loginRoot.add(new Label("Username:"), 0, 5);
        loginRoot.add(loginUsernameField, 1, 5);
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
        signupButton.setOnAction(e -> {
            String username = signUpUsernameField.getText();
            String password = signUpPasswordField.getText();
            User newUser = new User(username, password);

            // Checking if the username and password fields are not empty before adding the user
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



