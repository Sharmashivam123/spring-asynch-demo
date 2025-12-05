package com.ss.spring_asynch_demo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(OrderProcessingException.class)
    public ResponseEntity<StandardErrorResponse> handle(OrderProcessingException e) {
        StandardErrorResponse response = StandardErrorResponse.builder()
                .message(e.getMessage())
                .status(500)
                .errorCode("ORDER_PROCESSING_ERROR")
                .build();
        return ResponseEntity.status(500).body(response);
    }
}
