package dev.farhan.movieist.controller;

import dev.farhan.movieist.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private final UserActivityService userActivityService;

    @Autowired
    public AuthController(UserService userService, UserActivityService userActivityService) {
        this.userService = userService;
        this.userActivityService = userActivityService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        String token = JwtUtil.generateToken(request.getUsername());
        userActivityService.trackActivity(request.getUsername(), "User logged in");
        return token;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        UserDetails userDetails = userService.register(request);

        if (userDetails == null) {
            return ResponseEntity.badRequest().body("User with this username already exists.");
        }

        return ResponseEntity.ok("Registration successful");
    }
}
