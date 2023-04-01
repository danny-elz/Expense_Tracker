package com.example.expensetracker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Login_File {
    private static final String FILE_PATH = "users.txt";
    public static List<User> readUsersFromFile() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return users;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                users.add(new User(username, password));
            }
        }
        return users;
    }
    public static void writeUser_FILE(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(user.getUsername() + "," + user.getPassword() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addUser_FILE(User user) throws IOException {
        writeUser_FILE(user);
    }
}
