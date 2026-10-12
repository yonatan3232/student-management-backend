package com.yonatan.studentmanagement.service;

import com.yonatan.studentmanagement.entity.Department;
import com.yonatan.studentmanagement.exception.DepartmentNotFoundException;
import com.yonatan.studentmanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Add a new department
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get one department by ID
    public Department getDepartmentById(Long id) {

        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id));
    }

    // Update a department
    public Department updateDepartment(
            Long id,
            Department updatedDepartment) {

        Department existingDepartment =
                departmentRepository.findById(id)
                        .orElseThrow(() ->
                                new DepartmentNotFoundException(
                                        "Department not found with id: " + id));

        existingDepartment.setName(updatedDepartment.getName());
        existingDepartment.setDescription(
                updatedDepartment.getDescription());

        return departmentRepository.save(existingDepartment);
    }

    // Delete a department
    public void deleteDepartment(Long id) {

        Department existingDepartment =
                departmentRepository.findById(id)
                        .orElseThrow(() ->
                                new DepartmentNotFoundException(
                                        "Department not found with id: " + id));

        departmentRepository.delete(existingDepartment);
    }
}