package com.example;

import com.example.domain.enums.UserRolEnum;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.out.IUsuarioServicePortOut;
import com.example.infrastructure.exception.EntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class UsuariosServiceApplication implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(UsuariosServiceApplication.class);
    private final IUsuarioServicePortOut usuarioServicePortOut;

    public UsuariosServiceApplication(IUsuarioServicePortOut usuarioServicePortOut) {
        this.usuarioServicePortOut = usuarioServicePortOut;
    }

    public static void main(String[] args) {
        SpringApplication.run(UsuariosServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Verificando existencia de usuario admin...");
        String adminEmail = "jhonayquer@gmail.com";
        String adminPassword = "123456789";

        try {
            usuarioServicePortOut.getUsuarioByEmail(adminEmail);
            log.info("El usuario admin ya existe.");
        } catch (EntityException e) {
            log.info("El usuario admin no existe. Creando usuario admin...");
            UsuarioModel adminUser = new UsuarioModel();
            adminUser.setId(UUID.randomUUID());
            adminUser.setEmail(adminEmail);
            adminUser.setPassword(encriptarConBCrypt(adminPassword));
            adminUser.setRol(UserRolEnum.ROLE_ADMIN);
            usuarioServicePortOut.crearUsuario(adminUser);
            log.info("Usuario admin creado exitosamente.");
        }
    }

    public static String encriptarConBCrypt(String cadena) {
        return new BCryptPasswordEncoder().encode(cadena);
    }
}
