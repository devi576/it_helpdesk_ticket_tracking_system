/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Controller
 *  org.springframework.ui.Model
 *  org.springframework.web.bind.annotation.GetMapping
 */
package com.nec.controller;

import com.nec.model.Ticket;
import com.nec.model.User;
import com.nec.service.TicketService;
import com.nec.service.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final TicketService ticketService;
    private final UserService userService;

    public DashboardController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping(value={"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("title", (Object)"Helpdesk Dashboard");
        User currentUser = this.userService.getCurrentUser();
        model.addAttribute("currentUser", (Object)currentUser);
        List<Ticket> tickets = this.ticketService.list();
        model.addAttribute("totalTickets", (Object)(tickets != null ? tickets.size() : 0));
        model.addAttribute("openTickets", (Object)(tickets != null ? tickets.stream().filter(t -> t.getStatus() == Ticket.Status.OPEN).count() : 0L));
        model.addAttribute("resolvedTickets", (Object)(tickets != null ? tickets.stream().filter(t -> t.getStatus() == Ticket.Status.RESOLVED).count() : 0L));
        model.addAttribute("recentTickets", tickets != null ? tickets.stream().limit(5L).toList() : List.of());
        List<User> allUsers = this.userService.findAll();
        long activeAgents = allUsers != null ? allUsers.stream().filter(u -> u.getRole() == User.Role.AGENT && Boolean.TRUE.equals(u.getIsAvailable())).count() : 0L;
        model.addAttribute("activeAgents", (Object)activeAgents);
        return "dashboard";
    }

    @GetMapping(value={"/login"})
    public String login() {
        return "redirect:/auth/login";
    }
}

