package dev.adil.movieist.repository;

import dev.adil.movieist.entity.ChatMessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessageEntity, String> {
}