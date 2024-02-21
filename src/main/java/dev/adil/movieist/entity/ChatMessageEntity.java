package dev.adil.movieist.entity;

import dev.adil.movieist.model.MessageType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document(collection = "messages")
public class ChatMessageEntity {

    @Id
    private String id;
    private MessageType type;
    private String content;
    private String sender;
    private Date timestamp;

}
