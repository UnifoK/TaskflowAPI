package io.github.unifok.taskflowapi.service;

import io.github.unifok.taskflowapi.model.User;
import io.github.unifok.taskflowapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Use your UserRepository to find the user by their username.
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // 2. Convert your User entity into a Spring Security UserDetails object.
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>() // This is for authorities/roles, which we'll handle later.
        );
    }


    // Inside your UserService.java
    public User registerUser(User user) {
        // 1. Encode the plain-text password
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        // 2. Set the encoded password on the user object
        user.setPassword(encodedPassword);

        // 3. Save the user with the hashed password
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
