/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.Valid
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.CrossOrigin
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.nec.controller;

import com.nec.dto.TicketAssignmentRequest;
import com.nec.dto.TicketRequest;
import com.nec.dto.TicketResponse;
import com.nec.model.Ticket;
import com.nec.model.User;
import com.nec.service.TicketService;
import com.nec.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/tickets"})
@CrossOrigin(origins={"*"})
public class TicketController {
    private final TicketService ticketService;
    private final UserService userService;

    public TicketController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TicketRequest req) {
        User currentUser = this.userService.getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.status((int)401).body(Map.of("error", "You must be logged in to create a ticket"));
        }
        req.setCreatedByUserId(currentUser.getId());
        return ResponseEntity.ok(this.ticketService.create(req));
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> list() {
        return ResponseEntity.ok(this.ticketService.list());
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<Ticket> get(@PathVariable Long id) {
        Ticket ticket = this.ticketService.get(id);
        return ticket == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(ticket);
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<TicketResponse> update(@PathVariable Long id, @Valid @RequestBody TicketRequest req) {
        return ResponseEntity.ok(this.ticketService.update(id, req));
    }

    @DeleteMapping(value={"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.ticketService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value={"/status/{status}"})
    public ResponseEntity<List<TicketResponse>> getByStatus(@PathVariable Ticket.Status status) {
        return ResponseEntity.ok(this.ticketService.getTicketsByStatus(status));
    }

    @GetMapping(value={"/priority/{priority}"})
    public ResponseEntity<List<TicketResponse>> getByPriority(@PathVariable Ticket.Priority priority) {
        return ResponseEntity.ok(this.ticketService.getTicketsByPriority(priority));
    }

    @GetMapping(value={"/user/{userId}"})
    public ResponseEntity<List<TicketResponse>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(this.ticketService.getTicketsByUser(userId));
    }

    @GetMapping(value={"/assigned/{agentId}"})
    public ResponseEntity<List<TicketResponse>> getAssignedTickets(@PathVariable Long agentId) {
        return ResponseEntity.ok(this.ticketService.getAssignedTickets(agentId));
    }

    @PostMapping(value={"/assign"})
    public ResponseEntity<?> assignTicket(@RequestBody TicketAssignmentRequest req) {
        User currentUser = this.userService.getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.status((int)401).body(Map.of("error", "You must be logged in to assign a ticket"));
        }
        req.setAssignedByUserId(currentUser.getId());
        if (req.getTicketId() == null || req.getAssignedToUserId() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "ticketId and assignedToUserId are required"));
        }
        return ResponseEntity.ok(this.ticketService.assignTicket(req));
    }

    @PostMapping(value={"/{ticketId}/reassign/{newAgentId}"})
    public ResponseEntity<TicketResponse> reassignTicket(@PathVariable Long ticketId, @PathVariable Long newAgentId, @RequestParam String reason) {
        return ResponseEntity.ok(this.ticketService.reassignTicket(ticketId, newAgentId, reason));
    }

    @PostMapping(value={"/{ticketId}/unassign"})
    public ResponseEntity<Void> unassignTicket(@PathVariable Long ticketId) {
        this.ticketService.unassignTicket(ticketId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value={"/{ticketId}/status"})
    public ResponseEntity<TicketResponse> updateStatus(@PathVariable Long ticketId, @RequestParam Ticket.Status status, @RequestParam(required=false) String notes) {
        return ResponseEntity.ok(this.ticketService.updateTicketStatus(ticketId, status, notes));
    }

    @PostMapping(value={"/{ticketId}/resolve"})
    public ResponseEntity<TicketResponse> resolveTicket(@PathVariable Long ticketId, @RequestParam String resolutionNotes, @RequestParam Integer actualHours) {
        return ResponseEntity.ok(this.ticketService.resolveTicket(ticketId, resolutionNotes, actualHours));
    }

    @GetMapping(value={"/metrics"})
    public ResponseEntity<Map<String, Object>> getMetrics() {
        return ResponseEntity.ok(this.ticketService.getTicketMetrics());
    }

    @GetMapping(value={"/overdue"})
    public ResponseEntity<List<Ticket>> getOverdueTickets() {
        return ResponseEntity.ok(this.ticketService.getOverdueTickets());
    }

    @GetMapping(value={"/sla-breaching"})
    public ResponseEntity<List<Ticket>> getSlaBreachingTickets() {
        return ResponseEntity.ok(this.ticketService.getSlaBreachingTickets());
    }

    @GetMapping(value={"/search"})
    public ResponseEntity<List<TicketResponse>> searchTickets(@RequestParam(required=false) String query, @RequestParam(required=false) Ticket.Status status, @RequestParam(required=false) Ticket.Priority priority) {
        return ResponseEntity.ok(this.ticketService.searchTickets(query, status, priority));
    }
}

