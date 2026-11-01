/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.boot.CommandLineRunner
 *  org.springframework.security.crypto.password.PasswordEncoder
 *  org.springframework.stereotype.Component
 */
package com.nec.config;

import com.nec.model.User;
import com.nec.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer
implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(String ... args) throws Exception {
        if (this.userRepository.count() == 0L) {
            User adminUser = User.builder().name("Admin User").email("admin@helpdesk.com").password(this.passwordEncoder.encode((CharSequence)"admin123")).role(User.Role.ADMIN).enabled(true).build();
            this.userRepository.save(adminUser);
            User testUser = User.builder().name("Test User").email("user@helpdesk.com").password(this.passwordEncoder.encode((CharSequence)"user123")).role(User.Role.USER).enabled(true).build();
            this.userRepository.save(testUser);
            System.out.println("Default users created:");
            System.out.println("Admin: admin@helpdesk.com / admin123");
            System.out.println("User: user@helpdesk.com / user123");
        }
    }
}

