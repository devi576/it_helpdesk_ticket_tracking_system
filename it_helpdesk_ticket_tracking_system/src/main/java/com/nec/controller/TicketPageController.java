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
import com.nec.service.TicketService;
import com.nec.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketPageController {
    private final TicketService ticketService;
    private final UserService userService;

    public TicketPageController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping(value={"/tickets"})
    public String list(Model model) {
        model.addAttribute("currentUser", (Object)this.userService.getCurrentUser());
        model.addAttribute("tickets", this.ticketService.list());
        model.addAttribute("agents", this.userService.findAll().stream().filter(u -> u.getRole() == User.Role.AGENT).toList());
        return "tickets/list";
    }

    @GetMapping(value={"/tickets/create"})
    public String createForm(Model model) {
        model.addAttribute("currentUser", (Object)this.userService.getCurrentUser());
        return "tickets/create";
    }
}

