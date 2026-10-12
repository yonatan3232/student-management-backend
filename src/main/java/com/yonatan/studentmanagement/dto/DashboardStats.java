package com.yonatan.studentmanagement.dto;

public class DashboardStats {

    private long totalStudents;
    private long totalDepartments;
    private long totalCourses;
    private long totalUsers;
    private long totalEnrollments;
    private long totalAcademicRecords;
    private long totalAttendance;

    public DashboardStats(
            long totalStudents,
            long totalDepartments,
            long totalCourses,
            long totalUsers,
            long totalEnrollments,
            long totalAcademicRecords,
            long totalAttendance) {

        this.totalStudents = totalStudents;
        this.totalDepartments = totalDepartments;
        this.totalCourses = totalCourses;
        this.totalUsers = totalUsers;
        this.totalEnrollments = totalEnrollments;
        this.totalAcademicRecords = totalAcademicRecords;
        this.totalAttendance = totalAttendance;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public long getTotalDepartments() {
        return totalDepartments;
    }

    public long getTotalCourses() {
        return totalCourses;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalEnrollments() {
        return totalEnrollments;
    }

    public long getTotalAcademicRecords() {
        return totalAcademicRecords;
    }

    public long getTotalAttendance() {
        return totalAttendance;
    }
}