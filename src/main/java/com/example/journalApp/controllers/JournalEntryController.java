//package com.example.journalApp.controllers;
//
//import com.example.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//
//    private Map<String,JournalEntry> journalEntries=new HashMap<>();
//    @PostMapping
//    public boolean put(@RequestBody JournalEntry myEntry){
//        journalEntries.put(myEntry.getId(),myEntry);
//        return true;
//
//    }
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//    @GetMapping("id/{myId}")
//    public JournalEntry getEntrybyID(@PathVariable int myId){
//        return journalEntries.get(myId);
//    }
//    @PutMapping("id/{myId}")
//    public JournalEntry putEntrybyID(@PathVariable String myId,@RequestBody JournalEntry myEntry){
//        return journalEntries.put(myId,myEntry);
//    }
//    @DeleteMapping("id/{myId}")
//    public JournalEntry deleteEntrybyID(@PathVariable int myId){
//        return journalEntries.remove(myId);
//    }
////    @PutMapping
////    public List<JournalEntry> getAll(){
////        return new ArrayList<>(journalEntries.values());
////    }
//}
