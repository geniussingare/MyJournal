package com.geniusssoft.journalApp.services;

import com.geniusssoft.journalApp.entity.User;
import com.geniusssoft.journalApp.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Creating a New user in the Users collection, This can also be used to update the user.
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    // Getting the user by username.
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Get all Users from Users collection.
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    // Delete user entry by username.
    public boolean deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }
}
