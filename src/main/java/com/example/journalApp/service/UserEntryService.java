package com.example.journalApp.service;

import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserEntryService {
    @Autowired
    private UserEntryRepository userEntryRepository;
    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    public void saveNewEntry(UserEntry userEntry)
    {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(userEntry);
    }
    public void saveEntry(UserEntry userEntry)
    {
        userEntryRepository.save(userEntry);
    }

    public List<UserEntry> getEntry()
    {
        return userEntryRepository.findAll();
    }
    public UserEntry getEntrybyId(ObjectId id)
    {
        return userEntryRepository.findById(id).orElse(null);
    }
    public void deleteEntrybyId(ObjectId id)
    {
//        Optional<JournalEntry> journalEntry = journalEntryRepository.findById(id);
        userEntryRepository.deleteById(id);
    }
    public UserEntry findByUsername(String Username)
    {
        return userEntryRepository.findByUsername(Username);
    }

    public List<UserEntry> getALL() {
        return userEntryRepository.findAll();
    }

    public void createAdmin(UserEntry userEntry) {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USER","ADMIN"));
        userEntryRepository.save(userEntry);
    }
}
