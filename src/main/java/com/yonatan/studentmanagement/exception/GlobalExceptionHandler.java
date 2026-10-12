package com.yonatan.studentmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleStudentNotFound(StudentNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleDepartmentNotFound(DepartmentNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCourseNotFound(CourseNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEnrollmentNotFound(EnrollmentNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(AcademicRecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAcademicRecordNotFound(AcademicRecordNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(AttendanceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAttendanceNotFound(AttendanceNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleRoleNotFound(RoleNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFound(UserNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationErrors(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }
}