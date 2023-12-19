package com.upb.availabilityviewingservice.model;


import lombok.Data;

@Data
public class TableResponse {
    private Long id;
    private int capacity;
    private int tableNumber;
    private boolean isAvailable;
}
