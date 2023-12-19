package com.upb.availabilityviewingservice.repository;

import com.upb.availabilityviewingservice.entity.TableEntity;
import com.upb.availabilityviewingservice.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<TableEntity,Long> {

}
