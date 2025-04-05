package com.geniusssoft.journalApp.controllers;

import com.geniusssoft.journalApp.entity.JournalEntity;
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

    // Get List of all Journal Entries for user...
    // Create a Journal for user...
    // Get a Journal Entry by journal entry id...
    // Update a Journal Entry via user...
    // Delete a Journal Entry via user...
}
