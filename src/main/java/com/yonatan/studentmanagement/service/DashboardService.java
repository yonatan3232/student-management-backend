package com.yonatan.studentmanagement.service;

import com.yonatan.studentmanagement.dto.DashboardStats;
import com.yonatan.studentmanagement.repository.AcademicRecordRepository;
import com.yonatan.studentmanagement.repository.AttendanceRepository;
import com.yonatan.studentmanagement.repository.CourseRepository;
import com.yonatan.studentmanagement.repository.DepartmentRepository;
import com.yonatan.studentmanagement.repository.EnrollmentRepository;
import com.yonatan.studentmanagement.repository.StudentRepository;
import com.yonatan.studentmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AcademicRecordRepository academicRecordRepository;
    private final AttendanceRepository attendanceRepository;

    public DashboardService(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository,
            CourseRepository courseRepository,
            UserRepository userRepository,
            EnrollmentRepository enrollmentRepository,
            AcademicRecordRepository academicRecordRepository,
            AttendanceRepository attendanceRepository) {

        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.academicRecordRepository = academicRecordRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public DashboardStats getStats() {

        long totalStudents = studentRepository.count();
        long totalDepartments = departmentRepository.count();
        long totalCourses = courseRepository.count();
        long totalUsers = userRepository.count();
        long totalEnrollments = enrollmentRepository.count();
        long totalAcademicRecords = academicRecordRepository.count();
        long totalAttendance = attendanceRepository.count();

        return new DashboardStats(
                totalStudents,
                totalDepartments,
                totalCourses,
                totalUsers,
                totalEnrollments,
                totalAcademicRecords,
                totalAttendance
        );
    }
}