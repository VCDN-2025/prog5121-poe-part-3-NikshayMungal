package progfinal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jessica Mungal
 */
public class Message {
   
    String messageID;
    String messageHash;
    String recipient;
    String message;

    public Message(String messageID, String messageHash, String recipient, String message) {
        this.messageID = messageID;
        this.messageHash = messageHash;
        this.recipient = recipient;
        this.message = message;
    }
}


