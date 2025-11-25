/**
 * Manejador global de excepciones para la API REST.
 * <p>
 * Captura y responde a errores de forma centralizada, mejorando la experiencia del cliente.
 * <br>
 * Global exception handler for the REST API. Captures and responds to errors centrally,
 * improving client experience.
 * <p>
 * Autor / Author: Fernando Cote
 * Fecha / Date: 2025-11-24
 */
package main.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }
}