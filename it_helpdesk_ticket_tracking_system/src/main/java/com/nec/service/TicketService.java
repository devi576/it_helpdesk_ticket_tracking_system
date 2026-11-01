/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Service
 */
package com.nec.service;

import com.nec.dto.TicketAssignmentRequest;
import com.nec.dto.TicketRequest;
import com.nec.dto.TicketResponse;
import com.nec.model.Ticket;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public interface TicketService {
    public TicketResponse create(TicketRequest var1);

    public List<Ticket> list();

    public Ticket get(Long var1);

    public TicketResponse update(Long var1, TicketRequest var2);

    public void delete(Long var1);

    public List<TicketResponse> getTicketsByStatus(Ticket.Status var1);

    public List<TicketResponse> getTicketsByPriority(Ticket.Priority var1);

    public List<TicketResponse> getTicketsByUser(Long var1);

    public List<TicketResponse> getAssignedTickets(Long var1);

    public TicketResponse assignTicket(TicketAssignmentRequest var1);

    public TicketResponse reassignTicket(Long var1, Long var2, String var3);

    public void unassignTicket(Long var1);

    public TicketResponse updateTicketStatus(Long var1, Ticket.Status var2, String var3);

    public TicketResponse resolveTicket(Long var1, String var2, Integer var3);

    public Map<String, Object> getTicketMetrics();

    public List<Ticket> getOverdueTickets();

    public List<Ticket> getSlaBreachingTickets();

    public List<TicketResponse> searchTickets(String var1, Ticket.Status var2, Ticket.Priority var3);
}

