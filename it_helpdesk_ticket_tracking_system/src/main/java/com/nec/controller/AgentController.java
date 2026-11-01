/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.Valid
 *  org.springframework.http.ResponseEntity
 *  org.springframework.security.crypto.password.PasswordEncoder
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.nec.controller;

import com.nec.dto.AgentRequest;
import com.nec.model.User;
import com.nec.service.UserService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/agents"})
public class AgentController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AgentController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AgentRequest req) {
        User currentUser = this.userService.getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.status((int)401).body(Map.of("error", "You must be logged in"));
        }
        if (currentUser.getRole() != User.Role.ADMIN && currentUser.getRole() != User.Role.MANAGER) {
            return ResponseEntity.status((int)403).body(Map.of("error", "Only an admin or manager can add agents"));
        }
        if (this.userService.existsByEmail(req.getEmail())) {
            return ResponseEntity.status((int)409).body(Map.of("error", "A user with that email already exists"));
        }
        User agent = User.builder().name(req.getName()).email(req.getEmail()).password(this.passwordEncoder.encode((CharSequence)req.getPassword())).role(User.Role.AGENT).maxTickets(req.getMaxTickets() != null ? req.getMaxTickets() : 10).isAvailable(true).enabled(true).build();
        User saved = this.userService.save(agent);
        return ResponseEntity.ok(Map.of("id", saved.getId(), "name", saved.getName(), "email", saved.getEmail(), "maxTickets", saved.getMaxTickets()));
    }

    @DeleteMapping(value={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        User currentUser = this.userService.getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.status((int)401).body(Map.of("error", "You must be logged in"));
        }
        if (currentUser.getRole() != User.Role.ADMIN && currentUser.getRole() != User.Role.MANAGER) {
            return ResponseEntity.status((int)403).body(Map.of("error", "Only an admin or manager can remove agents"));
        }
        User agent = this.userService.findById(id).orElse(null);
        if (agent == null || agent.getRole() != User.Role.AGENT) {
            return ResponseEntity.status((int)404).body(Map.of("error", "Agent not found"));
        }
        if (agent.getCurrentTicketCount() != null && agent.getCurrentTicketCount() > 0) {
            return ResponseEntity.status((int)409).body(Map.of("error", "This agent still has " + String.valueOf(agent.getCurrentTicketCount()) + " ticket(s) assigned. Reassign them first."));
        }
        this.userService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Agent removed"));
    }
}

