package com.heystyles.usuarios.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.heystyles.usuarios.api.async",
        "com.heystyles.usuarios.api.config",
        "com.heystyles.usuarios.api.controller",
        "com.heystyles.usuarios.api.converter",
        "com.heystyles.usuarios.api.dao",
        "com.heystyles.usuarios.api.entity",
        "com.heystyles.usuarios.api.service",
        "com.heystyles.usuarios.api.exception",
        "com.heystyles.usuarios.api.http",
        "com.heystyles.usuarios.api.message",
        "com.heystyles.usuarios.api.service",
        "com.heystyles.usuarios.api.validator",
})
@SpringBootApplication
public class UsuariosApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UsuariosApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UsuariosApp.class, args);
    }

}
