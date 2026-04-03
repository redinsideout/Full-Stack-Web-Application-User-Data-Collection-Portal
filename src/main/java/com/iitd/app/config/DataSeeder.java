package com.iitd.app.config;

import com.iitd.app.entity.User;
import com.iitd.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String demoEmail = "info@poshatva.com";
        if (!userRepository.existsByEmail(demoEmail)) {
            User demoUser = new User();
            demoUser.setEmail(demoEmail);
            demoUser.setPassword(passwordEncoder.encode("Poshatva@1234"));
            userRepository.save(demoUser);
            System.out.println("✅ Demo user seeded: info@poshatva.com / Poshatva@1234");
        } else {
            System.out.println("ℹ️  Demo user already exists, skipping seed.");
        }
    }
}
