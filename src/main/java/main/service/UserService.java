/**
 * Servicio para la gestión de usuarios y autenticación.
 * <p>
 * Contiene la lógica de negocio para registrar, autenticar y consultar usuarios.
 * <br>
 * Service for user management and authentication. Contains business logic for registering,
 * authenticating and querying users.
 * <p>
 * Autor / Author: Fernando Cote
 * Fecha / Date: 2025-11-24
 */
package main.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import main.dto.UserDTO;
import main.entity.User;
import main.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;


    /**
     * Registra un nuevo usuario en el sistema.
     * <br>
     * Registers a new user in the system.
     *
     * @param userDTO datos del usuario / user data
     * @return usuario registrado / registered user
     */
    public User registerUser(UserDTO userDTO) {

        // Verificar si el email o username ya existen
        if (userRepository.existsByEmail(userDTO.getEmail()) || userRepository.existsByUserName(userDTO.getUserName())) {
            throw new RuntimeException("El usuario ya existe");
        }

         String passwordCifrada = passwordEncoder.encode(userDTO.getPassword());

         // Mapear el DTO a la entidad
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordCifrada);
        user.setRole(new ArrayList<>(Arrays.asList("USER")));
    
        userRepository.save(user);

        return userRepository.save(user);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * <br>
     * Finds a user by username.
     *
     * @param username nombre de usuario / username
     * @return usuario encontrado (opcional) / found user (optional)
     */
    public Optional<User> findByUserName(String username){
        return userRepository.findByUserName(username);
    }

    /**
     * Busca un usuario por su email.
     * <br>
     * Finds a user by email.
     *
     * @param email correo electrónico / email
     * @return usuario encontrado (opcional) / found user (optional)
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Obtiene la lista de todos los usuarios registrados.
     * <br>
     * Gets the list of all registered users.
     *
     * @return lista de usuarios / list of users
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Elimina un usuario por su ID.
     * <br>
     * Deletes a user by ID.
     *
     * @param id identificador del usuario / user ID
     */
    public void deleteUser(Long id){
        this.userRepository.deleteById(id);
    }

    /**
     * Valida las credenciales y retorna el usuario si son correctas.
     * <br>
     * Validates credentials and returns the user if correct.
     *
     * @param username nombre de usuario / username
     * @param password contraseña / password
     * @return usuario autenticado / authenticated user
     */
    public User login(String username, String password) {
        User user = userRepository.findByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }
        return user;
    }
}
