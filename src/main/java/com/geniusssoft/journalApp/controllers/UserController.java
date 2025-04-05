package com.geniusssoft.journalApp.controllers;

import com.geniusssoft.journalApp.entity.User;
import com.geniusssoft.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // Get All user Request controller...
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return  new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // Get Single user Using username...
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update user using username...{username in param variable}
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user) {
        User existingUser = userService.getUserByUsername(username);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername() != null && !user.getUsername().equals("") ? user.getUsername() : existingUser.getUsername());
            existingUser.setPassword(user.getPassword() != null && !user.getPassword().equals("") ? user.getPassword() : existingUser.getPassword());
            return new ResponseEntity<>(userService.saveUser(existingUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Delete user using username...{username in param variable}
    @DeleteMapping("/{username}")
    public boolean deleteUser(@PathVariable String username) {
        return userService.deleteUserByUsername(username);
    }
    // Create new user using username and password...
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        if (user.getUsername() != null && !user.getUsername().equals("") && user.getPassword() != null && !user.getPassword().equals("")) {
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
