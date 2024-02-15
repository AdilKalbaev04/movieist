package dev.adil.movieist.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private String id;
    private String userId;
    private String body;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Review(String userId, String body, LocalDateTime created, LocalDateTime updated) {
        this.userId = userId;
        this.body = body;
        this.created = created;
        this.updated = updated;
    }
}
