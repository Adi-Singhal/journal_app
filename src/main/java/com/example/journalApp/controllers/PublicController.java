package com.example.journalApp.controllers;

import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @GetMapping("/health-check")
    public String healthCheck()
    {
        return "ok";
    }
    @Autowired
    private UserEntryService userEntryService;
    @PostMapping
    public ResponseEntity<?> put(@RequestBody UserEntry myEntry){
        userEntryService.saveNewEntry(myEntry);
        return new ResponseEntity<>(myEntry, HttpStatus.OK);
    }
}
