package com.scale.global.insurance.app.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Customer Insurance Service")
                        .description("Customer Insurance Service description")
                        .version("1.0")
                        .contact(new Contact()
                                .name("FirstName LastName")
                                .url("http://www.global-scale.com/")
                                .email("info@global-scale.com"))
                        .license(new License()
                                .name("Apache License Version 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .termsOfService("Commercial register number: HRB 246345"));
    }
}
