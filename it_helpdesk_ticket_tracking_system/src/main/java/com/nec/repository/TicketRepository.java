/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.Query
 *  org.springframework.data.repository.query.Param
 */
package com.nec.repository;

import com.nec.model.Ticket;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository
extends JpaRepository<Ticket, Long> {
    public List<Ticket> findByStatus(Ticket.Status var1);

    public List<Ticket> findByPriority(Ticket.Priority var1);

    public List<Ticket> findByCreatedById(Long var1);

    public List<Ticket> findByAssignedToId(Long var1);

    public List<Ticket> findByDueDateBeforeAndStatusNot(LocalDateTime var1, Ticket.Status var2);

    public List<Ticket> findBySlaBreachTimeBeforeAndStatusNot(LocalDateTime var1, Ticket.Status var2);

    @Query(value="SELECT t FROM Ticket t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    public List<Ticket> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(@Param(value="query") String var1, @Param(value="query") String var2);

    public List<Ticket> findByCategory(Ticket.Category var1);

    public List<Ticket> findByCreatedAtBetween(LocalDateTime var1, LocalDateTime var2);

    public List<Ticket> findByStatusAndPriority(Ticket.Status var1, Ticket.Priority var2);

    public long countByStatus(Ticket.Status var1);

    public long countByPriority(Ticket.Priority var1);

    public List<Ticket> findByAssignedToIdAndStatus(Long var1, Ticket.Status var2);

    @Query(value="SELECT t FROM Ticket t WHERE t.priority IN ('HIGH', 'CRITICAL') AND t.status != 'RESOLVED' ORDER BY t.createdAt ASC")
    public List<Ticket> findHighPriorityActiveTickets();

    @Query(value="SELECT t FROM Ticket t WHERE (t.dueDate < :now AND t.status != 'RESOLVED') OR (t.slaBreachTime < :now AND t.status != 'RESOLVED') ORDER BY t.priority DESC, t.createdAt ASC")
    public List<Ticket> findTicketsNeedingAttention(@Param(value="now") LocalDateTime var1);
}

