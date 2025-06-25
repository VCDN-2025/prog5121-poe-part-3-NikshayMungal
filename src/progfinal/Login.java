/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progfinal;

/**
 *
 * @author Jessica Mungal
 */import javax.swing.JOptionPane;
import java.util.regex.*;

public class Login {
    // Stored registration data
    private String username;
    private String password;
    private String cellphone;

    // Check if username contains underscore and is no more than 5 characters
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Check if password is at least 8 chars, contains uppercase, number, and special char
    public boolean checkPasswordComplexity(String password) {
        boolean length = password.length() >= 8;
        boolean upper = password.matches(".*[A-Z].*");
        boolean digit = password.matches(".*\\d.*");
        boolean special = password.matches(".*[!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>\\/?].*");
        return length && upper && digit && special;
    }

    // Check if cell number has international code and is no more than 10 digits after the "+"
    public boolean checkCellPhoneNumber(String number) {
        // ChatGPT citation:
        // OpenAI. (2025). ChatGPT (June 2025 version) [Large language model]. https://chat.openai.com/
        return number.matches("^\\+\\d{1,3}\\d{7,10}$");
    }

    // Register a user and return message
    public String registerUser(String user, String pass, String cell) {
        boolean isUsernameOk = checkUserName(user);
        boolean isPasswordOk = checkPasswordComplexity(pass);
        boolean isCellOk = checkCellPhoneNumber(cell);

        if (!isUsernameOk) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        } else if (!isPasswordOk) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        } else if (!isCellOk) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        } else {
            this.username = user;
            this.password = pass;
            this.cellphone = cell;
            return "User successfully registered.";
        }
    }

    // Login using stored username and password
    public boolean loginUser(String user, String pass) {
        return this.username != null && user.equals(this.username) && pass.equals(this.password);
    }

    // Return login status message
    public String returnLoginStatus(boolean loginSuccessful, String firstName, String lastName) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

