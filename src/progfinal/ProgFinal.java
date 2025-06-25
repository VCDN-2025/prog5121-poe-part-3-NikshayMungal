/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package progfinal;

import javax.swing.JOptionPane;

/**
 *
 * @author Jessica Mungal
 */
public class ProgFinal {

    /**
     * @param args the command line arguments
     */
   public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        TaskManager taskManager = new TaskManager();
        Array messageData = new Array();

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat Registration");

        // Registration
        String username = JOptionPane.showInputDialog("Enter username (must include '_' and <=5 chars):");
        String password = JOptionPane.showInputDialog("Enter password (8+ chars, capital, number, special char):");
        String cell = JOptionPane.showInputDialog("Enter cell number (e.g. +27830000000):");

        String regResult = login.registerUser(username, password, cell);
        JOptionPane.showMessageDialog(null, regResult);

        // Exit if registration fails
        if (!regResult.equals("User successfully registered.")) {
            return;
        }

        // Ask for full name
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");

        // Login
        String inputUsername = JOptionPane.showInputDialog("Login - Enter username:");
        String inputPassword = JOptionPane.showInputDialog("Login - Enter password:");
        boolean loggedIn = login.loginUser(inputUsername, inputPassword);

        JOptionPane.showMessageDialog(null, login.returnLoginStatus(loggedIn, firstName, lastName));

        if (!loggedIn) return;

        // Menu Loop
        boolean running = true;
        while (running) {
            String option = JOptionPane.showInputDialog("""
                    Choose an option:
                    1 - Send Messages
                    2 - Show Sent Message Report
                    3 - Show Recently Sent (Coming Soon)
                    4 - Quit""");

            switch (option) {
                case "1" -> {
                    taskManager.sendMessages(); // Handles storage in file

                    // Optionally: you could capture message data here and feed it into MessageData arrays
                    // To fully populate MessageData, you'd need to pass back details from TaskManager
                    // Or combine them
                }
                case "2" -> messageData.displaySentReport();
                case "3" -> JOptionPane.showMessageDialog(null, "Coming Soon.");
                case "4" -> {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    running = false;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }
}
}