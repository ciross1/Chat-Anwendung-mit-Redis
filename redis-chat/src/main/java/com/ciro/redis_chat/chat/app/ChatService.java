package com.ciro.redis_chat.chat.app;


import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ChatService {

    private final ChatPublisher chatPublisher;
    private final ChatRepository chatRepository;

    public ChatService(ChatPublisher chatPublisher, ChatRepository chatRepository){
        this.chatPublisher = chatPublisher;
        this.chatRepository = chatRepository;
    }

    public void sendMessage(String user, String message){

        //1, nachricht formatieren:
        String fullMessage = "[" + LocalTime.now() + "] " + user + ": " + message;

        // Über Redis Pub/Sub senden
        chatPublisher.publish(fullMessage);

        // in Redis speichern
        chatRepository.save(fullMessage);

    }


    //Alle nachrichten abrufen:
    public List<String> getAllMessages(){
        return chatRepository.findAll();
    }

    //Chat löschen
    public String clearChat(){
       chatRepository.delete();

       return "chat was deleted";

    }



}
