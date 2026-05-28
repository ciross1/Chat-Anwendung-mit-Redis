package com.ciro.redis_chat.chat.app;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }


    @PostMapping("/send")
    public String sendMessage(@RequestBody ChatMessage chatMessage){

        chatService.sendMessage(chatMessage.getUser(), chatMessage.getMessage());

        return "Nachricht gesendet von: " + chatMessage.getUser();

    }





    @GetMapping("/messages")
    public List<String> getMessages(){

        return chatService.getAllMessages();
    }

    @DeleteMapping("/delete")
    public String deleteMessage(){
        return chatService.clearChat();
    }
}
