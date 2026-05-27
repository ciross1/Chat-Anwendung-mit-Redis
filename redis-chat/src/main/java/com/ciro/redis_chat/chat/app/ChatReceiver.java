package com.ciro.redis_chat.chat.app;


import org.springframework.stereotype.Component;

@Component
public class ChatReceiver {

    public void handleMessage(String message){
        System.out.println("Nachricht empfangen: "+ message);
    }
}
