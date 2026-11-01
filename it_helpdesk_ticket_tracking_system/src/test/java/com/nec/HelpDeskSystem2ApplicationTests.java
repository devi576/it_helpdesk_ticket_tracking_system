/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.junit.jupiter.api.Test
 *  org.springframework.boot.test.context.SpringBootTest
 *  org.springframework.context.annotation.Import
 */
package com.nec;

import com.nec.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(value={TestcontainersConfiguration.class})
@SpringBootTest
class HelpDeskSystem2ApplicationTests {
    HelpDeskSystem2ApplicationTests() {
    }

    @Test
    void contextLoads() {
    }
}

