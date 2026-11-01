/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.NotBlank
 *  jakarta.validation.constraints.NotNull
 *  lombok.Generated
 */
package com.nec.dto;

import com.nec.model.Ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Generated;

public class TicketRequest {
    @NotBlank
    private String title;
    private String description;
    @NotNull
    private Ticket.Priority priority;
    @NotNull
    private Ticket.Category category;
    private Long createdByUserId;
    private Long assignedToUserId;
    private LocalDateTime dueDate;
    private Integer estimatedResolutionHours;
    private String resolutionNotes;

    @Generated
    public String getTitle() {
        return this.title;
    }

    @Generated
    public String getDescription() {
        return this.description;
    }

    @Generated
    public Ticket.Priority getPriority() {
        return this.priority;
    }

    @Generated
    public Ticket.Category getCategory() {
        return this.category;
    }

    @Generated
    public Long getCreatedByUserId() {
        return this.createdByUserId;
    }

    @Generated
    public Long getAssignedToUserId() {
        return this.assignedToUserId;
    }

    @Generated
    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    @Generated
    public Integer getEstimatedResolutionHours() {
        return this.estimatedResolutionHours;
    }

    @Generated
    public String getResolutionNotes() {
        return this.resolutionNotes;
    }

    @Generated
    public void setTitle(String title) {
        this.title = title;
    }

    @Generated
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated
    public void setPriority(Ticket.Priority priority) {
        this.priority = priority;
    }

    @Generated
    public void setCategory(Ticket.Category category) {
        this.category = category;
    }

    @Generated
    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    @Generated
    public void setAssignedToUserId(Long assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    @Generated
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Generated
    public void setEstimatedResolutionHours(Integer estimatedResolutionHours) {
        this.estimatedResolutionHours = estimatedResolutionHours;
    }

    @Generated
    public void setResolutionNotes(String resolutionNotes) {
        this.resolutionNotes = resolutionNotes;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TicketRequest)) {
            return false;
        }
        TicketRequest other = (TicketRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$createdByUserId = this.getCreatedByUserId();
        Long other$createdByUserId = other.getCreatedByUserId();
        if (this$createdByUserId == null ? other$createdByUserId != null : !((Object)this$createdByUserId).equals(other$createdByUserId)) {
            return false;
        }
        Long this$assignedToUserId = this.getAssignedToUserId();
        Long other$assignedToUserId = other.getAssignedToUserId();
        if (this$assignedToUserId == null ? other$assignedToUserId != null : !((Object)this$assignedToUserId).equals(other$assignedToUserId)) {
            return false;
        }
        Integer this$estimatedResolutionHours = this.getEstimatedResolutionHours();
        Integer other$estimatedResolutionHours = other.getEstimatedResolutionHours();
        if (this$estimatedResolutionHours == null ? other$estimatedResolutionHours != null : !((Object)this$estimatedResolutionHours).equals(other$estimatedResolutionHours)) {
            return false;
        }
        String this$title = this.getTitle();
        String other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        Ticket.Priority this$priority = this.getPriority();
        Ticket.Priority other$priority = other.getPriority();
        if (this$priority == null ? other$priority != null : !((Object)((Object)this$priority)).equals((Object)other$priority)) {
            return false;
        }
        Ticket.Category this$category = this.getCategory();
        Ticket.Category other$category = other.getCategory();
        if (this$category == null ? other$category != null : !((Object)((Object)this$category)).equals((Object)other$category)) {
            return false;
        }
        LocalDateTime this$dueDate = this.getDueDate();
        LocalDateTime other$dueDate = other.getDueDate();
        if (this$dueDate == null ? other$dueDate != null : !((Object)this$dueDate).equals(other$dueDate)) {
            return false;
        }
        String this$resolutionNotes = this.getResolutionNotes();
        String other$resolutionNotes = other.getResolutionNotes();
        return !(this$resolutionNotes == null ? other$resolutionNotes != null : !this$resolutionNotes.equals(other$resolutionNotes));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof TicketRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $createdByUserId = this.getCreatedByUserId();
        result = result * 59 + ($createdByUserId == null ? 43 : ((Object)$createdByUserId).hashCode());
        Long $assignedToUserId = this.getAssignedToUserId();
        result = result * 59 + ($assignedToUserId == null ? 43 : ((Object)$assignedToUserId).hashCode());
        Integer $estimatedResolutionHours = this.getEstimatedResolutionHours();
        result = result * 59 + ($estimatedResolutionHours == null ? 43 : ((Object)$estimatedResolutionHours).hashCode());
        String $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        Ticket.Priority $priority = this.getPriority();
        result = result * 59 + ($priority == null ? 43 : ((Object)((Object)$priority)).hashCode());
        Ticket.Category $category = this.getCategory();
        result = result * 59 + ($category == null ? 43 : ((Object)((Object)$category)).hashCode());
        LocalDateTime $dueDate = this.getDueDate();
        result = result * 59 + ($dueDate == null ? 43 : ((Object)$dueDate).hashCode());
        String $resolutionNotes = this.getResolutionNotes();
        result = result * 59 + ($resolutionNotes == null ? 43 : $resolutionNotes.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "TicketRequest(title=" + this.getTitle() + ", description=" + this.getDescription() + ", priority=" + String.valueOf((Object)this.getPriority()) + ", category=" + String.valueOf((Object)this.getCategory()) + ", createdByUserId=" + String.valueOf(this.getCreatedByUserId()) + ", assignedToUserId=" + String.valueOf(this.getAssignedToUserId()) + ", dueDate=" + String.valueOf(this.getDueDate()) + ", estimatedResolutionHours=" + String.valueOf(this.getEstimatedResolutionHours()) + ", resolutionNotes=" + this.getResolutionNotes() + ")";
    }

    @Generated
    public TicketRequest() {
    }
}

