package com.geniusssoft.journalApp.services;

import com.geniusssoft.journalApp.entity.User;
import com.geniusssoft.journalApp.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Creating a New user in the Users collection, This can also be used to update the user.
    public User saveUser(User user) {
        // If password is being updated, encode it
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
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

    public User registerNewUser(String username, String password) {
        // Check if user already exists
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }
        
        // Create new user with encoded password and default role
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        
        // Add default role
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        newUser.setRoles(roles);
        
        return userRepository.save(newUser);
    }
    
    public User registerNewUserWithRoles(String username, String password, Set<String> roles) {
        // Check if user already exists
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }
        
        // Create new user with encoded password and specified roles
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        
        // Add roles
        if (roles == null || roles.isEmpty()) {
            roles = new HashSet<>();
            roles.add("ROLE_USER");
        }
        newUser.setRoles(roles);
        
        return userRepository.save(newUser);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User addRoleToUser(String username, String role) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.getRoles().add(role);
            return userRepository.save(user);
        }
        return null;
    }
    
    public User removeRoleFromUser(String username, String role) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.getRoles().remove(role);
            return userRepository.save(user);
        }
        return null;
    }
}
