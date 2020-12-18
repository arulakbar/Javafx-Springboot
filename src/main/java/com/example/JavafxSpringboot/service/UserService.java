package com.example.JavafxSpringboot.service;

import com.example.JavafxSpringboot.model.User;
import com.example.JavafxSpringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authentication(String user, String password){
        if (userRepository.existsByName(user)){
            return userRepository.findByName(user).getPassword().equals(password);
        }
        return false;
    }

    public User getUser(String user){
        return userRepository.findByName(user);
    }


}
