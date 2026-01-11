package com.example.projektApi;

import com.example.projektApi.model.Role;
import com.example.projektApi.model.User;
import com.example.projektApi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjektApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjektApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User(
                        "admin",
                        passwordEncoder.encode("admin123"),
                        "admin@example.com",
                        Role.ADMIN
                );
                userRepository.save(admin);
            }
        };
    }
}
