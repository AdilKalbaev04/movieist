package dev.adil.movieist.controller;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByUsername(String username);
    UserDetails register(RegisterRequest request);
}
