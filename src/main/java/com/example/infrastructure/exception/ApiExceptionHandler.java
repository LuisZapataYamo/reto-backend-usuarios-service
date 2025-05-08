package com.example.infrastructure.exception;

import com.example.domain.exception.ApiException;
import com.example.infrastructure.constants.DataIntegrityConstants;
import com.example.infrastructure.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponseDto> handleExceptionDomain(ApiException exception) {
        List<String> errorList = List.of(exception.getMessage());

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                errorList,
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.valueOf(exception.getHttpStatus().getCode()))
                .body(errorResponse);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        Throwable cause = ex.getMostSpecificCause();
        String errorMessage = cause.getMessage();
        String message = errorMessage;

        Pattern pattern = Pattern.compile(DataIntegrityConstants.FIELD_PATTERN);
        Matcher matcher = pattern.matcher(errorMessage);

        if (matcher.find()) {
            message = String.format(DataIntegrityConstants.DUPLICATE_FIELD_MESSAGE,matcher.group(1));
        }

        ErrorResponseDto error = new ErrorResponseDto(
                List.of(DataIntegrityConstants.DUPLICATE_KEY_CODE),
                message
        );

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
