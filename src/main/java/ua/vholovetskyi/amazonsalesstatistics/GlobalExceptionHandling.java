package ua.vholovetskyi.amazonsalesstatistics;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.vholovetskyi.amazonsalesstatistics.exception.ErrorResponse;
import ua.vholovetskyi.amazonsalesstatistics.exception.ResourceNotFound;
import ua.vholovetskyi.amazonsalesstatistics.exception.ValidationErrorResponse;
import ua.vholovetskyi.amazonsalesstatistics.exception.Violation;
import ua.vholovetskyi.amazonsalesstatistics.security.exception.UserAlreadyExistsException;


import java.time.LocalDateTime;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-02-28
 */

@ControllerAdvice
public class GlobalExceptionHandling {
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse illegalArgumentHandler(IllegalArgumentException exception) {
        return getErrorResponse(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse resourceNotFound(ResourceNotFound exception) {
        return getErrorResponse(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ErrorResponse accessDeniedHandler(AccessDeniedException exception) {
        return getErrorResponse(HttpStatus.FORBIDDEN.name(), HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse usernameNotFoundHandler(UsernameNotFoundException exception) {
        return getErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse userAlreadyExistsHandler(UserAlreadyExistsException exception) {
        return getErrorResponse(HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(), exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse methodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }

    private ErrorResponse getErrorResponse(String status, int statusCode, String message){
        return new ErrorResponse(status, statusCode, message, LocalDateTime.now());
    }
}
