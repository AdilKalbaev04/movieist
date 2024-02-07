package dev.farhan.movieist.controller;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "user_activity")
public class UserActivity {

    @Id
    private String id;
    private String userId;
    private String activity;
    private LocalDateTime timestamp;
}
