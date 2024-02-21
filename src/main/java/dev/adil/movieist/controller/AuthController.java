package dev.adil.movieist.controller;

import dev.adil.movieist.model.LoginRequest;
import dev.adil.movieist.model.RegisterRequest;
import dev.adil.movieist.service.UserActivityService;
import dev.adil.movieist.service.UserService;
import dev.adil.movieist.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private final UserActivityService userActivityService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, UserActivityService userActivityService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userActivityService = userActivityService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());

        if (userDetails == null || !passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        String token = JwtUtil.generateToken(request.getUsername());
        userActivityService.trackActivity(request.getUsername(), "User logged in");

        return ResponseEntity.ok(token);
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
