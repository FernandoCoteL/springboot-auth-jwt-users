package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import main.repository.UserRepository;
import main.service.UserService;
import main.dto.UserDTO;
import main.entity.User;

public class UserServiceTest {

    @Test
    public void testRegisterUser() {
        // Mock del repositorio
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        Mockito.when(userRepository.existsByUserName("testuser")).thenReturn(false);

        // Aquí insertas la línea:
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Instancia del encoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Crea el servicio y asigna el mock
        UserService userService = new UserService();
        userService.userRepository = userRepository; // Asigna el mock
        userService.passwordEncoder = passwordEncoder;

        // Crea el DTO
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("testuser");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("123456");

        // Ejecuta el método
        User user = userService.registerUser(userDTO);

        // Verifica el resultado
        assertNotNull(user);
        assertEquals("testuser", user.getUserName());
    }
}