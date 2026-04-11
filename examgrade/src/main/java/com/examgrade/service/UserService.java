package com.examgrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examgrade.entity.User;
import com.examgrade.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // CREATE USER
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET USER BY ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}