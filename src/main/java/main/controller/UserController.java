/**
 * Controlador REST para operaciones de usuario autenticado.
 * <p>
 * Permite consultar el perfil del usuario autenticado y otras operaciones protegidas por JWT.
 * <br>
 * REST controller for authenticated user operations. Allows querying the profile of the authenticated user
 * and other JWT-protected operations.
 * <p>
 * Autor / Author: Fernando Cote
 * Fecha / Date: 2025-11-24
 */
package main.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import main.dto.UserDTO;
import main.entity.User;
import main.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Retorna el perfil del usuario autenticado usando el JWT.
     * <br>
     * Returns the profile of the authenticated user using JWT.
     *
     * @param authentication contexto de autenticación / authentication context
     * @return perfil del usuario / user profile
     */
    @Operation(summary = "Obtener perfil del usuario autenticado", description = "Devuelve los datos del usuario actualmente autenticado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Perfil obtenido correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado o token inválido")
    })
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile(Authentication authentication) {
        logger.info("Intentando obtener perfil: " + authentication.getName());
        String userName = authentication.getName();
        Optional<User> user = userService.findByUserName(userName);
        UserDTO dto = mapToDTO(user);
        return ResponseEntity.ok(dto);
    }

    /**
     * Convierte la entidad User en un objeto UserDTO para exponer solo datos
     * relevantes.
     * <br>
     * Converts the User entity to a UserDTO to expose only relevant data.
     *
     * @param user usuario a convertir / user to convert
     * @return DTO del usuario / user DTO
     */
    public UserDTO mapToDTO(Optional<User> user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.get().getId());
        dto.setUserName(user.get().getUserName());
        dto.setEmail(user.get().getEmail());
        dto.setRole(user.get().getRole());
        return dto;
    }

}
