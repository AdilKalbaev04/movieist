package dev.adil.movieist.repository;

import dev.adil.movieist.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}
