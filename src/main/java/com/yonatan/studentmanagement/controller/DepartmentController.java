package com.yonatan.studentmanagement.controller;

import com.yonatan.studentmanagement.entity.Department;
import com.yonatan.studentmanagement.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Get all departments
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // Add a new department
    @PostMapping("/departments")
    public ResponseEntity<Department> addDepartment(
            @Valid @RequestBody Department department) {

        Department savedDepartment =
                departmentService.addDepartment(department);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDepartment);
    }

    // Get one department by ID
    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    // Update a department
    @PutMapping("/departments/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody Department department) {

        return departmentService.updateDepartment(id, department);
    }

    // Delete a department
    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }
}