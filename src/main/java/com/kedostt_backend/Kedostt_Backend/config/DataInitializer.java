package com.kedostt_backend.Kedostt_Backend.config;

import com.kedostt_backend.Kedostt_Backend.model.Role;
import com.kedostt_backend.Kedostt_Backend.model.User;
import com.kedostt_backend.Kedostt_Backend.repository.RoleRepository;
import com.kedostt_backend.Kedostt_Backend.repository.UserRepository;
import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
    public void initData() {

            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(Set.of(adminRole));

                userRepository.save(admin);
                System.out.println("✅ Varsayılan admin kullanıcısı oluşturuldu (admin / admin123)");
            }
    }
}
