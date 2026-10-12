package com.yonatan.studentmanagement.controller;

import com.yonatan.studentmanagement.entity.Student;
import com.yonatan.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // =========================
    // STUDENT CRUD
    // =========================

    // Get all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Add a new student
    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(
            @Valid @RequestBody Student student) {

        Student savedStudent = studentService.addStudent(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedStudent);
    }

    // Get one student by ID
    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // Update a student
    @PutMapping("/students/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {

        return studentService.updateStudent(id, student);
    }

    // Delete a student
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    // =========================
    // STUDENT SEARCH
    // =========================

    // Search by first name
    @GetMapping("/students/search/first-name")
    public List<Student> searchByFirstName(
            @RequestParam String firstName) {

        return studentService.searchByFirstName(firstName);
    }

    // Search by last name
    @GetMapping("/students/search/last-name")
    public List<Student> searchByLastName(
            @RequestParam String lastName) {

        return studentService.searchByLastName(lastName);
    }

    // Search by student ID
    @GetMapping("/students/search/student-id")
    public List<Student> searchByStudentId(
            @RequestParam String studentId) {

        return studentService.searchByStudentId(studentId);
    }

    // Search by email
    @GetMapping("/students/search/email")
    public List<Student> searchByEmail(
            @RequestParam String email) {

        return studentService.searchByEmail(email);
    }

    // Search by program
    @GetMapping("/students/search/program")
    public List<Student> searchByProgram(
            @RequestParam String program) {

        return studentService.searchByProgram(program);
    }

    // Search by status
    @GetMapping("/students/search/status")
    public List<Student> searchByStatus(
            @RequestParam String status) {

        return studentService.searchByStatus(status);
    }
}