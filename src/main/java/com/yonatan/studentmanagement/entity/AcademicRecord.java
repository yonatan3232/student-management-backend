package com.yonatan.studentmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AcademicRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double assignmentGrade;

    private Double midtermGrade;

    private Double finalExamGrade;

    private Double totalGrade;

    private String letterGrade;

    private Double gradePoint;

    // Academic record belongs to one Student
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Academic record belongs to one Course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAssignmentGrade() {
        return assignmentGrade;
    }

    public void setAssignmentGrade(Double assignmentGrade) {
        this.assignmentGrade = assignmentGrade;
    }

    public Double getMidtermGrade() {
        return midtermGrade;
    }

    public void setMidtermGrade(Double midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public Double getFinalExamGrade() {
        return finalExamGrade;
    }

    public void setFinalExamGrade(Double finalExamGrade) {
        this.finalExamGrade = finalExamGrade;
    }

    public Double getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(Double totalGrade) {
        this.totalGrade = totalGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public Double getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(Double gradePoint) {
        this.gradePoint = gradePoint;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}