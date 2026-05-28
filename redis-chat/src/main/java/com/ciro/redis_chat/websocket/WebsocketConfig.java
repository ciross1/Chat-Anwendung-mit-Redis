package com.ciro.redis_chat.websocket;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    private final ChatWebsocketHandler chatWebsocketHandler;

    public WebsocketConfig(ChatWebsocketHandler chatWebsocketHandler){
        this.chatWebsocketHandler = chatWebsocketHandler;
    }






    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebsocketHandler, "/ws/chat")
                .setAllowedOrigins("*"); // ← Alle Browser erlauben
    }
}


/*Browser                    Spring Boot
   │                           │
   │── WebSocket Verbindung ──▶│
   │   ws://localhost:8080/ws/chat
   │                           │
   │                    ChatWebSocketHandler
   │                    verwaltet Verbindung*/
