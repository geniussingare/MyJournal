package com.geniusssoft.journalApp.controllers;

import com.geniusssoft.journalApp.entity.JournalEntity;
import com.geniusssoft.journalApp.entity.User;
import com.geniusssoft.journalApp.repositorys.UserRepository;
import com.geniusssoft.journalApp.services.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {
    @Autowired
    private JournalService journalService;
    @Autowired
    private UserRepository userRepository;



   // Get all journal entries for a specific user
    @GetMapping("/{username}")
    public ResponseEntity<List<JournalEntity>> getAllJournalEntries(
            @PathVariable String username)
    {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            List<JournalEntity> journalEntries = user.getJournalEntries();
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntity> createJournalEntry(
            @PathVariable String username,
            @RequestBody JournalEntity journalEntity)
    {
        User user = userRepository.findByUsername(username);
        if (user != null) {

            return new ResponseEntity<>(journalService.saveJournalEntry(journalEntity, username), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<String> deleteJournalEntry(
            @PathVariable String username,
            @PathVariable ObjectId id)
    {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            boolean deleted = journalService.deleteEntry(id, username);
            if (deleted) {
                return new ResponseEntity<>("Journal entry deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to delete journal entry", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

