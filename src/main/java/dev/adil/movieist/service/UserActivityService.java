package dev.adil.movieist.service;


import java.time.LocalDateTime;
import java.util.List;


import dev.adil.movieist.entity.UserActivity;
import dev.adil.movieist.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;

    @Autowired
    public UserActivityService(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
    }

    public void trackActivity(String userId, String activity) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(userId);
        userActivity.setActivity(activity);
        userActivity.setTimestamp(LocalDateTime.now());

        userActivityRepository.save(userActivity);
    }

    public List<UserActivity> getUserActivities(String userId) {
        return userActivityRepository.findByUserId(userId);
    }
}
