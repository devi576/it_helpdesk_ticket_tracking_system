/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Controller
 *  org.springframework.ui.Model
 *  org.springframework.web.bind.annotation.GetMapping
 */
package com.nec.controller;

import com.nec.service.TicketService;
import com.nec.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportPageController {
    private final TicketService ticketService;
    private final UserService userService;

    public ReportPageController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping(value={"/reports"})
    public String reports(Model model) {
        model.addAttribute("currentUser", (Object)this.userService.getCurrentUser());
        model.addAttribute("metrics", this.ticketService.getTicketMetrics());
        return "reports";
    }
}

