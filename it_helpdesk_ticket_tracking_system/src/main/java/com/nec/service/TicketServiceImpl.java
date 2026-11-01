/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.EntityNotFoundException
 *  jakarta.transaction.Transactional
 *  org.springframework.stereotype.Service
 */
package com.nec.service;

import com.nec.dto.TicketAssignmentRequest;
import com.nec.dto.TicketRequest;
import com.nec.dto.TicketResponse;
import com.nec.model.Ticket;
import com.nec.model.User;
import com.nec.repository.TicketRepository;
import com.nec.repository.UserRepository;
import com.nec.service.EmailService;
import com.nec.service.SLAService;
import com.nec.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl
implements TicketService {
    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;
    private final SLAService slaService;
    private final EmailService emailService;

    public TicketServiceImpl(TicketRepository ticketRepo, UserRepository userRepo, SLAService slaService, EmailService emailService) {
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
        this.slaService = slaService;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public TicketResponse create(TicketRequest req) {
        User creator = (User)this.userRepo.findById(req.getCreatedByUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Ticket ticket = Ticket.builder().title(req.getTitle()).description(req.getDescription()).priority(req.getPriority()).category(req.getCategory()).status(Ticket.Status.OPEN).createdBy(creator).dueDate(req.getDueDate()).estimatedResolutionHours(req.getEstimatedResolutionHours()).build();
        this.slaService.applySlaToTicket(ticket);
        Ticket saved = (Ticket)this.ticketRepo.save(ticket);
        this.emailService.send(creator.getEmail(), "Ticket Created #" + String.valueOf(saved.getId()), "Your ticket has been created and is being reviewed.");
        return this.buildTicketResponse(saved);
    }

    @Override
    public List<Ticket> list() {
        return this.ticketRepo.findAll();
    }

    @Override
    public Ticket get(Long id) {
        return this.ticketRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public TicketResponse update(Long id, TicketRequest req) {
        Ticket ticket = (Ticket)this.ticketRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        ticket.setTitle(req.getTitle());
        ticket.setDescription(req.getDescription());
        ticket.setPriority(req.getPriority());
        ticket.setCategory(req.getCategory());
        ticket.setDueDate(req.getDueDate());
        ticket.setEstimatedResolutionHours(req.getEstimatedResolutionHours());
        Ticket saved = (Ticket)this.ticketRepo.save(ticket);
        return this.buildTicketResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Ticket ticket = (Ticket)this.ticketRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        this.ticketRepo.delete(ticket);
    }

    @Override
    public List<TicketResponse> getTicketsByStatus(Ticket.Status status) {
        return this.ticketRepo.findByStatus(status).stream().map(this::buildTicketResponse).collect(Collectors.toList());
    }

    @Override
    public List<TicketResponse> getTicketsByPriority(Ticket.Priority priority) {
        return this.ticketRepo.findByPriority(priority).stream().map(this::buildTicketResponse).collect(Collectors.toList());
    }

    @Override
    public List<TicketResponse> getTicketsByUser(Long userId) {
        return this.ticketRepo.findByCreatedById(userId).stream().map(this::buildTicketResponse).collect(Collectors.toList());
    }

    @Override
    public List<TicketResponse> getAssignedTickets(Long agentId) {
        return this.ticketRepo.findByAssignedToId(agentId).stream().map(this::buildTicketResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TicketResponse assignTicket(TicketAssignmentRequest req) {
        Ticket ticket = (Ticket)this.ticketRepo.findById(req.getTicketId()).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        User agent = (User)this.userRepo.findById(req.getAssignedToUserId()).orElseThrow(() -> new EntityNotFoundException("Agent not found"));
        User assignedBy = (User)this.userRepo.findById(req.getAssignedByUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!agent.getIsAvailable().booleanValue() || agent.getCurrentTicketCount() >= agent.getMaxTickets()) {
            throw new IllegalStateException("Agent is not available or has reached maximum ticket capacity");
        }
        ticket.setAssignedTo(agent);
        ticket.setStatus(Ticket.Status.IN_PROGRESS);
        ticket.setEstimatedResolutionHours(req.getEstimatedResolutionHours());
        agent.setCurrentTicketCount(agent.getCurrentTicketCount() + 1);
        this.userRepo.save(agent);
        Ticket saved = (Ticket)this.ticketRepo.save(ticket);
        this.emailService.send(agent.getEmail(), "Ticket Assigned #" + String.valueOf(saved.getId()), "You have been assigned ticket: " + saved.getTitle());
        return this.buildTicketResponse(saved);
    }

    @Override
    @Transactional
    public TicketResponse reassignTicket(Long ticketId, Long newAgentId, String reason) {
        Ticket ticket = (Ticket)this.ticketRepo.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        User newAgent = (User)this.userRepo.findById(newAgentId).orElseThrow(() -> new EntityNotFoundException("Agent not found"));
        if (ticket.getAssignedTo() != null) {
            User currentAgent = ticket.getAssignedTo();
            currentAgent.setCurrentTicketCount(Math.max(0, currentAgent.getCurrentTicketCount() - 1));
            this.userRepo.save(currentAgent);
        }
        ticket.setAssignedTo(newAgent);
        newAgent.setCurrentTicketCount(newAgent.getCurrentTicketCount() + 1);
        this.userRepo.save(newAgent);
        Ticket saved = (Ticket)this.ticketRepo.save(ticket);
        this.emailService.send(newAgent.getEmail(), "Ticket Reassigned #" + String.valueOf(saved.getId()), "Ticket has been reassigned to you: " + saved.getTitle() + "\nReason: " + reason);
        return this.buildTicketResponse(saved);
    }

    @Override
    @Transactional
    public void unassignTicket(Long ticketId) {
        Ticket ticket = (Ticket)this.ticketRepo.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        if (ticket.getAssignedTo() != null) {
            User agent = ticket.getAssignedTo();
            agent.setCurrentTicketCount(Math.max(0, agent.getCurrentTicketCount() - 1));
            this.userRepo.save(agent);
            ticket.setAssignedTo(null);
            ticket.setStatus(Ticket.Status.OPEN);
            this.ticketRepo.save(ticket);
        }
    }

    @Override
    @Transactional
    public TicketResponse updateTicketStatus(Long ticketId, Ticket.Status status, String notes) {
        Ticket ticket = (Ticket)this.ticketRepo.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        ticket.setStatus(status);
        if (status == Ticket.Status.RESOLVED) {
            ticket.setResolvedAt(LocalDateTime.now());
        }
        Ticket saved = (Ticket)this.ticketRepo.save(ticket);
        this.emailService.send(ticket.getCreatedBy().getEmail(), "Ticket Status Updated #" + String.valueOf(saved.getId()), "Your ticket status has been updated to: " + String.valueOf((Object)status) + "\nNotes: " + notes);
        return this.buildTicketResponse(saved);
    }

    @Override
    @Transactional
    public TicketResponse resolveTicket(Long ticketId, String resolutionNotes, Integer actualHours) {
        long hoursDiff;
        Ticket ticket = (Ticket)this.ticketRepo.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        ticket.setStatus(Ticket.Status.RESOLVED);
        ticket.setResolvedAt(LocalDateTime.now());
        ticket.setResolutionNotes(resolutionNotes);
        ticket.setActualResolutionHours(actualHours);
        if (ticket.getEstimatedResolutionHours() != null && actualHours != null && (hoursDiff = (long)Math.abs(ticket.getEstimatedResolutionHours() - actualHours)) > 2L) {
            System.out.println("Significant difference in estimated vs actual hours for ticket: " + String.valueOf(ticketId));
        }
        Ticket saved = (Ticket)this.ticketRepo.save(ticket);
        this.emailService.send(ticket.getCreatedBy().getEmail(), "Ticket Resolved #" + String.valueOf(saved.getId()), "Your ticket has been resolved!\nResolution Notes: " + resolutionNotes);
        return this.buildTicketResponse(saved);
    }

    @Override
    public Map<String, Object> getTicketMetrics() {
        HashMap<String, Object> metrics = new HashMap<String, Object>();
        List<Ticket> allTickets = this.ticketRepo.findAll();
        metrics.put("totalTickets", allTickets.size());
        metrics.put("openTickets", allTickets.stream().filter(t -> t.getStatus() == Ticket.Status.OPEN).count());
        metrics.put("inProgressTickets", allTickets.stream().filter(t -> t.getStatus() == Ticket.Status.IN_PROGRESS).count());
        metrics.put("resolvedTickets", allTickets.stream().filter(t -> t.getStatus() == Ticket.Status.RESOLVED).count());
        metrics.put("closedTickets", allTickets.stream().filter(t -> t.getStatus() == Ticket.Status.CLOSED).count());
        Map<Ticket.Priority, Long> priorityDistribution = allTickets.stream().collect(Collectors.groupingBy(Ticket::getPriority, Collectors.counting()));
        metrics.put("priorityDistribution", priorityDistribution);
        Map<Ticket.Category, Long> categoryDistribution = allTickets.stream().collect(Collectors.groupingBy(Ticket::getCategory, Collectors.counting()));
        metrics.put("categoryDistribution", categoryDistribution);
        double avgResolutionTime = allTickets.stream().filter(t -> t.getResolvedAt() != null && t.getCreatedAt() != null).mapToLong(t -> ChronoUnit.HOURS.between(t.getCreatedAt(), t.getResolvedAt())).average().orElse(0.0);
        metrics.put("averageResolutionTime", avgResolutionTime);
        return metrics;
    }

    @Override
    public List<Ticket> getOverdueTickets() {
        return this.ticketRepo.findByDueDateBeforeAndStatusNot(LocalDateTime.now(), Ticket.Status.RESOLVED);
    }

    @Override
    public List<Ticket> getSlaBreachingTickets() {
        return this.ticketRepo.findBySlaBreachTimeBeforeAndStatusNot(LocalDateTime.now(), Ticket.Status.RESOLVED);
    }

    @Override
    public List<TicketResponse> searchTickets(String query, Ticket.Status status, Ticket.Priority priority) {
        List<Ticket> tickets = this.ticketRepo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
        if (status != null) {
            tickets = tickets.stream().filter(t -> t.getStatus() == status).collect(Collectors.toList());
        }
        if (priority != null) {
            tickets = tickets.stream().filter(t -> t.getPriority() == priority).collect(Collectors.toList());
        }
        return tickets.stream().map(this::buildTicketResponse).collect(Collectors.toList());
    }

    private TicketResponse buildTicketResponse(Ticket ticket) {
        boolean slaBreached = ticket.getSlaBreachTime() != null && ticket.getSlaBreachTime().isBefore(LocalDateTime.now());
        return TicketResponse.builder().id(ticket.getId()).title(ticket.getTitle()).description(ticket.getDescription()).priority(ticket.getPriority()).status(ticket.getStatus()).category(ticket.getCategory()).createdBy(ticket.getCreatedBy() != null ? ticket.getCreatedBy().getName() : null).assignedTo(ticket.getAssignedTo() != null ? ticket.getAssignedTo().getName() : null).createdAt(ticket.getCreatedAt()).updatedAt(ticket.getUpdatedAt()).dueDate(ticket.getDueDate()).resolvedAt(ticket.getResolvedAt()).estimatedResolutionHours(ticket.getEstimatedResolutionHours()).actualResolutionHours(ticket.getActualResolutionHours()).resolutionNotes(ticket.getResolutionNotes()).customerSatisfactionRating(ticket.getCustomerSatisfactionRating()).slaBreached(slaBreached).slaBreachTime(ticket.getSlaBreachTime()).build();
    }
}

