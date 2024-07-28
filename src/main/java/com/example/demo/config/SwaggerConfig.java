package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(name = "Book Store"),
                description = "",
                title = "",
                version = "1.0",
                termsOfService = "Terms of service"
        )
)
public class SwaggerConfig {}