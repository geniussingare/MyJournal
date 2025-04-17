package com.geniusssoft.journalApp.controllers;

import com.geniusssoft.journalApp.entity.User;
import com.geniusssoft.journalApp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users/{username}/roles")
    public ResponseEntity<?> addRoleToUser(@PathVariable String username, @RequestBody Map<String, String> request) {
        String role = request.get("role");
        
        if (role == null || role.isEmpty()) {
            return new ResponseEntity<>("Role is required", HttpStatus.BAD_REQUEST);
        }
        
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        
        User user = userService.addRoleToUser(username, role);
        
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("roles", user.getRoles());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/users/{username}/roles/{role}")
    public ResponseEntity<?> removeRoleFromUser(@PathVariable String username, @PathVariable String role) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        
        User user = userService.removeRoleFromUser(username, role);
        
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("roles", user.getRoles());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/users/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        boolean deleted = userService.deleteUserByUsername(username);
        
        if (!deleted) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
} 