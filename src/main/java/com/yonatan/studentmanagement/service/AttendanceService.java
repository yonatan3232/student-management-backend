package com.yonatan.studentmanagement.service;

import com.yonatan.studentmanagement.entity.Attendance;
import com.yonatan.studentmanagement.entity.Course;
import com.yonatan.studentmanagement.entity.Student;
import com.yonatan.studentmanagement.exception.AttendanceNotFoundException;
import com.yonatan.studentmanagement.exception.CourseNotFoundException;
import com.yonatan.studentmanagement.exception.StudentNotFoundException;
import com.yonatan.studentmanagement.repository.AttendanceRepository;
import com.yonatan.studentmanagement.repository.CourseRepository;
import com.yonatan.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Add a new attendance record
    public Attendance addAttendance(Attendance attendance) {

        // Check if the student exists
        if (attendance.getStudent() != null) {

            Long studentId = attendance.getStudent().getId();

            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() ->
                            new StudentNotFoundException(
                                    "Student not found with id: " + studentId));

            attendance.setStudent(student);
        }

        // Check if the course exists
        if (attendance.getCourse() != null) {

            Long courseId = attendance.getCourse().getId();

            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() ->
                            new CourseNotFoundException(
                                    "Course not found with id: " + courseId));

            attendance.setCourse(course);
        }

        return attendanceRepository.save(attendance);
    }

    // Get all attendance records
    public List<Attendance> getAllAttendance() {

        return attendanceRepository.findAll();
    }

    // Get one attendance record by ID
    public Attendance getAttendanceById(Long id) {

        return attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new AttendanceNotFoundException(
                                "Attendance not found with id: " + id));
    }

    // Update an attendance record
    public Attendance updateAttendance(
            Long id,
            Attendance updatedAttendance) {

        Attendance existingAttendance =
                attendanceRepository.findById(id)
                        .orElseThrow(() ->
                                new AttendanceNotFoundException(
                                        "Attendance not found with id: " + id));

        existingAttendance.setDate(
                updatedAttendance.getDate());

        existingAttendance.setStatus(
                updatedAttendance.getStatus());

        // Update Student
        if (updatedAttendance.getStudent() != null) {

            Long studentId =
                    updatedAttendance.getStudent().getId();

            Student student =
                    studentRepository.findById(studentId)
                            .orElseThrow(() ->
                                    new StudentNotFoundException(
                                            "Student not found with id: "
                                                    + studentId));

            existingAttendance.setStudent(student);

        } else {

            existingAttendance.setStudent(null);
        }

        // Update Course
        if (updatedAttendance.getCourse() != null) {

            Long courseId =
                    updatedAttendance.getCourse().getId();

            Course course =
                    courseRepository.findById(courseId)
                            .orElseThrow(() ->
                                    new CourseNotFoundException(
                                            "Course not found with id: "
                                                    + courseId));

            existingAttendance.setCourse(course);

        } else {

            existingAttendance.setCourse(null);
        }

        return attendanceRepository.save(existingAttendance);
    }

    // Delete an attendance record
    public void deleteAttendance(Long id) {

        Attendance existingAttendance =
                attendanceRepository.findById(id)
                        .orElseThrow(() ->
                                new AttendanceNotFoundException(
                                        "Attendance not found with id: "
                                                + id));

        attendanceRepository.delete(existingAttendance);
    }
}