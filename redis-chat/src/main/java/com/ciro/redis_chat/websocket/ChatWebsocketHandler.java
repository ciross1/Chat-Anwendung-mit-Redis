package com.ciro.redis_chat.websocket;


import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatWebsocketHandler extends TextWebSocketHandler  {

    //Lister alle verbundenen Benutzer:
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    // WebSocketSession = eine offene Verbindung zu einem Browser
    //CopyOnWriteArrayList = thread-sichere Liste (mehrere Benutzer gleichzeitig!)

    // sessions = [
    //    Session(Anna),    ← Browser Tab 1
    //    Session(Max),     ← Browser Tab 2
    //    Session(...)      ← weitere Benutzer
    //]



    //Benutzer verbinden sich:
    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session){
        sessions.add(session);

        System.out.println("Neuer benutzer verbunden: " + session.getId());
    }

    /*Anna öffnet Browser
        │
        ▼
afterConnectionEstablished() wird aufgerufen
        │
        ▼
Anna's Session wird zur Liste hinzugefügt
sessions = [Session(Anna)]   */






    //Nachricht empfangen und an alle senden:
    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception{

        for(WebSocketSession s : sessions){
            //  ↑ jede Session      ↑ aus der Liste

            if(s.isOpen()){
                //  ↑ ist Verbindung noch aktiv?

                s.sendMessage(message);
                //  ↑ sende Nachricht an diesen Benutzer
            }
        }

    }


    // Anna schreibt "Hallo!"
    //        │
    //        ▼
    //handleTextMessage() wird aufgerufen
    //        │
    //        ▼
    //Loop durch alle sessions:
    //    → Anna bekommt "Hallo!"
    //    → Max bekommt "Hallo!"
    //    → alle anderen auch!


    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus closeStatus){
        sessions.remove(session);
        System.out.println("Benutzer getrennt: " + session.getId());
    }

    // Anna schließt Browser
    //        │
    //        ▼
    //afterConnectionClosed() wird aufgerufen
    //        │
    //        ▼
    //Anna's Session wird aus Liste entfernt
    //sessions = [Session(Max)]  ← nur noch Max
}
