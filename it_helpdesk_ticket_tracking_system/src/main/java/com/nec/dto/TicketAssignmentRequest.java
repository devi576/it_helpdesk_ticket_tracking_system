/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.NotNull
 *  lombok.Generated
 */
package com.nec.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Generated;

public class TicketAssignmentRequest {
    @NotNull
    private Long ticketId;
    @NotNull
    private Long assignedToUserId;
    private Long assignedByUserId;
    private LocalDateTime assignedUntil;
    private String assignmentReason;
    private Integer estimatedResolutionHours;
    private String notes;

    @Generated
    public Long getTicketId() {
        return this.ticketId;
    }

    @Generated
    public Long getAssignedToUserId() {
        return this.assignedToUserId;
    }

    @Generated
    public Long getAssignedByUserId() {
        return this.assignedByUserId;
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
    public String getNotes() {
        return this.notes;
    }

    @Generated
    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Generated
    public void setAssignedToUserId(Long assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    @Generated
    public void setAssignedByUserId(Long assignedByUserId) {
        this.assignedByUserId = assignedByUserId;
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
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TicketAssignmentRequest)) {
            return false;
        }
        TicketAssignmentRequest other = (TicketAssignmentRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$ticketId = this.getTicketId();
        Long other$ticketId = other.getTicketId();
        if (this$ticketId == null ? other$ticketId != null : !((Object)this$ticketId).equals(other$ticketId)) {
            return false;
        }
        Long this$assignedToUserId = this.getAssignedToUserId();
        Long other$assignedToUserId = other.getAssignedToUserId();
        if (this$assignedToUserId == null ? other$assignedToUserId != null : !((Object)this$assignedToUserId).equals(other$assignedToUserId)) {
            return false;
        }
        Long this$assignedByUserId = this.getAssignedByUserId();
        Long other$assignedByUserId = other.getAssignedByUserId();
        if (this$assignedByUserId == null ? other$assignedByUserId != null : !((Object)this$assignedByUserId).equals(other$assignedByUserId)) {
            return false;
        }
        Integer this$estimatedResolutionHours = this.getEstimatedResolutionHours();
        Integer other$estimatedResolutionHours = other.getEstimatedResolutionHours();
        if (this$estimatedResolutionHours == null ? other$estimatedResolutionHours != null : !((Object)this$estimatedResolutionHours).equals(other$estimatedResolutionHours)) {
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
        String this$notes = this.getNotes();
        String other$notes = other.getNotes();
        return !(this$notes == null ? other$notes != null : !this$notes.equals(other$notes));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof TicketAssignmentRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $ticketId = this.getTicketId();
        result = result * 59 + ($ticketId == null ? 43 : ((Object)$ticketId).hashCode());
        Long $assignedToUserId = this.getAssignedToUserId();
        result = result * 59 + ($assignedToUserId == null ? 43 : ((Object)$assignedToUserId).hashCode());
        Long $assignedByUserId = this.getAssignedByUserId();
        result = result * 59 + ($assignedByUserId == null ? 43 : ((Object)$assignedByUserId).hashCode());
        Integer $estimatedResolutionHours = this.getEstimatedResolutionHours();
        result = result * 59 + ($estimatedResolutionHours == null ? 43 : ((Object)$estimatedResolutionHours).hashCode());
        LocalDateTime $assignedUntil = this.getAssignedUntil();
        result = result * 59 + ($assignedUntil == null ? 43 : ((Object)$assignedUntil).hashCode());
        String $assignmentReason = this.getAssignmentReason();
        result = result * 59 + ($assignmentReason == null ? 43 : $assignmentReason.hashCode());
        String $notes = this.getNotes();
        result = result * 59 + ($notes == null ? 43 : $notes.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "TicketAssignmentRequest(ticketId=" + String.valueOf(this.getTicketId()) + ", assignedToUserId=" + String.valueOf(this.getAssignedToUserId()) + ", assignedByUserId=" + String.valueOf(this.getAssignedByUserId()) + ", assignedUntil=" + String.valueOf(this.getAssignedUntil()) + ", assignmentReason=" + this.getAssignmentReason() + ", estimatedResolutionHours=" + String.valueOf(this.getEstimatedResolutionHours()) + ", notes=" + this.getNotes() + ")";
    }

    @Generated
    public TicketAssignmentRequest() {
    }
}

