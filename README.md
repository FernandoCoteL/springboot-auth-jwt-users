
# Spring Boot Auth JWT Users

Proyecto de autenticación y gestión de usuarios con Spring Boot, JWT y Spring Security.

Authentication and user management project using Spring Boot, JWT and Spring Security.

---

## Descripción / Description
Este proyecto es una API REST para registro, autenticación y consulta de usuarios, usando JWT para proteger los endpoints. Incluye buenas prácticas de arquitectura y seguridad.

This project is a REST API for user registration, authentication and profile query, using JWT to protect endpoints. It follows best practices in architecture and security.

---

## Características / Features
- Registro de usuarios / User registration
- Autenticación (login) y generación de JWT / Authentication (login) and JWT generation
- Endpoints protegidos por JWT / JWT-protected endpoints
- Consulta de perfil del usuario autenticado / Authenticated user profile query
- Roles de usuario / User roles
- Validaciones y manejo global de excepciones / Validation and global exception handling
- Pruebas unitarias básicas / Basic unit tests

---

## Estructura del proyecto / Project structure
```
main/
  config/         # Configuración de seguridad y Jackson / Security & Jackson config
  controller/     # Controladores REST / REST controllers
  dto/            # Data Transfer Objects
  entity/         # Entidades JPA / JPA entities
  exception/      # Manejo global de excepciones / Global exception handling
  repository/     # Repositorios JPA / JPA repositories
  security/       # JWT y filtros de seguridad / JWT & security filters
  service/        # Lógica de negocio / Business logic
resources/
  application.yml # Configuración de la app / App config
```

---

## Ejemplo de uso / Usage example
### Registro / Register
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"userName":"nuevoUsuario","email":"nuevo@correo.com","password":"123456","role":["USER"]}'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"userName":"nuevoUsuario","password":"123456"}'
```
La API cuenta con documentación interactiva generada automáticamente con Swagger/OpenAPI. Puedes explorar y probar los endpoints desde el navegador:

- URL Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- Documentación OpenAPI: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

La configuración incluye título, descripción, versión, términos de servicio, contacto y licencia MIT para mayor profesionalismo.

---
Respuesta / Response:
```json
{"token": "<jwt_token>"}
```

### Acceso protegido / Protected access
```bash
curl -X GET http://localhost:8080/api/user/profile \
  -H "Authorization: Bearer <jwt_token>"
```

---

## Requisitos / Requirements
- Java 17+
- Maven
- Spring Boot 4.x

---

## Aprendizaje y aportación / Learning & contribution
- Proyecto realizado como parte de mi portafolio en GitHub. / Project created for my GitHub portfolio.
- Curva de aprendizaje en Spring Boot, JWT y Spring Security. / Learning curve in Spring Boot, JWT and Spring Security.
- Se usó apoyo de AI (GitHub Copilot) y revisión de desarrollador para asegurar buenas prácticas y calidad. / AI support (GitHub Copilot) and developer review were used to ensure best practices and quality.
## Licencia / License
Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

---
## Contacto / Contact
Fernando Cote
Email: fernando.cote.lozano@gmail.com
GitHub: [https://github.com/fernandocote/springboot-auth-jwt-users](https://github.com/fernandocote/springboot-auth-jwt-users)

¡Gracias por revisar mi proyecto! / Thanks for reviewing my project!

## Cómo ejecutar / How to run
1. Clona el repositorio / Clone the repository
2. Ejecuta `mvn spring-boot:run` / Run `mvn spring-boot:run`
3. Prueba los endpoints con curl o Postman / Test endpoints with curl or Postman

---

## Notas / Notes
- No uses la clave JWT de ejemplo en producción. / Do not use the example JWT secret in production.
- Puedes extender el proyecto agregando endpoints de administración, Swagger, etc. / You can extend the project by adding admin endpoints, Swagger, etc.

---
¡Gracias por revisar mi proyecto! / Thanks for reviewing my project!
