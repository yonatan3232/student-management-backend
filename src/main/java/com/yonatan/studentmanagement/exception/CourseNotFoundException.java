package com.yonatan.studentmanagement.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String message) {
        super(message);
    }
}