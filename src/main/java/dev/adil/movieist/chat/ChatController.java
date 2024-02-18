package dev.adil.movieist.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageRepository messageRepository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageEntity sendMessage(@Payload ChatMessage chatMessage) {
        return processMessage(chatMessage);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessageEntity addUser(@Payload ChatMessage chatMessage) {
        return processMessage(chatMessage);
    }
//
    private ChatMessageEntity processMessage(ChatMessage chatMessage) {
        if (chatMessage.getType() != null && chatMessage.getContent() != null && chatMessage.getSender() != null) {
            ChatMessageEntity entity = new ChatMessageEntity();
            entity.setType(chatMessage.getType());
            entity.setContent(chatMessage.getContent());
            entity.setSender(chatMessage.getSender());
            entity.setTimestamp(new Date());
            messageRepository.save(entity);
            return entity;
        } else {
            return null;
        }
    }
}
