package com.upb.availabilityviewingservice.service;

import com.upb.availabilityviewingservice.entity.TableEntity;
import com.upb.availabilityviewingservice.model.Table;
import com.upb.availabilityviewingservice.repository.AvailabilityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AvailabilityServiceImpl implements AvailabilityService{
    @Autowired
    private AvailabilityRepository availabilityRepository;
    @Override
    public long addTable(Table tableReq) {
        List<Table> tableList = availabilityRepository.findAll().stream()
                .filter((tableEntity -> tableEntity.getNumber().equals(tableReq.getNumber())))
                .map(tableEntity -> {
                    Table table1 = new Table();
                    return table1;
                }).collect(Collectors.toList());

        TableEntity table = TableEntity.builder()
                .id(tableReq.getId())
                .capacity(tableReq.getCapacity())
                .number(tableReq.getNumber())
                .build();
        availabilityRepository.save(table);

        return table.getId();
    }

    @Override
    public Table getTableById(long tableId) {
        Optional<TableEntity> tableEntity = availabilityRepository.findById(tableId);
        Table tableResponse = new Table();
        BeanUtils.copyProperties(tableEntity,tableResponse);
        return tableResponse;
    }
}
