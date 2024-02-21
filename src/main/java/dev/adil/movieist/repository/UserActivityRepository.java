package dev.adil.movieist.repository;

import dev.adil.movieist.entity.UserActivity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserActivityRepository extends MongoRepository<UserActivity, String> {
    List<UserActivity> findByUserId(String userId);
}
