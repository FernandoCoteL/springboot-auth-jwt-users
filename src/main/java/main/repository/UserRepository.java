/**
 * Repositorio JPA para la entidad User.
 * <p>
 * Proporciona métodos para consultar y persistir usuarios en la base de datos.
 * <br>
 * JPA repository for the User entity. Provides methods to query and persist users in the database.
 * <p>
 * Autor / Author: Fernando Cote
 * Fecha / Date: 2025-11-24
 */
package main.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import main.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    /**
     * Busca usuario por email.
     * <br>
     * Finds user by email.
     *
     * @param email correo electrónico / email
     * @return usuario encontrado (opcional) / found user (optional)
     */
    Optional<User> findByEmail(String email);

    /**
     * Busca usuario por nombre de usuario.
     * <br>
     * Finds user by username.
     *
     * @param userName nombre de usuario / username
     * @return usuario encontrado (opcional) / found user (optional)
     */
    Optional<User> findByUserName(String userName);

    /**
     * Verifica si existe usuario por email.
     * <br>
     * Checks if user exists by email.
     *
     * @param email correo electrónico / email
     * @return true si existe / true if exists
     */
    boolean existsByEmail(String email);

    /**
     * Verifica si existe usuario por nombre de usuario.
     * <br>
     * Checks if user exists by username.
     *
     * @param userName nombre de usuario / username
     * @return true si existe / true if exists
     */
    boolean existsByUserName(String userName);

    /**
     * Busca usuarios por rol.
     * <br>
     * Finds users by role.
     *
     * @param role rol de usuario / user role
     * @return lista de usuarios / list of users
     */
    java.util.List<User> findByRole(String role);
}
