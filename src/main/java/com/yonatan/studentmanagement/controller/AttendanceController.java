package com.yonatan.studentmanagement.controller;

import com.yonatan.studentmanagement.entity.Attendance;
import com.yonatan.studentmanagement.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // Get all attendance records
    @GetMapping("/attendance")
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    // Add a new attendance record
    @PostMapping("/attendance")
    public ResponseEntity<Attendance> addAttendance(
            @RequestBody Attendance attendance) {

        Attendance savedAttendance =
                attendanceService.addAttendance(attendance);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedAttendance);
    }

    // Get one attendance record
    @GetMapping("/attendance/{id}")
    public Attendance getAttendanceById(
            @PathVariable Long id) {

        return attendanceService.getAttendanceById(id);
    }

    // Update an attendance record
    @PutMapping("/attendance/{id}")
    public Attendance updateAttendance(
            @PathVariable Long id,
            @RequestBody Attendance attendance) {

        return attendanceService.updateAttendance(
                id,
                attendance
        );
    }

    // Delete an attendance record
    @DeleteMapping("/attendance/{id}")
    public ResponseEntity<Void> deleteAttendance(
            @PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.noContent().build();
    }
}