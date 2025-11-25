/**
 * Excepción personalizada para usuarios ya existentes.
 * <p>
 * Se lanza cuando se intenta registrar un usuario con email o nombre ya registrado.
 * <br>
 * Custom exception for already existing users. Thrown when trying to register a user with
 * an already registered email or username.
 * <p>
 * Autor / Author: Fernando Cote
 * Fecha / Date: 2025-11-24
 */
package main.exception;

public class UserAlreadyExistsException  extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
    
}
