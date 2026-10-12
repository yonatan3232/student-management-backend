package com.yonatan.studentmanagement.controller;

import com.yonatan.studentmanagement.entity.AcademicRecord;
import com.yonatan.studentmanagement.service.AcademicRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AcademicRecordController {

    private final AcademicRecordService academicRecordService;

    public AcademicRecordController(AcademicRecordService academicRecordService) {
        this.academicRecordService = academicRecordService;
    }

    // Get all academic records
    @GetMapping("/academic-records")
    public List<AcademicRecord> getAllAcademicRecords() {
        return academicRecordService.getAllAcademicRecords();
    }

    // Add a new academic record
    @PostMapping("/academic-records")
    public ResponseEntity<AcademicRecord> addAcademicRecord(
            @RequestBody AcademicRecord academicRecord) {

        AcademicRecord savedRecord =
                academicRecordService.addAcademicRecord(academicRecord);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedRecord);
    }

    // Get one academic record
    @GetMapping("/academic-records/{id}")
    public AcademicRecord getAcademicRecordById(
            @PathVariable Long id) {

        return academicRecordService.getAcademicRecordById(id);
    }

    // Update an academic record
    @PutMapping("/academic-records/{id}")
    public AcademicRecord updateAcademicRecord(
            @PathVariable Long id,
            @RequestBody AcademicRecord academicRecord) {

        return academicRecordService.updateAcademicRecord(
                id,
                academicRecord
        );
    }

    // Delete an academic record
    @DeleteMapping("/academic-records/{id}")
    public ResponseEntity<Void> deleteAcademicRecord(
            @PathVariable Long id) {

        academicRecordService.deleteAcademicRecord(id);

        return ResponseEntity.noContent().build();
    }
}