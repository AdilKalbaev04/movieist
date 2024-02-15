package dev.adil.movieist.controller;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails register(RegisterRequest request);
}
