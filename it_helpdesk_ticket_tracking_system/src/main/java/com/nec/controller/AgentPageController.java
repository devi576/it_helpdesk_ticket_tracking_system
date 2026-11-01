/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Controller
 *  org.springframework.ui.Model
 *  org.springframework.web.bind.annotation.GetMapping
 */
package com.nec.controller;

import com.nec.model.User;
import com.nec.service.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AgentPageController {
    private final UserService userService;

    public AgentPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value={"/agents"})
    public String agents(Model model) {
        model.addAttribute("currentUser", (Object)this.userService.getCurrentUser());
        List<User> agents = this.userService.findAll().stream().filter(u -> u.getRole() == User.Role.AGENT).toList();
        model.addAttribute("agents", agents);
        return "agents";
    }
}

