package dev.adil.movieist.service;

import dev.adil.movieist.model.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByUsername(String username);
    UserDetails register(RegisterRequest request);
}
