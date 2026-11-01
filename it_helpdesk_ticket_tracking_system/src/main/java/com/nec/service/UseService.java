/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Service
 */
package com.nec.service;

import com.nec.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UseService {
    public User getByEmail(String var1);
}

