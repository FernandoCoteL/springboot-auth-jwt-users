/**
 * Controlador REST para autenticación y registro de usuarios.
 * <p>
 * Expone endpoints para registrar usuarios y autenticarlos, generando tokens JWT.
 * <br>
 * REST controller for user authentication and registration. Exposes endpoints to register users
 * and authenticate, generating JWT tokens.
 * <p>
 * Autor / Author: Fernando Cote
 * Fecha / Date: 2025-11-24
 */
package main.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import main.dto.UserDTO;
import main.entity.User;
import main.security.JwtUtil;
import main.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * Endpoint para registrar un nuevo usuario.
     * <br>
     * Endpoint to register a new user.
     *
     * @param userDTO datos del usuario / user data
     * @return ResponseEntity con el usuario registrado / registered user
     */
    @Operation(summary = "Registrar usuario", description = "Crea un nuevo usuario en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "409", description = "El usuario ya existe")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        logger.info("Intentando registrar usuario: " + userDTO.getUserName());
        User user = userService.registerUser(userDTO);
        return ResponseEntity.status(201).body(user);
    }

    /**
     * Endpoint para autenticar usuario y generar token JWT.
     * <br>
     * Endpoint to authenticate user and generate JWT token.
     *
     * @param userDTO datos de login / login data
     * @return ResponseEntity con el token JWT / JWT token
     */
    @Operation(summary = "Autenticar usuario", description = "Recibe credenciales y retorna un token JWT si son válidas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login exitoso, token generado"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        logger.info("Intentando ingresar usuario: " + userDTO.getUserName());
        User user = userService.login(userDTO.getUserName(), userDTO.getPassword());
        String token = jwtUtil.generateToken(user.getUserName());
        return ResponseEntity.ok(Map.of("token", token));
    }

    /**
     * Endpoint para refrescar usuario y generar token JWT.
     * <br>
     * Endpoint to refresh user and generate JWT token.
     *
     * @param String token
     * @return ResponseEntity con el token JWT / JWT token
     */
    @Operation(summary = "Refresca el token JWT", description = "Genera un nuevo token JWT si el anterior es válido y no ha expirado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Token refrescado correctamente"),
            @ApiResponse(responseCode = "401", description = "Token expirado o inválido")
    })
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody String oldToken) {
        String username = jwtUtil.extractUsername(oldToken);

        if (!jwtUtil.validateToken(oldToken, username)) {
            return ResponseEntity.status(401).body("Token expirado o inválido");
        }

        String newToken = jwtUtil.generateToken(username);

        return ResponseEntity.ok(Map.of("token", newToken));
    }
}
