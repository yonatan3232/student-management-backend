package com.yonatan.studentmanagement.service;

import com.yonatan.studentmanagement.entity.Department;
import com.yonatan.studentmanagement.entity.Student;
import com.yonatan.studentmanagement.exception.DepartmentNotFoundException;
import com.yonatan.studentmanagement.exception.StudentNotFoundException;
import com.yonatan.studentmanagement.repository.DepartmentRepository;
import com.yonatan.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentService(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository) {

        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    // Add a new student
    public Student addStudent(Student student) {

        if (student.getDepartment() != null) {

            Long departmentId = student.getDepartment().getId();

            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() ->
                            new DepartmentNotFoundException(
                                    "Department not found with id: " + departmentId));

            student.setDepartment(department);
        }

        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get one student by ID
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id));
    }

    // Update a student
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id));

        existingStudent.setStudentId(updatedStudent.getStudentId());
        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setMiddleName(updatedStudent.getMiddleName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setGender(updatedStudent.getGender());
        existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPhone(updatedStudent.getPhone());
        existingStudent.setAddress(updatedStudent.getAddress());
        existingStudent.setProgram(updatedStudent.getProgram());
        existingStudent.setAcademicYear(updatedStudent.getAcademicYear());
        existingStudent.setYearLevel(updatedStudent.getYearLevel());
        existingStudent.setSemester(updatedStudent.getSemester());
        existingStudent.setEnrollmentDate(updatedStudent.getEnrollmentDate());
        existingStudent.setStatus(updatedStudent.getStatus());

        existingStudent.setEmergencyContactName(
                updatedStudent.getEmergencyContactName());

        existingStudent.setEmergencyContactRelationship(
                updatedStudent.getEmergencyContactRelationship());

        existingStudent.setEmergencyContactPhone(
                updatedStudent.getEmergencyContactPhone());

        // Update the student's department
        if (updatedStudent.getDepartment() != null) {

            Long departmentId = updatedStudent.getDepartment().getId();

            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() ->
                            new DepartmentNotFoundException(
                                    "Department not found with id: " + departmentId));

            existingStudent.setDepartment(department);

        } else {

            existingStudent.setDepartment(null);
        }

        return studentRepository.save(existingStudent);
    }

    // Delete a student
    public void deleteStudent(Long id) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id));

        studentRepository.delete(existingStudent);
    }

    // Search students by first name
    public List<Student> searchByFirstName(String firstName) {
        return studentRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    // Search students by last name
    public List<Student> searchByLastName(String lastName) {
        return studentRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    // Search students by student ID
    public List<Student> searchByStudentId(String studentId) {
        return studentRepository.findByStudentIdContainingIgnoreCase(studentId);
    }

    // Search students by email
    public List<Student> searchByEmail(String email) {
        return studentRepository.findByEmailContainingIgnoreCase(email);
    }

    // Search students by program
    public List<Student> searchByProgram(String program) {
        return studentRepository.findByProgramContainingIgnoreCase(program);
    }

    // Search students by status
    public List<Student> searchByStatus(String status) {
        return studentRepository.findByStatusIgnoreCase(status);
    }
}