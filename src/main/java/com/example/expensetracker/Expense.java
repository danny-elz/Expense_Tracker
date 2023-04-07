/**

 The Expense class represents an individual expense with a date, description, and amount.
 The class also contains a SimpleStringProperty representing the expense type.
 The expense date is stored as a string in the format "dd/MM/yyyy".
 The class is used in the ExpenseTable class to store individual expense entries.
 The class provides two constructors - one with a type parameter and one without.
 If the type parameter is not provided, the type property is initialized as null.
 The class contains getter and setter methods for the date, description, amount, and type fields.
 The class also provides a static constant DATE_FORMAT field representing the date format "dd/MM/yyyy".
 This field is used to format and parse the expense date.
 The type field is stored as a SimpleStringProperty for binding to a JavaFX UI element.
 @author [Julian, Danny]
 */
package com.example.expensetracker;
import javafx.beans.property.SimpleStringProperty;
import java.time.format.DateTimeFormatter;

public class Expense {
    /**
     * The date format used to store the expense date as a string.
     */
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * The date of the expense in the format "dd/MM/yyyy".
     */
    private String date;

    /**
     * The description of the expense.
     */
    private String description;

    /**
     * The amount of the expense.
     */
    private String amount;

    /**
     * The type of the expense stored as a SimpleStringProperty for binding to a JavaFX UI element.
     */
    private SimpleStringProperty type;

    /**
     * Creates a new Expense instance with the specified date, description, amount, and type.
     *
     * @param date The date of the expense in the format "dd/MM/yyyy".
     * @param description The description of the expense.
     * @param amount The amount of the expense.
     * @param type The type of the expense.
     */
    public Expense(String date, String description, String amount, String type) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.type = new SimpleStringProperty(type);
    }

    /**
     * Creates a new Expense instance with the specified date, description, and amount.
     * The type property is initialized as null.
     *
     * @param date The date of the expense in the format "dd/MM/yyyy".
     * @param description The description of the expense.
     * @param amount The amount of the expense.
     */
    public Expense(String date, String description, String amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    /**
     * Gets the amount of the expense.
     *
     * @return The amount of the expense.
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the expense to the specified value.
     *
     * @param amount The new amount value.
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Gets the date of the expense in the format "dd/MM/yyyy".
     *
     * @return The date of the expense.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the expense to the specified value.
     *
     * @param date The new date value in the format "dd/MM/yyyy".
     */
    public void setDate(String date) {
        this.date = date;
    }

/**
 * Gets the description of the expense.
 *
 * @return The description of the expense
 */
public String getDescription() {
    return description;
}

    /**
     * Sets the description of the expense to the specified value.
     *
     * @param description The new description value.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Gets the DateTimeFormatter instance used to format and parse the expense date.
     *
     * @return The DateTimeFormatter instance used to format and parse the expense date.
     */
    public static DateTimeFormatter getDateFormatter() {
        return DATE_FORMAT;
    }
    /**
     * Gets the type of the expense.
     *
     * @return The type of the expense.
     */
    public String getType() {
        return type.get();
    }
    /**
     * Sets the type of the expense to the specified value.
     *
     * @param type The new type value.
     */
    public void setType(String type) {
        this.type.set(type);
    }
    /**
     * Gets the SimpleStringProperty instance representing the type of the expense.
     *
     * @return The SimpleStringProperty instance representing the type of the expense.
     */
    public SimpleStringProperty typeProperty() {
        return type;
    }
}
