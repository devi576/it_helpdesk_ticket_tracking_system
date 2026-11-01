/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.boot.SpringApplication
 *  org.springframework.boot.test.context.SpringBootTest
 *  org.testcontainers.junit.jupiter.Testcontainers
 */
package com.nec;

import com.nec.HelpDeskSystem2Application;
import com.nec.TestcontainersConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class TestHelpDeskSystem2Application {
    public static void main(String[] args) {
        SpringApplication.from(HelpDeskSystem2Application::main).with(new Class[]{TestcontainersConfiguration.class}).run(args);
    }
}

