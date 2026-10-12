package com.yonatan.studentmanagement.service;

import com.yonatan.studentmanagement.entity.Course;
import com.yonatan.studentmanagement.entity.Enrollment;
import com.yonatan.studentmanagement.entity.Student;
import com.yonatan.studentmanagement.exception.CourseNotFoundException;
import com.yonatan.studentmanagement.exception.EnrollmentNotFoundException;
import com.yonatan.studentmanagement.exception.StudentNotFoundException;
import com.yonatan.studentmanagement.repository.CourseRepository;
import com.yonatan.studentmanagement.repository.EnrollmentRepository;
import com.yonatan.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Add a new enrollment
    public Enrollment addEnrollment(Enrollment enrollment) {

        // Check if the student exists
        if (enrollment.getStudent() != null) {

            Long studentId = enrollment.getStudent().getId();

            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() ->
                            new StudentNotFoundException(
                                    "Student not found with id: " + studentId));

            enrollment.setStudent(student);
        }

        // Check if the course exists
        if (enrollment.getCourse() != null) {

            Long courseId = enrollment.getCourse().getId();

            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() ->
                            new CourseNotFoundException(
                                    "Course not found with id: " + courseId));

            enrollment.setCourse(course);
        }

        return enrollmentRepository.save(enrollment);
    }

    // Get all enrollments
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // Get one enrollment by ID
    public Enrollment getEnrollmentById(Long id) {

        return enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new EnrollmentNotFoundException(
                                "Enrollment not found with id: " + id));
    }

    // Update an enrollment
    public Enrollment updateEnrollment(
            Long id,
            Enrollment updatedEnrollment) {

        Enrollment existingEnrollment =
                enrollmentRepository.findById(id)
                        .orElseThrow(() ->
                                new EnrollmentNotFoundException(
                                        "Enrollment not found with id: " + id));

        existingEnrollment.setAcademicYear(
                updatedEnrollment.getAcademicYear());

        existingEnrollment.setSemester(
                updatedEnrollment.getSemester());

        existingEnrollment.setEnrollmentDate(
                updatedEnrollment.getEnrollmentDate());

        existingEnrollment.setStatus(
                updatedEnrollment.getStatus());

        // Update Student
        if (updatedEnrollment.getStudent() != null) {

            Long studentId =
                    updatedEnrollment.getStudent().getId();

            Student student =
                    studentRepository.findById(studentId)
                            .orElseThrow(() ->
                                    new StudentNotFoundException(
                                            "Student not found with id: "
                                                    + studentId));

            existingEnrollment.setStudent(student);

        } else {

            existingEnrollment.setStudent(null);
        }

        // Update Course
        if (updatedEnrollment.getCourse() != null) {

            Long courseId =
                    updatedEnrollment.getCourse().getId();

            Course course =
                    courseRepository.findById(courseId)
                            .orElseThrow(() ->
                                    new CourseNotFoundException(
                                            "Course not found with id: "
                                                    + courseId));

            existingEnrollment.setCourse(course);

        } else {

            existingEnrollment.setCourse(null);
        }

        return enrollmentRepository.save(existingEnrollment);
    }

    // Delete an enrollment
    public void deleteEnrollment(Long id) {

        Enrollment existingEnrollment =
                enrollmentRepository.findById(id)
                        .orElseThrow(() ->
                                new EnrollmentNotFoundException(
                                        "Enrollment not found with id: " + id));

        enrollmentRepository.delete(existingEnrollment);
    }
}