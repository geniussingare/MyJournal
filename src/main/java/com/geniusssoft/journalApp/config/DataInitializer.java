package com.geniusssoft.journalApp.config;

import com.geniusssoft.journalApp.entity.User;
import com.geniusssoft.journalApp.repositorys.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Create initial users if they don't exist
        createUserIfNotFound("admin", "admin123", true);
        createUserIfNotFound("user", "password", false);
        
        System.out.println("Data initialization completed.");
    }
    
    private void createUserIfNotFound(String username, String password, boolean isAdmin) {
        if (userRepository.findByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            
            Set<String> roles = new HashSet<>();
            roles.add("ROLE_USER");
            if (isAdmin) {
                roles.add("ROLE_ADMIN");
            }
            user.setRoles(roles);
            
            userRepository.save(user);
            
            System.out.println("User '" + username + "' created successfully!");
        }
    }
} 