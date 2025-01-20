package com.example.journalApp.controllers;

import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.repository.UserEntryRepository;
import com.example.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private UserEntryRepository userEntryRepository;
    @GetMapping
    public ResponseEntity<?> getAllEntriesOfUsers(){
        List<UserEntry>all=userEntryService.getEntry();
        if(all!=null && !all.isEmpty())
            return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntry userEntry){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntry db_entry=userEntryService.findByUsername(username);
        db_entry.setUsername(userEntry.getUsername());
        db_entry.setPassword(userEntry.getPassword());
        userEntryService.saveNewEntry(db_entry);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody UserEntry userEntry){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        userEntryRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
