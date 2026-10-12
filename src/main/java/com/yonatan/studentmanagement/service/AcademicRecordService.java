package com.yonatan.studentmanagement.service;

import com.yonatan.studentmanagement.entity.AcademicRecord;
import com.yonatan.studentmanagement.entity.Course;
import com.yonatan.studentmanagement.entity.Student;
import com.yonatan.studentmanagement.exception.AcademicRecordNotFoundException;
import com.yonatan.studentmanagement.exception.CourseNotFoundException;
import com.yonatan.studentmanagement.exception.StudentNotFoundException;
import com.yonatan.studentmanagement.repository.AcademicRecordRepository;
import com.yonatan.studentmanagement.repository.CourseRepository;
import com.yonatan.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicRecordService {

    private final AcademicRecordRepository academicRecordRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public AcademicRecordService(
            AcademicRecordRepository academicRecordRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.academicRecordRepository = academicRecordRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Add a new academic record
    public AcademicRecord addAcademicRecord(
            AcademicRecord academicRecord) {

        // Check if the student exists
        if (academicRecord.getStudent() != null) {

            Long studentId =
                    academicRecord.getStudent().getId();

            Student student =
                    studentRepository.findById(studentId)
                            .orElseThrow(() ->
                                    new StudentNotFoundException(
                                            "Student not found with id: "
                                                    + studentId));

            academicRecord.setStudent(student);
        }

        // Check if the course exists
        if (academicRecord.getCourse() != null) {

            Long courseId =
                    academicRecord.getCourse().getId();

            Course course =
                    courseRepository.findById(courseId)
                            .orElseThrow(() ->
                                    new CourseNotFoundException(
                                            "Course not found with id: "
                                                    + courseId));

            academicRecord.setCourse(course);
        }

        return academicRecordRepository.save(academicRecord);
    }

    // Get all academic records
    public List<AcademicRecord> getAllAcademicRecords() {

        return academicRecordRepository.findAll();
    }

    // Get one academic record by ID
    public AcademicRecord getAcademicRecordById(Long id) {

        return academicRecordRepository.findById(id)
                .orElseThrow(() ->
                        new AcademicRecordNotFoundException(
                                "Academic record not found with id: "
                                        + id));
    }

    // Update an academic record
    public AcademicRecord updateAcademicRecord(
            Long id,
            AcademicRecord updatedRecord) {

        AcademicRecord existingRecord =
                academicRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new AcademicRecordNotFoundException(
                                        "Academic record not found with id: "
                                                + id));

        existingRecord.setAssignmentGrade(
                updatedRecord.getAssignmentGrade());

        existingRecord.setMidtermGrade(
                updatedRecord.getMidtermGrade());

        existingRecord.setFinalExamGrade(
                updatedRecord.getFinalExamGrade());

        existingRecord.setTotalGrade(
                updatedRecord.getTotalGrade());

        existingRecord.setLetterGrade(
                updatedRecord.getLetterGrade());

        existingRecord.setGradePoint(
                updatedRecord.getGradePoint());

        // Update Student
        if (updatedRecord.getStudent() != null) {

            Long studentId =
                    updatedRecord.getStudent().getId();

            Student student =
                    studentRepository.findById(studentId)
                            .orElseThrow(() ->
                                    new StudentNotFoundException(
                                            "Student not found with id: "
                                                    + studentId));

            existingRecord.setStudent(student);

        } else {

            existingRecord.setStudent(null);
        }

        // Update Course
        if (updatedRecord.getCourse() != null) {

            Long courseId =
                    updatedRecord.getCourse().getId();

            Course course =
                    courseRepository.findById(courseId)
                            .orElseThrow(() ->
                                    new CourseNotFoundException(
                                            "Course not found with id: "
                                                    + courseId));

            existingRecord.setCourse(course);

        } else {

            existingRecord.setCourse(null);
        }

        return academicRecordRepository.save(existingRecord);
    }

    // Delete an academic record
    public void deleteAcademicRecord(Long id) {

        AcademicRecord existingRecord =
                academicRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new AcademicRecordNotFoundException(
                                        "Academic record not found with id: "
                                                + id));

        academicRecordRepository.delete(existingRecord);
    }
}