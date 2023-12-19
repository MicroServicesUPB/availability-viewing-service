package com.upb.availabilityviewingservice.controller;

import com.upb.availabilityviewingservice.model.Table;
import com.upb.availabilityviewingservice.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class AvailabilityController {
    @Autowired
    private AvailabilityService availabilityService;
    @PostMapping
    public ResponseEntity<Long> addTable(@RequestBody Table table) {
        long tableId = availabilityService.addTable(table);
        return new ResponseEntity<>(tableId, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity <Table> getTableById(@PathVariable long id){
        Table table = availabilityService.getTableById(id);
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

}
