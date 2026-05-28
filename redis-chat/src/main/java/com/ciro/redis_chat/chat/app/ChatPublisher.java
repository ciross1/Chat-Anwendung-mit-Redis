package com.ciro.redis_chat.chat.app;

// Der ChatPublisher ist der Absender – er schickt Nachrichten in den Redis-Kanal.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatPublisher {





    private final StringRedisTemplate redisTemplate;


    public ChatPublisher(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

      /* Mit Konstruktor – final möglich!
private final StringRedisTemplate redisTemplate;
// ↑ final = kann nie null sein = sicherer!

// Mit @Autowired – kein final möglich
@Autowired
private StringRedisTemplate redisTemplate;
// ↑ könnte theoretisch null sein*/

    public void publish(String message){
        //Sendet nachricht an Redis Kanal
        redisTemplate.convertAndSend("chat", message);

        System.out.println("Nachricht gesendet: " + message);
    }



}




/*1. ChatController ruft auf:
   chatPublisher.publish("Anna: Hallo!")
          │
          ▼
2. ChatPublisher sendet an Redis:
   redisTemplate.convertAndSend("chat", "Anna: Hallo!")
          │
          ▼
3. Redis leitet weiter an Kanal "chat"
          │
          ▼
4. ChatReceiver empfängt:
   handleMessage("Anna: Hallo!")
          │
          ▼
5. Konsole zeigt:
   "Nachricht empfangen: Anna: Hallo!"*/