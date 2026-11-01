/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.boot.test.context.TestConfiguration
 *  org.springframework.boot.testcontainers.service.connection.ServiceConnection
 *  org.springframework.context.annotation.Bean
 *  org.testcontainers.containers.MySQLContainer
 *  org.testcontainers.utility.DockerImageName
 */
package com.nec;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods=false)
class TestcontainersConfiguration {
    TestcontainersConfiguration() {
    }

    @Bean
    @ServiceConnection
    MySQLContainer<?> mysqlContainer() {
        return new MySQLContainer(DockerImageName.parse((String)"mysql:latest"));
    }
}

