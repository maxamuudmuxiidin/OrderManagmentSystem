package com.OrderManagementSystem.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle DocumentException specifically for PDF generation issues
    @ExceptionHandler(DocumentException.class)
    public ResponseEntity<String> handleDocumentException(DocumentException ex) {
        return new ResponseEntity<>("Failed to generate PDF: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Generic exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
