package main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI configSwApi() {
        Contact contact = new Contact();
        contact.setEmail("fernando.cote.lozano@gmail.com");
        contact.setName("Fernando Cote");
            contact.setUrl("https://github.com/fernandocote/springboot-auth-jwt-users");

        Info info = new Info();
            info.setTitle("API de Autenticación JWT - Spring Boot");
            info.setDescription("API RESTful para autenticación y gestión de usuarios usando Spring Boot, JWT y Swagger. Ideal para portafolio y aprendizaje.");
            info.setVersion("1.0.0");
            info.setContact(contact);
            info.setLicense(new io.swagger.v3.oas.models.info.License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT"));

            return new OpenAPI().info(info);
    }
    
}
