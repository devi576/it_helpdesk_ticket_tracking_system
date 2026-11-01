/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Service
 */
package com.nec.service;

import com.nec.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User getByEmail(String var1);

    public User save(User var1);

    public Optional<User> findById(Long var1);

    public List<User> findAll();

    public boolean existsByEmail(String var1);

    public User getCurrentUser();

    public void delete(Long var1);
}

