package com.upb.availabilityviewingservice.service;

import com.upb.availabilityviewingservice.entity.Schedule;
import com.upb.availabilityviewingservice.entity.Table;
import com.upb.availabilityviewingservice.model.TableResponse;
import com.upb.availabilityviewingservice.repository.ScheduleRepository;
import com.upb.availabilityviewingservice.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService{

    private final TableRepository tableRepository;
    private final ScheduleRepository scheduleRepository;
    @Override
    public List<TableResponse> getAvailableTables(LocalTime startTime, LocalTime endTime) {

        List<Schedule> schedules = scheduleRepository.findByStartTimeBetween(startTime, endTime);

        return tableRepository.findAll().stream()
                .filter(table -> table.getSchedules().stream()
                        .anyMatch(schedule -> schedules.contains(schedule) && schedule.isAvailable()))
                .map(this::toTableResponse)
                .collect(Collectors.toList());
    }
    @Override
    public void reserveTable(Long tableId) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found"));

        table.getSchedules().forEach(schedule -> schedule.setAvailable(false));
        tableRepository.save(table);
    }

    @Override
    public void initializeAvailability() {
        LocalTime[] times = {LocalTime.of(9, 0), LocalTime.of(10, 0), LocalTime.of(13, 0), LocalTime.of(14, 0)};

        // Crear y guardar los horarios en la base de datos
        for (LocalTime time : times) {
            Schedule schedule = new Schedule();
            schedule.setStartTime(time);
            schedule.setEndTime(time.plusHours(1));
            schedule.setAvailable(true);
            scheduleRepository.save(schedule);
        }

        // Asociar cada horario a todas las mesas
        List<Table> tables = tableRepository.findAll();
        List<Schedule> schedules = scheduleRepository.findAll();
        for (Table table : tables) {
            table.setSchedules(new HashSet<>(schedules));
            tableRepository.save(table);
        }
    }


    private TableResponse toTableResponse(Table table) {
        TableResponse response = new TableResponse();
        response.setId(table.getId());
        response.setCapacity(table.getCapacity());
        response.setTableNumber(table.getTableNumber());

        // Aquí asumimos que 'isAvailable' depende de la disponibilidad actual de la mesa
        boolean isAvailable = table.getSchedules().stream().anyMatch(Schedule::isAvailable);
        response.setAvailable(isAvailable);

        return response;
    }
}
