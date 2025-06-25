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
public class Array {
     private String[] sentMessages = new String[8];
    private String[] disregardedMessages = new String[8];
    private String[] storedMessages = new String[8];
    private String[] messageHashes = new String[8];
    private String[] messageIDs = new String[8];
    private String[] recipients = new String[8];

    private int sentCount = 0;
    private int disregardedCount = 0;
    private int storedCount = 0;
    private int messageIndex = 0;

    // Adds a sent message
    public void addSentMessage(String id, String hash, String recipient, String message) {
        if (sentCount < 8) {
            sentMessages[sentCount] = message;
            messageHashes[sentCount] = hash;
            messageIDs[sentCount] = id;
            recipients[sentCount] = recipient;
            sentCount++;
            messageIndex++;
        } else {
            JOptionPane.showMessageDialog(null, "Sent message limit reached.");
        }
    }

    // Adds a disregarded message
    public void addDisregardedMessage(String message) {
        if (disregardedCount < 8) {
            disregardedMessages[disregardedCount++] = message;
            messageIndex++;
        }
    }

    // Adds a stored message
    public void addStoredMessage(String message) {
        if (storedCount < 8) {
            storedMessages[storedCount++] = message;
            messageIndex++;
        }
    }

    // a. Display all sent messages with sender + recipient
    public void displaySentMessages() {
        StringBuilder result = new StringBuilder("Sent Messages:\n");
        for (int i = 0; i < sentCount; i++) {
            result.append("To ").append(recipients[i]).append(": ").append(sentMessages[i]).append("\n");
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }

    // b. Display longest sent message
    public void displayLongestSentMessage() {
        String longest = "";
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i].length() > longest.length()) {
                longest = sentMessages[i];
            }
        }
        JOptionPane.showMessageDialog(null, "Longest sent message:\n" + longest);
    }

    // c. Search by message ID
    public void searchMessageByID(String id) {
        for (int i = 0; i < sentCount; i++) {
            if (messageIDs[i].equals(id)) {
                JOptionPane.showMessageDialog(null, "Recipient: " + recipients[i] + "\nMessage: " + sentMessages[i]);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message ID not found.");
    }

    // d. Search all messages by recipient
    public void searchMessagesByRecipient(String target) {
        StringBuilder result = new StringBuilder("Messages to " + target + ":\n");
        boolean found = false;
        for (int i = 0; i < sentCount; i++) {
            if (recipients[i].equals(target)) {
                result.append(sentMessages[i]).append("\n");
                found = true;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "No messages found for recipient.");
        } else {
            JOptionPane.showMessageDialog(null, result.toString());
        }
    }

    // e. Delete by message hash
    public void deleteMessageByHash(String hash) {
        for (int i = 0; i < sentCount; i++) {
            if (messageHashes[i].equals(hash)) {
                String deleted = sentMessages[i];
                for (int j = i; j < sentCount - 1; j++) {
                    sentMessages[j] = sentMessages[j + 1];
                    recipients[j] = recipients[j + 1];
                    messageIDs[j] = messageIDs[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                }
                sentMessages[sentCount - 1] = null;
                messageHashes[sentCount - 1] = null;
                messageIDs[sentCount - 1] = null;
                recipients[sentCount - 1] = null;
                sentCount--;
                JOptionPane.showMessageDialog(null, "Message \"" + deleted + "\" successfully deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message hash not found.");
    }

    // f. Display full report of all sent messages
    public void displaySentReport() {
        StringBuilder report = new StringBuilder("Sent Message Report:\n");
        for (int i = 0; i < sentCount; i++) {
            report.append("Message Hash: ").append(messageHashes[i]).append("\n")
                  .append("Recipient: ").append(recipients[i]).append("\n")
                  .append("Message: ").append(sentMessages[i]).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    // Returns number of sent messages
    public int getTotalMessages() {
        return sentCount;
    }
}