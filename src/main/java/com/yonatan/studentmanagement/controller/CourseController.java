package com.yonatan.studentmanagement.controller;

import com.yonatan.studentmanagement.entity.Course;
import com.yonatan.studentmanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Get all courses
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Add a new course
    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(
            @Valid @RequestBody Course course) {

        Course savedCourse = courseService.addCourse(course);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCourse);
    }

    // Get one course by ID
    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // Update a course
    @PutMapping("/courses/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody Course course) {

        return courseService.updateCourse(id, course);
    }

    // Delete a course
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
}