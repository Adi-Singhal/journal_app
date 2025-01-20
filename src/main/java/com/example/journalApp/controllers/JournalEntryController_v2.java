package com.example.journalApp.controllers;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.service.JournalEntryService;
import com.example.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController_v2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserEntryService userEntryService;

    @PostMapping()
    public ResponseEntity<?> put(@RequestBody JournalEntry myEntry){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        journalEntryService.saveEntry(myEntry,username);
        return new ResponseEntity<>(myEntry, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntry userEntry=userEntryService.findByUsername(username);
        List<JournalEntry>all=userEntry.getJournalEntryList();
        if(all!=null && !all.isEmpty())
            return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntrybyID(@PathVariable ObjectId myId){
        Optional<JournalEntry>journalEntry= Optional.ofNullable(journalEntryService.getEntrybyId(myId));
        if(journalEntry.isPresent())
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public JournalEntry putEntrybyID(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntry newEntry){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        JournalEntry old=journalEntryService.getEntrybyId(myId);
        if(old!=null)
        {
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
        }
        journalEntryService.saveEntry(old,username);
        return old;
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryByID(@PathVariable ObjectId myId){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        journalEntryService.deleteEntrybyId(myId,username);
        return true;
    }
}
