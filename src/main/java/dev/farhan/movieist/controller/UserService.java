package dev.farhan.movieist.controller;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails register(RegisterRequest request);
}
