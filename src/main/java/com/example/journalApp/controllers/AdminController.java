package com.example.journalApp.controllers;

import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserEntryService userEntryService;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllEntry(){
        List<UserEntry>all=userEntryService.getALL();
        if(all!=null && !all.isEmpty())
            return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin")
    public ResponseEntity<?> getAllEntry(@RequestBody UserEntry userEntry){
        userEntryService.createAdmin(userEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
