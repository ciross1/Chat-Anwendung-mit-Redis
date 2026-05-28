package com.ciro.redis_chat.chat.app;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepository {

    private final StringRedisTemplate redisTemplate;
    private static final String KEY = "chat-history"; // -> Redis Schlüssel

    public ChatRepository(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    //Nachricht speichern
    public void save(String message){
        redisTemplate.opsForList().rightPush(KEY,message);
    }

    //Alle nachrichten abrufen um daten zum lesen
    public List<String> findAll(){
        return redisTemplate.opsForList().range(KEY, 0, 1);
    }

    //Alle Nachrichten Löschen
    public void delete(){
        redisTemplate.delete(KEY);
    }


}


// Was ist der KEY?

// Der KEY ist wie ein Etikette auf einer Schachtel in Redis:

/*Redis Datenbank
│
├── "chat-history"  ← KEY (Etikette)
│      │
│      ├── [0] "Anna: Hallo!"
│      ├── [1] "Max: Hey!"
│      └── [2] "Anna: Wie geht's?"
│
├── "andere-liste"  ← anderer KEY
│      └── ...*/

// Ohne KEY geht es nicht:
//Redis ist wie ein Wörterbuch:

/*KEY             →    VALUE
"chat-history"  →    ["Anna: Hallo!", "Max: Hey!"]
"user-sessions" →    ["user1", "user2"]
"online-users"  →    ["Anna", "Max"]*/
