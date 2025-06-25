/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progfinal;

/**
 *
 * @author Jessica Mungal
 */
import javax.swing.*;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TaskManager {
    private int totalMessagesSent = 0;
    private int messageCount = 0;

    // Generate random 10-digit message ID
    public String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    // Validate recipient phone number
    public boolean checkRecipientCell(String cellNumber) {
        return cellNumber.matches("^\\+\\d{1,3}\\d{7,10}$");
    }

    // Validate message length
    public boolean isMessageValid(String message) {
        return message.length() <= 250;
    }

    // How many characters the message exceeds 250 by
    public int getMessageExcess(String message) {
        return message.length() - 250;
    }

    // Create message hash (format: 00:1:HELLOWORLD)
    public String createMessageHash(String messageID, int msgNum, String message) {
        String[] words = message.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return messageID.substring(0, 2) + ":" + msgNum + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
    }

    // Store message using Gson
    public void storeMessage(String messageID, String hash, String recipient, String messageText) {
        Message msg = new Message(messageID, hash, recipient, messageText);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("stored_messages.json", true)) {
            writer.write(gson.toJson(msg));
            writer.write(",\n"); // Optional comma to separate
            JOptionPane.showMessageDialog(null, "Message successfully stored in JSON file using Gson.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving message: " + e.getMessage());
        }
    }

    // Main interaction method
    public void sendMessages() {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        int numMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to enter?"));

        while (messageCount < numMessages) {
            String recipient = JOptionPane.showInputDialog("Enter recipient phone number (include +country code):");
            if (!checkRecipientCell(recipient)) {
                JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code.");
                continue;
            }

            String message = JOptionPane.showInputDialog("Enter message (max 250 characters):");
            if (!isMessageValid(message)) {
                int excess = getMessageExcess(message);
                JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + excess + ", please reduce size.");
                continue;
            }

            String messageID = generateMessageID();
            String hash = createMessageHash(messageID, messageCount + 1, message);

            String[] options = {"Send Message", "Disregard Message", "Store Message"};
            int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Message Options",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0 -> {
                    JOptionPane.showMessageDialog(null, "Message successfully sent.");
                    totalMessagesSent++;
                    messageCount++;
                    JOptionPane.showMessageDialog(null,
                            "Message ID: " + messageID + "\nMessage Hash: " + hash +
                                    "\nRecipient: " + recipient + "\nMessage: " + message);
                }
                case 1 -> JOptionPane.showMessageDialog(null, "Press 0 to delete message."); // Stub
                case 2 -> storeMessage(messageID, hash, recipient, message); // Use Gson
                default -> JOptionPane.showMessageDialog(null, "Invalid option selected.");
            }
        }

        JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessagesSent);
    }

    public int returnTotalMessages() {
        return totalMessagesSent;
    }
}
