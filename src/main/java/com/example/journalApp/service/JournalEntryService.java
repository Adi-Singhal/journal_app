package com.example.journalApp.service;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.repository.JournalEntryRepository;
import com.example.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryRepository userEntryRepository;
    @Autowired
    private UserEntryService userEntryService;
    @Transactional
    public void saveEntry(JournalEntry journalEntry,String username)
    {
        UserEntry userEntry=userEntryService.findByUsername(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved=journalEntryRepository.save(journalEntry);
        userEntry.getJournalEntryList().add(saved);
        userEntryService.saveEntry(userEntry);
    }
//    public void saveEntry(JournalEntry journalEntry)
//    {
//        journalEntryRepository.save(journalEntry);
//    }
    public List<JournalEntry> getEntry()
    {
        return journalEntryRepository.findAll();
    }
    public JournalEntry getEntrybyId(ObjectId id)
    {
        return journalEntryRepository.findById(id).orElse(null);
    }
    public void deleteEntrybyId(ObjectId id, String username)
    {
//        Optional<JournalEntry> journalEntry = journalEntryRepository.findById(id);
        UserEntry userEntry=userEntryService.findByUsername(username);
        userEntry.getJournalEntryList().removeIf(x->x.getId().equals(id));
        userEntryService.saveEntry(userEntry);
        journalEntryRepository.deleteById(id);
    }
}
