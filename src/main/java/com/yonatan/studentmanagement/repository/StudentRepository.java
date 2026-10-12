package com.yonatan.studentmanagement.repository;

import com.yonatan.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstNameContainingIgnoreCase(String firstName);

    List<Student> findByLastNameContainingIgnoreCase(String lastName);

    List<Student> findByStudentIdContainingIgnoreCase(String studentId);

    List<Student> findByEmailContainingIgnoreCase(String email);

    List<Student> findByProgramContainingIgnoreCase(String program);

    List<Student> findByStatusIgnoreCase(String status);
}