# Spring Boot Auth JWT Users  
Proyecto de autenticación y gestión de usuarios usando Spring Boot, Spring Security y JWT.  
Authentication and user management project using Spring Boot, Spring Security, and JWT.

---

## 📌 Descripción / Description
Este proyecto implementa una API REST para registrar, autenticar y consultar usuarios utilizando JSON Web Tokens (JWT) para proteger los endpoints. Incluye buenas prácticas de arquitectura, validaciones y manejo de excepciones.  
This project provides a REST API for user registration, authentication, and profile access using JSON Web Tokens (JWT) to secure endpoints. It follows best practices in architecture, validation, and exception handling.

---

## 🚀 Características / Features
- Registro de usuarios / User registration  
- Autenticación (login) y generación de JWT / Authentication (login) and JWT generation  
- Endpoints protegidos con JWT / JWT-secured endpoints  
- Consulta del perfil del usuario autenticado / Authenticated user profile endpoint  
- Roles de usuario / User roles  
- Validaciones y manejo global de excepciones / Validation & global exception handler  
- Pruebas unitarias básicas / Basic unit tests  

---

## 📂 Estructura del Proyecto / Project Structure
```
main/
  config/         # Configuración de seguridad, CORS y Jackson / Security, CORS & Jackson config
  controller/     # Controladores REST / REST controllers
  dto/            # Data Transfer Objects
  entity/         # Entidades JPA / JPA entities
  exception/      # Manejo global de excepciones / Global exception handling
  repository/     # Repositorios JPA / JPA repositories
  security/       # JWT, filtros y utils de seguridad / JWT, filters & security utils
  service/        # Lógica de negocio / Business logic
resources/
  application.yml # Configuración principal / Main configuration
```

---

## 🧪 Ejemplos de Uso / Usage Examples

### 📍 Registro / Register
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"userName":"nuevoUsuario","email":"nuevo@correo.com","password":"123456","role":["USER"]}'
```

### 🔐 Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"userName":"nuevoUsuario","password":"123456"}'
```

**Respuesta / Response**
```json
{"token": "<jwt_token>"}
```

### 🔒 Endpoint protegido / Protected endpoint
```bash
curl -X GET http://localhost:8080/api/user/profile \
  -H "Authorization: Bearer <jwt_token>"
```

---

## 📘 Documentación Swagger / Swagger Documentation
La API incluye documentación interactiva generada con OpenAPI.  
The API includes interactive documentation generated using OpenAPI.

- Swagger UI: http://localhost:8080/swagger-ui.html  
- OpenAPI docs: http://localhost:8080/v3/api-docs

---

## 🛠️ Requisitos / Requirements
- Java 17+  
- Maven  
- Spring Boot 4.x  

---

## ▶️ Cómo ejecutar / How to Run
1. Clona el repositorio / Clone the repository  
2. Ejecuta:  
   ```bash
   mvn spring-boot:run
   ```  
3. Prueba los endpoints con curl, Postman o Swagger UI.  
   Test endpoints with curl, Postman, or Swagger UI.

---

## 📚 Aprendizaje y Aportación / Learning & Contribution
- Proyecto creado como parte de mi portafolio en GitHub.  
- Mejora de habilidades en Spring Boot, JWT y Spring Security.  
- Uso de AI (GitHub Copilot) y revisión de desarrollador para asegurar buenas prácticas.  

---

## 📝 Licencia / License  
Este proyecto está bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.  
This project is licensed under the MIT license. See the LICENSE file for details.

---

## 📬 Contacto / Contact
**Fernando Cote**  
Email: fernando.cote.lozano@gmail.com  
GitHub: https://github.com/fernandocote/springboot-auth-jwt-users  

---

## 📝 Notas / Notes
- No utilices la clave JWT incluida en el ejemplo para entornos productivos.  
- El proyecto puede ampliarse con endpoints de administración, refresco de JWT, paginación, etc.  

---
