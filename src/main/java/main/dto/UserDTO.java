/**
 * Data Transfer Object (DTO) para exponer datos de usuario.
 * <p>
 * Utilizado para transferir información relevante del usuario sin exponer datos sensibles.
 * <br>
 * Data Transfer Object (DTO) to expose user data. Used to transfer relevant user information
 * without exposing sensitive data.
 * <p>
 * Autor / Author: Fernando Cote
 * Fecha / Date: 2025-11-24
 */
package main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    /**
     * Identificador único del usuario / Unique user identifier
     */
    private Long id;

    /**
     * Nombre de usuario / Username
     */
    private String userName;

    /**
     * Correo electrónico / Email
     */
    private String email;

    /**
     * Contraseña (solo para registro/login, no se expone) / Password (only for registration/login, not exposed)
     */
    private String password;

    /**
     * Roles asignados al usuario / User roles
     */
    private java.util.List<String> role;

}
