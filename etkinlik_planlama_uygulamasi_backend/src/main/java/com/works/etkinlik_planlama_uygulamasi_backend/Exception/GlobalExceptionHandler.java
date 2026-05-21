package com.works.etkinlik_planlama_uygulamasi_backend.Exception;

import com.works.etkinlik_planlama_uygulamasi_backend.dto.errorResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // Uygulama genelindeki istisnaları yakalayan merkezi bir filtreleme Bean'idir.
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<errorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        errorResponseDTO errorResponse = new errorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                LocalDateTime.now(),
                errors
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<errorResponseDTO> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        errorResponseDTO errorResponse = new errorResponseDTO(
                HttpStatus.CONFLICT.value(),
                "Bu etkinlik silinemiyor. İlişkili kayıtlar temizlenemedi; lütfen tekrar deneyin.",
                LocalDateTime.now(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<errorResponseDTO> handleRuntimeException(RuntimeException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex.getMessage().contains("bulunamadı")) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex.getMessage().contains("yetki") || ex.getMessage().contains("giriş yapmalısınız")) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex.getMessage().contains("zaten kullanımda")) {
            status = HttpStatus.CONFLICT;
        }

        errorResponseDTO errorResponse = new errorResponseDTO(
                status.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                null
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}