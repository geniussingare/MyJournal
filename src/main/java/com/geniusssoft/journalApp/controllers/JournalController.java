package com.geniusssoft.journalApp.controllers;

import com.geniusssoft.journalApp.entity.JournalEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/journal")
public class JournalController {

    // Getting all the journal entries....
    @GetMapping
    public List<JournalEntity> getAll(){
        return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntity p_Entry) {


        return true;
    }

    // update the Journal entry
    @PutMapping("/{id}")
    public boolean updateEntry(@PathVariable long id, @RequestBody JournalEntity p_Entry) {
        return true;
    }

    // Get Single Journal Entry
    @GetMapping("/{id}")
    public JournalEntity getEntry(@PathVariable long id) {
        return null;
    }

    // Delete Journal Entry
    @DeleteMapping("/{id}")
    public boolean deleteEntry(@PathVariable long id) {

        return false;
    }
}
