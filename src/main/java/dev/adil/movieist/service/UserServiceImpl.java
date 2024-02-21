package dev.adil.movieist.service;

import dev.adil.movieist.model.RegisterRequest;
import dev.adil.movieist.entity.UserEntity;
import dev.adil.movieist.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            return null;
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = new UserEntity();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setRoles(new String[]{"USER"});

        userRepository.save(newUser);

        return User.builder()
                .username(newUser.getUsername())
                .password(newUser.getPassword())
                .roles(newUser.getRoles())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            return null;
        }

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(userEntity.getRoles())
                .build();
    }
}
