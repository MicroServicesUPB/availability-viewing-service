package com.upb.availabilityviewingservice.controller;


import com.upb.availabilityviewingservice.model.TableResponse;
import com.upb.availabilityviewingservice.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables22")
public class AvailabilityController {

    private final AvailabilityService availabilityService;
    @GetMapping("/available")
    public ResponseEntity<List<TableResponse>> getAvailableTables(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {

        List<TableResponse> availableTables = availabilityService.getAvailableTables(startTime, endTime);
        return ResponseEntity.ok(availableTables);
    }
    @PutMapping("/{tableId}/reserve")
    public ResponseEntity<String> reserveTable(@PathVariable Long tableId) {
        availabilityService.reserveTable(tableId);
        return ResponseEntity.ok("Mesa reservada con éxito");
    }

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeTableAvailability() {
        availabilityService.initializeAvailability();
        return ResponseEntity.ok("Disponibilidad de mesas inicializada");
    }

}
