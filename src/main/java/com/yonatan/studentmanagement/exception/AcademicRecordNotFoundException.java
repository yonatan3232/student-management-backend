package com.yonatan.studentmanagement.exception;

public class AcademicRecordNotFoundException extends RuntimeException {

    public AcademicRecordNotFoundException(String message) {
        super(message);
    }
}