package com.upb.availabilityviewingservice.repository;


import com.upb.availabilityviewingservice.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {
}