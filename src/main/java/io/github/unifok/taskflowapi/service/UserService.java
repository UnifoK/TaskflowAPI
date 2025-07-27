package io.github.unifok.taskflowapi.service;

import io.github.unifok.taskflowapi.model.User;
import io.github.unifok.taskflowapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        userRepository.save(user);
        return user;
    }
}
