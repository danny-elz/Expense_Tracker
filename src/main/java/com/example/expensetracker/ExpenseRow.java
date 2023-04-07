/**

 The ExpenseRow class represents a row in the expense table.
 It contains an ID and an Expense object.
 The ID is used to uniquely identify the expense row in the table.
 The class is used in the ExpenseTable class to store individual expense entries.
 The class contains a static count field to keep track of the total number of expense rows created.
 When a new ExpenseRow object is created, the count field is incremented and assigned as the object's ID.
 @author [Author Name]
 */
package com.example.expensetracker;
public class ExpenseRow {
    /**
     * The static count field used to keep track of the total number of expense rows created.
     */
    private static int count = 0;
    /**
     * The ID of the expense row.
     */
    private int id;
    /**
     * The Expense object representing the expense in the row.
     */
    private Expense expense;
    /**
     * Creates a new ExpenseRow instance with the specified Expense object.
     * The count field is incremented and assigned as the ID of the new object.
     *
     * @param expense The Expense object representing the expense in the row.
     */
    public ExpenseRow(Expense expense) {
        this.expense = expense;
        this.id = count++;
    }
    /**
     * Gets the ID of the expense row.
     *
     * @return The ID of the expense row.
     */
    public int getId() {
        return id;
    }
    /**
     * Gets the Expense object representing the expense in the row.
     *
     * @return The Expense object representing the expense in the row.
     */
    public Expense getExpense() {
        return expense;
    }
}