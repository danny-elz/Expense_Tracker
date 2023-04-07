/**
 The Login_File class handles reading and writing user credentials to a file.
 It provides methods for reading user credentials from a file and writing new user credentials to a file.
 The user credentials are stored in the "users.txt" file.
 @author [Danny]
 */
package com.example.expensetracker;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Login_File {
    /**
     * The path of the file to store user credentials.
     */
    private static final String userFilePath = "users.txt";
    /**
     * Reads user credentials from the file and returns as a list of User objects.
     *
     * @return The list of users read from the file.
     * @throws IOException if there is an error reading from the file.
     */
    public static List<User> readUsersFromFile() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(userFilePath);

        // If the file doesn't exist, return an empty list
        if (!file.exists()) {
            return users;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Read each line of the file until end of file is reached
            String loginLine;
            while ((loginLine = reader.readLine()) != null) {
                // Split the line by a comma to get the username and password
                String[] parts = loginLine.split(",");
                String username = parts[0];
                String password = parts[1];
                // Create a new User object with the extracted username and password and add it to the list
                users.add(new User(username, password));
            }
        }

        // Return the list of users
        return users;
    }

    /**
     * Writes a new user's login details to the file.
     *
     * @param user The User object containing the new user's login details.
     */
    public static void writeUserToFile(User user) {
        try (BufferedWriter BR = new BufferedWriter(new FileWriter(userFilePath, true))) {
            // Write the new user's username and password to the file
            BR.write(user.getUsername() + "," + user.getPassword() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a new user's credentials to the file by calling the writeUserToFile method.
     *
     * @param user The User object containing the new user's login details.
     * @throws IOException if there is an error writing to the file.
     */
    public static void addUser_FILE(User user) throws IOException {
        writeUserToFile(user);
    }
}