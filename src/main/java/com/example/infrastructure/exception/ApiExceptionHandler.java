package com.example.infrastructure.exception;

import com.example.domain.exception.ApiException;
import com.example.infrastructure.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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
}
