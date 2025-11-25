/**
 * Entidad JPA para usuarios del sistema.
 * <p>
 * Representa los datos persistentes de cada usuario, incluyendo roles y credenciales.
 * <br>
 * JPA entity for system users. Represents persistent user data, including roles and credentials.
 * <p>
 * Autor / Author: Fernando Cote
 * Fecha / Date: 2025-11-24
 */
package main.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "\"users\"")
public class User {

    /**
     * Identificador único del usuario / Unique user identifier
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * Nombre de usuario / Username
     */
    @NotBlank
    private String userName;

    /**
     * Correo electrónico / Email
     */
    @NotBlank
    @Email
    private String email;

    /**
     * Contraseña cifrada / Encrypted password
     */
    @NotBlank
    private String password;

    /**
     * Roles asignados al usuario / User roles
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private java.util.List<String> role;
}
