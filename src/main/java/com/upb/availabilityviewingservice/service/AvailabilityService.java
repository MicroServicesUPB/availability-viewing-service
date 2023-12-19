package com.upb.availabilityviewingservice.service;

import com.upb.availabilityviewingservice.model.Table;

public interface AvailabilityService {
    long addTable(Table table);
    Table getTableById(long tableId);

}
