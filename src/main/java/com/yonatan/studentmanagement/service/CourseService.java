package com.yonatan.studentmanagement.service;

import com.yonatan.studentmanagement.entity.Course;
import com.yonatan.studentmanagement.entity.Department;
import com.yonatan.studentmanagement.exception.CourseNotFoundException;
import com.yonatan.studentmanagement.exception.DepartmentNotFoundException;
import com.yonatan.studentmanagement.repository.CourseRepository;
import com.yonatan.studentmanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public CourseService(
            CourseRepository courseRepository,
            DepartmentRepository departmentRepository) {

        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    // Add a new course
    public Course addCourse(Course course) {

        if (course.getDepartment() != null) {

            Long departmentId = course.getDepartment().getId();

            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() ->
                            new DepartmentNotFoundException(
                                    "Department not found with id: " + departmentId));

            course.setDepartment(department);
        }

        return courseRepository.save(course);
    }

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get one course by ID
    public Course getCourseById(Long id) {

        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                "Course not found with id: " + id));
    }

    // Update a course
    public Course updateCourse(
            Long id,
            Course updatedCourse) {

        Course existingCourse =
                courseRepository.findById(id)
                        .orElseThrow(() ->
                                new CourseNotFoundException(
                                        "Course not found with id: " + id));

        existingCourse.setCourseCode(
                updatedCourse.getCourseCode());

        existingCourse.setCourseName(
                updatedCourse.getCourseName());

        existingCourse.setCreditHours(
                updatedCourse.getCreditHours());

        existingCourse.setSemester(
                updatedCourse.getSemester());

        existingCourse.setAcademicYear(
                updatedCourse.getAcademicYear());

        // Update the course's department
        if (updatedCourse.getDepartment() != null) {

            Long departmentId =
                    updatedCourse.getDepartment().getId();

            Department department =
                    departmentRepository.findById(departmentId)
                            .orElseThrow(() ->
                                    new DepartmentNotFoundException(
                                            "Department not found with id: "
                                                    + departmentId));

            existingCourse.setDepartment(department);

        } else {

            existingCourse.setDepartment(null);
        }

        return courseRepository.save(existingCourse);
    }

    // Delete a course
    public void deleteCourse(Long id) {

        Course existingCourse =
                courseRepository.findById(id)
                        .orElseThrow(() ->
                                new CourseNotFoundException(
                                        "Course not found with id: " + id));

        courseRepository.delete(existingCourse);
    }
}