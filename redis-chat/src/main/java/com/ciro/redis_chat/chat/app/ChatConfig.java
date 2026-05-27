package com.ciro.redis_chat.chat.app;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;


@Configuration
public class ChatConfig {

    @Bean
    public MessageListenerAdapter messageListenerAdapter(ChatReceiver chatReceiver){
        return new MessageListenerAdapter(chatReceiver, "handleMessage");
    }

    /*Was ist ein MessageListenerAdapter?
Er ist ein Übersetzer zwischen Redis und deiner eigenen Klasse.
Redis schickt Nachrichten in einem eigenen Format – dein ChatReceiver versteht das aber nicht direkt.
Der Adapter übernimmt das Übersetzen:

Redis-Nachricht  →  MessageListenerAdapter  →  chatReceiver.handleMessage("text")
Der zweite Parameter "handleMessage" sagt dem Adapter:

"Wenn eine Nachricht kommt, ruf die Methode handleMessage() im ChatReceiver auf."

Spring injiziert ChatReceiver automatisch, weil er mit @Component markiert ist.*/




    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,
                                                   MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListenerAdapter, new PatternTopic("chat"));

        return container;
    }

    /*Der Container ist der Hauptverantwortliche – er läuft im Hintergrund und horcht dauerhaft auf Redis.*/

    // new RedisMessageListenerContainer() -> Erstellt den Listener-Container
    // setConnectionFactory(...) -> Verbindet ihn mit deinem Redis-Server
    // addMessageListener(..., new PatternTopic("chat")) -> Sagt: "Höre auf den Kanal namens chat
    // return container -> Gibt ihn an Spring zurück



}


/*ChatPublisher                Redis-Server              ChatReceiver
     │                            │                         │
     │── publish("chat", msg) ───▶│                         │
     │                            │── Nachricht kommt ─────▶│
     │                            │   Container erkennt     │
     │                            │   Kanal "chat"          │
     │                            │   Adapter ruft auf ────▶│ handleMessage(msg)*/