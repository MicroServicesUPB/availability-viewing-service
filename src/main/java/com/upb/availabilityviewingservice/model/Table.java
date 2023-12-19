package com.upb.availabilityviewingservice.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Table {
    private Long id;
    private Long capacity;
    private Long number;
}

