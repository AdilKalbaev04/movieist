package dev.farhan.movieist.repository;

import dev.farhan.movieist.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}
