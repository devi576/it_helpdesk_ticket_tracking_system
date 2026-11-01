/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Service
 */
package com.nec.service;

import com.nec.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl
implements EmailService {
    @Override
    public void send(String to, String subject, String body) {
        System.out.println("Sending email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}

