package com.geniusssoft.journalApp.controllers;

import com.geniusssoft.journalApp.entity.JournalEntity;
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


    // Getting all the journal entries....
    @GetMapping
    public ResponseEntity<List<JournalEntity>> getAll(){
        return new ResponseEntity<>(journalService.getAll(), HttpStatus.OK);
    }

    // Creating a new Journal Entry....
    @PostMapping
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity p_Entry) {
        p_Entry.setDate(LocalDateTime.now());

        return new ResponseEntity<>(journalService.saveJournalEntry(p_Entry), HttpStatus.CREATED);

    }

    // update the Journal entry
    @PutMapping("/{id}")
    public JournalEntity updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntity p_NewEntry) {
        JournalEntity oldEntry = journalService.getEntryById(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(p_NewEntry.getTitle() != null && ! p_NewEntry.getTitle().equals("") ? p_NewEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(p_NewEntry.getContent() != null && ! p_NewEntry.getContent().equals("") ? p_NewEntry.getContent() : oldEntry.getContent());
            return journalService.saveJournalEntry(oldEntry);
        } else {
            return null;
        }
    }

    // Get Single Journal Entry
    @GetMapping("/{id}")
    public JournalEntity getEntry(@PathVariable ObjectId id) {
        return journalService.getEntryById(id).orElse(null);
    }

    // Delete Journal Entry
    @DeleteMapping("/{id}")
    public boolean deleteEntry(@PathVariable ObjectId id) {
        journalService.deleteEntry(id);
        return true;
    }
}
