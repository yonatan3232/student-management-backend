package com.yonatan.studentmanagement.controller;

import com.yonatan.studentmanagement.entity.Enrollment;
import com.yonatan.studentmanagement.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // Get all enrollments
    @GetMapping("/enrollments")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // Add a new enrollment
    @PostMapping("/enrollments")
    public ResponseEntity<Enrollment> addEnrollment(
            @RequestBody Enrollment enrollment) {

        Enrollment savedEnrollment =
                enrollmentService.addEnrollment(enrollment);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedEnrollment);
    }

    // Get one enrollment by ID
    @GetMapping("/enrollments/{id}")
    public Enrollment getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    // Update an enrollment
    @PutMapping("/enrollments/{id}")
    public Enrollment updateEnrollment(
            @PathVariable Long id,
            @RequestBody Enrollment enrollment) {

        return enrollmentService.updateEnrollment(id, enrollment);
    }

    // Delete an enrollment
    @DeleteMapping("/enrollments/{id}")
    public ResponseEntity<Void> deleteEnrollment(
            @PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return ResponseEntity.noContent().build();
    }
}