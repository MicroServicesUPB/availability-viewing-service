package com.upb.availabilityviewingservice.service;

import com.upb.availabilityviewingservice.model.TableResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface AvailabilityService {
        List<TableResponse> getAvailableTables(LocalTime startTime, LocalTime endTime);
        void reserveTable(Long tableId);
        void initializeAvailability();

}


