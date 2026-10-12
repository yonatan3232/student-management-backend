package com.yonatan.studentmanagement.exception;

public class EnrollmentNotFoundException extends RuntimeException {

    public EnrollmentNotFoundException(String message) {
        super(message);
    }
}