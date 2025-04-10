package com.geniusssoft.journalApp.services;

import com.geniusssoft.journalApp.entity.JournalEntity;
import com.geniusssoft.journalApp.entity.User;
import com.geniusssoft.journalApp.repositorys.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    @Transactional
    // Here Service is used to Create new Journal Entry...
    public JournalEntity saveJournalEntry(JournalEntity journalEntity, String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            journalEntity.setDate(LocalDateTime.now());
            JournalEntity LNewjournalEntry = journalRepository.save(journalEntity);

            user.getJournalEntries().add(LNewjournalEntry);
            userService.saveUser(user);
            return LNewjournalEntry;
        }
        else return null;
    }

    // Here Service is used to get all entries from database....
    public List<JournalEntity> getAll() {
        return journalRepository.findAll();
    }

    // Here Service is used to get a single entry from database....
    public Optional<JournalEntity> getEntryById(ObjectId id) {
        return journalRepository.findById(id);
    }
    public boolean deleteEntry(ObjectId id, String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
            journalRepository.deleteById(id);
            userService.saveUser(user);
            return true;
        }
        else
        {
            return false;
        }


    }

}
