/**

 The User class represents a user with a username and password.
 It provides methods for getting and setting the username and password.
 The class is used in the Login_File class to store user credentials.
 @author [Julian]
 */
package com.example.expensetracker;
public class User {
    /**
     * The user's username.
     */
    private String username;

    /**
     * The user's password.
     */
    private String password;

    /**
     * Creates a new User instance with the specified username and password.
     *
     * @param username The user's username.
     * @param password The user's password.
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the user's password to the specified value.
     *
     * @param password The new password value.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Gets the user's username.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the user's username to the specified value.
     *
     * @param username The new username value.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}