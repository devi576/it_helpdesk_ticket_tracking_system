/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.EnumType
 *  jakarta.persistence.Enumerated
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.JoinColumn
 *  jakarta.persistence.ManyToOne
 *  jakarta.persistence.PrePersist
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.nec.model;

import com.nec.model.Ticket;
import com.nec.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Generated;

@Entity
@Table(name="ticket_assignments")
public class TicketAssignment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="ticket_id", nullable=false)
    private Ticket ticket;
    @ManyToOne
    @JoinColumn(name="assigned_to", nullable=false)
    private User assignedTo;
    @ManyToOne
    @JoinColumn(name="assigned_by", nullable=false)
    private User assignedBy;
    @Column(name="assigned_at", nullable=false)
    private LocalDateTime assignedAt;
    @Column(name="assigned_until")
    private LocalDateTime assignedUntil;
    @Column(name="assignment_reason", columnDefinition="TEXT")
    private String assignmentReason;
    @Column(name="estimated_resolution_hours")
    private Integer estimatedResolutionHours;
    @Enumerated(value=EnumType.STRING)
    @Column(name="assignment_status")
    private AssignmentStatus status;
    @Column(name="notes", columnDefinition="TEXT")
    private String notes;

    @PrePersist
    protected void onCreate() {
        this.assignedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = AssignmentStatus.ACTIVE;
        }
    }

    @Generated
    public static TicketAssignmentBuilder builder() {
        return new TicketAssignmentBuilder();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Ticket getTicket() {
        return this.ticket;
    }

    @Generated
    public User getAssignedTo() {
        return this.assignedTo;
    }

    @Generated
    public User getAssignedBy() {
        return this.assignedBy;
    }

    @Generated
    public LocalDateTime getAssignedAt() {
        return this.assignedAt;
    }

    @Generated
    public LocalDateTime getAssignedUntil() {
        return this.assignedUntil;
    }

    @Generated
    public String getAssignmentReason() {
        return this.assignmentReason;
    }

    @Generated
    public Integer getEstimatedResolutionHours() {
        return this.estimatedResolutionHours;
    }

    @Generated
    public AssignmentStatus getStatus() {
        return this.status;
    }

    @Generated
    public String getNotes() {
        return this.notes;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Generated
    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Generated
    public void setAssignedBy(User assignedBy) {
        this.assignedBy = assignedBy;
    }

    @Generated
    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    @Generated
    public void setAssignedUntil(LocalDateTime assignedUntil) {
        this.assignedUntil = assignedUntil;
    }

    @Generated
    public void setAssignmentReason(String assignmentReason) {
        this.assignmentReason = assignmentReason;
    }

    @Generated
    public void setEstimatedResolutionHours(Integer estimatedResolutionHours) {
        this.estimatedResolutionHours = estimatedResolutionHours;
    }

    @Generated
    public void setStatus(AssignmentStatus status) {
        this.status = status;
    }

    @Generated
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TicketAssignment)) {
            return false;
        }
        TicketAssignment other = (TicketAssignment)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Integer this$estimatedResolutionHours = this.getEstimatedResolutionHours();
        Integer other$estimatedResolutionHours = other.getEstimatedResolutionHours();
        if (this$estimatedResolutionHours == null ? other$estimatedResolutionHours != null : !((Object)this$estimatedResolutionHours).equals(other$estimatedResolutionHours)) {
            return false;
        }
        Ticket this$ticket = this.getTicket();
        Ticket other$ticket = other.getTicket();
        if (this$ticket == null ? other$ticket != null : !((Object)this$ticket).equals(other$ticket)) {
            return false;
        }
        User this$assignedTo = this.getAssignedTo();
        User other$assignedTo = other.getAssignedTo();
        if (this$assignedTo == null ? other$assignedTo != null : !((Object)this$assignedTo).equals(other$assignedTo)) {
            return false;
        }
        User this$assignedBy = this.getAssignedBy();
        User other$assignedBy = other.getAssignedBy();
        if (this$assignedBy == null ? other$assignedBy != null : !((Object)this$assignedBy).equals(other$assignedBy)) {
            return false;
        }
        LocalDateTime this$assignedAt = this.getAssignedAt();
        LocalDateTime other$assignedAt = other.getAssignedAt();
        if (this$assignedAt == null ? other$assignedAt != null : !((Object)this$assignedAt).equals(other$assignedAt)) {
            return false;
        }
        LocalDateTime this$assignedUntil = this.getAssignedUntil();
        LocalDateTime other$assignedUntil = other.getAssignedUntil();
        if (this$assignedUntil == null ? other$assignedUntil != null : !((Object)this$assignedUntil).equals(other$assignedUntil)) {
            return false;
        }
        String this$assignmentReason = this.getAssignmentReason();
        String other$assignmentReason = other.getAssignmentReason();
        if (this$assignmentReason == null ? other$assignmentReason != null : !this$assignmentReason.equals(other$assignmentReason)) {
            return false;
        }
        AssignmentStatus this$status = this.getStatus();
        AssignmentStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)((Object)this$status)).equals((Object)other$status)) {
            return false;
        }
        String this$notes = this.getNotes();
        String other$notes = other.getNotes();
        return !(this$notes == null ? other$notes != null : !this$notes.equals(other$notes));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof TicketAssignment;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $estimatedResolutionHours = this.getEstimatedResolutionHours();
        result = result * 59 + ($estimatedResolutionHours == null ? 43 : ((Object)$estimatedResolutionHours).hashCode());
        Ticket $ticket = this.getTicket();
        result = result * 59 + ($ticket == null ? 43 : ((Object)$ticket).hashCode());
        User $assignedTo = this.getAssignedTo();
        result = result * 59 + ($assignedTo == null ? 43 : ((Object)$assignedTo).hashCode());
        User $assignedBy = this.getAssignedBy();
        result = result * 59 + ($assignedBy == null ? 43 : ((Object)$assignedBy).hashCode());
        LocalDateTime $assignedAt = this.getAssignedAt();
        result = result * 59 + ($assignedAt == null ? 43 : ((Object)$assignedAt).hashCode());
        LocalDateTime $assignedUntil = this.getAssignedUntil();
        result = result * 59 + ($assignedUntil == null ? 43 : ((Object)$assignedUntil).hashCode());
        String $assignmentReason = this.getAssignmentReason();
        result = result * 59 + ($assignmentReason == null ? 43 : $assignmentReason.hashCode());
        AssignmentStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        String $notes = this.getNotes();
        result = result * 59 + ($notes == null ? 43 : $notes.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "TicketAssignment(id=" + String.valueOf(this.getId()) + ", ticket=" + String.valueOf(this.getTicket()) + ", assignedTo=" + String.valueOf(this.getAssignedTo()) + ", assignedBy=" + String.valueOf(this.getAssignedBy()) + ", assignedAt=" + String.valueOf(this.getAssignedAt()) + ", assignedUntil=" + String.valueOf(this.getAssignedUntil()) + ", assignmentReason=" + this.getAssignmentReason() + ", estimatedResolutionHours=" + String.valueOf(this.getEstimatedResolutionHours()) + ", status=" + String.valueOf((Object)this.getStatus()) + ", notes=" + this.getNotes() + ")";
    }

    @Generated
    public TicketAssignment() {
    }

    @Generated
    public TicketAssignment(Long id, Ticket ticket, User assignedTo, User assignedBy, LocalDateTime assignedAt, LocalDateTime assignedUntil, String assignmentReason, Integer estimatedResolutionHours, AssignmentStatus status, String notes) {
        this.id = id;
        this.ticket = ticket;
        this.assignedTo = assignedTo;
        this.assignedBy = assignedBy;
        this.assignedAt = assignedAt;
        this.assignedUntil = assignedUntil;
        this.assignmentReason = assignmentReason;
        this.estimatedResolutionHours = estimatedResolutionHours;
        this.status = status;
        this.notes = notes;
    }

    public static enum AssignmentStatus {
        ACTIVE,
        COMPLETED,
        REASSIGNED,
        CANCELLED;

    }

    @Generated
    public static class TicketAssignmentBuilder {
        @Generated
        private Long id;
        @Generated
        private Ticket ticket;
        @Generated
        private User assignedTo;
        @Generated
        private User assignedBy;
        @Generated
        private LocalDateTime assignedAt;
        @Generated
        private LocalDateTime assignedUntil;
        @Generated
        private String assignmentReason;
        @Generated
        private Integer estimatedResolutionHours;
        @Generated
        private AssignmentStatus status;
        @Generated
        private String notes;

        @Generated
        TicketAssignmentBuilder() {
        }

        @Generated
        public TicketAssignmentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Generated
        public TicketAssignmentBuilder ticket(Ticket ticket) {
            this.ticket = ticket;
            return this;
        }

        @Generated
        public TicketAssignmentBuilder assignedTo(User assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        @Generated
        public TicketAssignmentBuilder assignedBy(User assignedBy) {
            this.assignedBy = assignedBy;
            return this;
        }

        @Generated
        public TicketAssignmentBuilder assignedAt(LocalDateTime assignedAt) {
            this.assignedAt = assignedAt;
            return this;
        }

        @Generated
        public TicketAssignmentBuilder assignedUntil(LocalDateTime assignedUntil) {
            this.assignedUntil = assignedUntil;
            return this;
        }

        @Generated
        public TicketAssignmentBuilder assignmentReason(String assignmentReason) {
            this.assignmentReason = assignmentReason;
            return this;
        }

        @Generated
        public TicketAssignmentBuilder estimatedResolutionHours(Integer estimatedResolutionHours) {
            this.estimatedResolutionHours = estimatedResolutionHours;
            return this;
        }

        @Generated
        public TicketAssignmentBuilder status(AssignmentStatus status) {
            this.status = status;
            return this;
        }

        @Generated
        public TicketAssignmentBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        @Generated
        public TicketAssignment build() {
            return new TicketAssignment(this.id, this.ticket, this.assignedTo, this.assignedBy, this.assignedAt, this.assignedUntil, this.assignmentReason, this.estimatedResolutionHours, this.status, this.notes);
        }

        @Generated
        public String toString() {
            return "TicketAssignment.TicketAssignmentBuilder(id=" + String.valueOf(this.id) + ", ticket=" + String.valueOf(this.ticket) + ", assignedTo=" + String.valueOf(this.assignedTo) + ", assignedBy=" + String.valueOf(this.assignedBy) + ", assignedAt=" + String.valueOf(this.assignedAt) + ", assignedUntil=" + String.valueOf(this.assignedUntil) + ", assignmentReason=" + this.assignmentReason + ", estimatedResolutionHours=" + String.valueOf(this.estimatedResolutionHours) + ", status=" + String.valueOf((Object)this.status) + ", notes=" + this.notes + ")";
        }
    }
}

