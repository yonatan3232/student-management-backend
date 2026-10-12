package com.yonatan.studentmanagement.exception;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message) {
        super(message);
    }
}