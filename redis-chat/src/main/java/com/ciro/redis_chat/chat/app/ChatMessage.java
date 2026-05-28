package com.ciro.redis_chat.chat.app;

public class ChatMessage {

    private String user; // We schreibt?
    private String message;
    private String timestamp;


    //Konstruktor
    public ChatMessage(String user, String message, String timestamp){
        this.user = user;
        this.message = message;
        this.timestamp = timestamp;

    }


    //Getter
    public String getUser(){
        return user;
    }
    public String getMessage(){
        return message;
    }

    public String getTimestamp(){
        return timestamp;
    }


    @Override
    public String toString() {
        return "ChatMessage{" +
                "user='" + user + '\'' +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}


/*Ohne ChatMessage:
java// Nur ein einfacher String
publish("Anna: Hallo!")
// ← unstrukturiert, schwer zu verarbeiten


Mit ChatMessage:
java// Strukturiertes Objekt
ChatMessage msg = new ChatMessage("Anna", "Hallo!", "10:30");
msg.getUser();      // "Anna"
msg.getMessage();   // "Hallo!"
msg.getTimestamp(); // "10:30"*/
