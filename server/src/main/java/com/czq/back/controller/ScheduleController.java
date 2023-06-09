package com.czq.back.controller;

import com.czq.back.dto.ListRet;
import com.czq.back.dto.PageDTO;
import com.czq.back.dto.QueryIdDTO;
import com.czq.back.entity.Schedule;
import com.czq.back.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("update")
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule scheduleDTO) {
        if (scheduleDTO.getId() == null) {
            Schedule savedSchedule = scheduleService.createSchedule(scheduleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
        }
        Schedule updatedSchedule = scheduleService.updateSchedule(scheduleDTO.getId(), scheduleDTO);
        return ResponseEntity.ok(updatedSchedule);

    }

    @PostMapping("/one")
    public ResponseEntity<Optional<Schedule>> getScheduleById(@RequestBody QueryIdDTO queryIdDTO) {
        Optional<Schedule> scheduleDTO = scheduleService.getScheduleById(queryIdDTO.getId());
        return ResponseEntity.ok(scheduleDTO);
    }


    @PostMapping("/del")
    public ResponseEntity<?> deleteSchedule(@RequestBody QueryIdDTO queryIdDTO) {
        scheduleService.deleteSchedule(queryIdDTO.getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("list")
    public ListRet getAllSchedules(@RequestBody PageDTO pageDTO) {
        pageDTO.setPage(pageDTO.getPage() - 1);
        final ListRet allSchedules = scheduleService.getAllSchedules(pageDTO);
        return allSchedules;
    }

    @PostMapping("getById")
    public ListRet getById(@RequestBody QueryIdDTO queryIdDTO) {
        final ListRet allSchedules = scheduleService.findById(queryIdDTO);
        return allSchedules;
    }

}
