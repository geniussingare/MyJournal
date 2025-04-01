package com.geniusssoft.journalApp.services;

import com.geniusssoft.journalApp.entity.JournalEntity;
import com.geniusssoft.journalApp.repositorys.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;

    // Here Service is used to Create new Journal Entry...
    public JournalEntity saveJournalEntry(JournalEntity journalEntity) {
        journalRepository.save(journalEntity);
        return journalEntity;
    }

    // Here Service is used to get all entries from database....
    public List<JournalEntity> getAll() {
        return journalRepository.findAll();
    }

    // Here Service is used to get a single entry from database....
    public Optional<JournalEntity> getEntryById(ObjectId id) {
        return journalRepository.findById(id);
    }
    public void deleteEntry(ObjectId id) {
        journalRepository.deleteById(id);

    }

}
