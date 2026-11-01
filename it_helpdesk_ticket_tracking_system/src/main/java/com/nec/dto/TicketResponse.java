/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.nec.dto;

import com.nec.model.Ticket;
import java.time.LocalDateTime;
import lombok.Generated;

public class TicketResponse {
    private Long id;
    private String title;
    private String description;
    private Ticket.Priority priority;
    private Ticket.Status status;
    private Ticket.Category category;
    private String createdBy;
    private String assignedTo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;
    private LocalDateTime resolvedAt;
    private Integer estimatedResolutionHours;
    private Integer actualResolutionHours;
    private String resolutionNotes;
    private Integer customerSatisfactionRating;
    private boolean slaBreached;
    private LocalDateTime slaBreachTime;

    @Generated
    public static TicketResponseBuilder builder() {
        return new TicketResponseBuilder();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

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
    public Ticket.Status getStatus() {
        return this.status;
    }

    @Generated
    public Ticket.Category getCategory() {
        return this.category;
    }

    @Generated
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Generated
    public String getAssignedTo() {
        return this.assignedTo;
    }

    @Generated
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Generated
    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    @Generated
    public LocalDateTime getResolvedAt() {
        return this.resolvedAt;
    }

    @Generated
    public Integer getEstimatedResolutionHours() {
        return this.estimatedResolutionHours;
    }

    @Generated
    public Integer getActualResolutionHours() {
        return this.actualResolutionHours;
    }

    @Generated
    public String getResolutionNotes() {
        return this.resolutionNotes;
    }

    @Generated
    public Integer getCustomerSatisfactionRating() {
        return this.customerSatisfactionRating;
    }

    @Generated
    public boolean isSlaBreached() {
        return this.slaBreached;
    }

    @Generated
    public LocalDateTime getSlaBreachTime() {
        return this.slaBreachTime;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
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
    public void setStatus(Ticket.Status status) {
        this.status = status;
    }

    @Generated
    public void setCategory(Ticket.Category category) {
        this.category = category;
    }

    @Generated
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Generated
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Generated
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Generated
    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    @Generated
    public void setEstimatedResolutionHours(Integer estimatedResolutionHours) {
        this.estimatedResolutionHours = estimatedResolutionHours;
    }

    @Generated
    public void setActualResolutionHours(Integer actualResolutionHours) {
        this.actualResolutionHours = actualResolutionHours;
    }

    @Generated
    public void setResolutionNotes(String resolutionNotes) {
        this.resolutionNotes = resolutionNotes;
    }

    @Generated
    public void setCustomerSatisfactionRating(Integer customerSatisfactionRating) {
        this.customerSatisfactionRating = customerSatisfactionRating;
    }

    @Generated
    public void setSlaBreached(boolean slaBreached) {
        this.slaBreached = slaBreached;
    }

    @Generated
    public void setSlaBreachTime(LocalDateTime slaBreachTime) {
        this.slaBreachTime = slaBreachTime;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TicketResponse)) {
            return false;
        }
        TicketResponse other = (TicketResponse)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.isSlaBreached() != other.isSlaBreached()) {
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
        Integer this$actualResolutionHours = this.getActualResolutionHours();
        Integer other$actualResolutionHours = other.getActualResolutionHours();
        if (this$actualResolutionHours == null ? other$actualResolutionHours != null : !((Object)this$actualResolutionHours).equals(other$actualResolutionHours)) {
            return false;
        }
        Integer this$customerSatisfactionRating = this.getCustomerSatisfactionRating();
        Integer other$customerSatisfactionRating = other.getCustomerSatisfactionRating();
        if (this$customerSatisfactionRating == null ? other$customerSatisfactionRating != null : !((Object)this$customerSatisfactionRating).equals(other$customerSatisfactionRating)) {
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
        Ticket.Status this$status = this.getStatus();
        Ticket.Status other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)((Object)this$status)).equals((Object)other$status)) {
            return false;
        }
        Ticket.Category this$category = this.getCategory();
        Ticket.Category other$category = other.getCategory();
        if (this$category == null ? other$category != null : !((Object)((Object)this$category)).equals((Object)other$category)) {
            return false;
        }
        String this$createdBy = this.getCreatedBy();
        String other$createdBy = other.getCreatedBy();
        if (this$createdBy == null ? other$createdBy != null : !this$createdBy.equals(other$createdBy)) {
            return false;
        }
        String this$assignedTo = this.getAssignedTo();
        String other$assignedTo = other.getAssignedTo();
        if (this$assignedTo == null ? other$assignedTo != null : !this$assignedTo.equals(other$assignedTo)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !((Object)this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$updatedAt = this.getUpdatedAt();
        LocalDateTime other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !((Object)this$updatedAt).equals(other$updatedAt)) {
            return false;
        }
        LocalDateTime this$dueDate = this.getDueDate();
        LocalDateTime other$dueDate = other.getDueDate();
        if (this$dueDate == null ? other$dueDate != null : !((Object)this$dueDate).equals(other$dueDate)) {
            return false;
        }
        LocalDateTime this$resolvedAt = this.getResolvedAt();
        LocalDateTime other$resolvedAt = other.getResolvedAt();
        if (this$resolvedAt == null ? other$resolvedAt != null : !((Object)this$resolvedAt).equals(other$resolvedAt)) {
            return false;
        }
        String this$resolutionNotes = this.getResolutionNotes();
        String other$resolutionNotes = other.getResolutionNotes();
        if (this$resolutionNotes == null ? other$resolutionNotes != null : !this$resolutionNotes.equals(other$resolutionNotes)) {
            return false;
        }
        LocalDateTime this$slaBreachTime = this.getSlaBreachTime();
        LocalDateTime other$slaBreachTime = other.getSlaBreachTime();
        return !(this$slaBreachTime == null ? other$slaBreachTime != null : !((Object)this$slaBreachTime).equals(other$slaBreachTime));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof TicketResponse;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isSlaBreached() ? 79 : 97);
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $estimatedResolutionHours = this.getEstimatedResolutionHours();
        result = result * 59 + ($estimatedResolutionHours == null ? 43 : ((Object)$estimatedResolutionHours).hashCode());
        Integer $actualResolutionHours = this.getActualResolutionHours();
        result = result * 59 + ($actualResolutionHours == null ? 43 : ((Object)$actualResolutionHours).hashCode());
        Integer $customerSatisfactionRating = this.getCustomerSatisfactionRating();
        result = result * 59 + ($customerSatisfactionRating == null ? 43 : ((Object)$customerSatisfactionRating).hashCode());
        String $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        Ticket.Priority $priority = this.getPriority();
        result = result * 59 + ($priority == null ? 43 : ((Object)((Object)$priority)).hashCode());
        Ticket.Status $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        Ticket.Category $category = this.getCategory();
        result = result * 59 + ($category == null ? 43 : ((Object)((Object)$category)).hashCode());
        String $createdBy = this.getCreatedBy();
        result = result * 59 + ($createdBy == null ? 43 : $createdBy.hashCode());
        String $assignedTo = this.getAssignedTo();
        result = result * 59 + ($assignedTo == null ? 43 : $assignedTo.hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        LocalDateTime $dueDate = this.getDueDate();
        result = result * 59 + ($dueDate == null ? 43 : ((Object)$dueDate).hashCode());
        LocalDateTime $resolvedAt = this.getResolvedAt();
        result = result * 59 + ($resolvedAt == null ? 43 : ((Object)$resolvedAt).hashCode());
        String $resolutionNotes = this.getResolutionNotes();
        result = result * 59 + ($resolutionNotes == null ? 43 : $resolutionNotes.hashCode());
        LocalDateTime $slaBreachTime = this.getSlaBreachTime();
        result = result * 59 + ($slaBreachTime == null ? 43 : ((Object)$slaBreachTime).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "TicketResponse(id=" + String.valueOf(this.getId()) + ", title=" + this.getTitle() + ", description=" + this.getDescription() + ", priority=" + String.valueOf((Object)this.getPriority()) + ", status=" + String.valueOf((Object)this.getStatus()) + ", category=" + String.valueOf((Object)this.getCategory()) + ", createdBy=" + this.getCreatedBy() + ", assignedTo=" + this.getAssignedTo() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ", dueDate=" + String.valueOf(this.getDueDate()) + ", resolvedAt=" + String.valueOf(this.getResolvedAt()) + ", estimatedResolutionHours=" + String.valueOf(this.getEstimatedResolutionHours()) + ", actualResolutionHours=" + String.valueOf(this.getActualResolutionHours()) + ", resolutionNotes=" + this.getResolutionNotes() + ", customerSatisfactionRating=" + String.valueOf(this.getCustomerSatisfactionRating()) + ", slaBreached=" + this.isSlaBreached() + ", slaBreachTime=" + String.valueOf(this.getSlaBreachTime()) + ")";
    }

    @Generated
    public TicketResponse(Long id, String title, String description, Ticket.Priority priority, Ticket.Status status, Ticket.Category category, String createdBy, String assignedTo, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime dueDate, LocalDateTime resolvedAt, Integer estimatedResolutionHours, Integer actualResolutionHours, String resolutionNotes, Integer customerSatisfactionRating, boolean slaBreached, LocalDateTime slaBreachTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dueDate = dueDate;
        this.resolvedAt = resolvedAt;
        this.estimatedResolutionHours = estimatedResolutionHours;
        this.actualResolutionHours = actualResolutionHours;
        this.resolutionNotes = resolutionNotes;
        this.customerSatisfactionRating = customerSatisfactionRating;
        this.slaBreached = slaBreached;
        this.slaBreachTime = slaBreachTime;
    }

    @Generated
    public static class TicketResponseBuilder {
        @Generated
        private Long id;
        @Generated
        private String title;
        @Generated
        private String description;
        @Generated
        private Ticket.Priority priority;
        @Generated
        private Ticket.Status status;
        @Generated
        private Ticket.Category category;
        @Generated
        private String createdBy;
        @Generated
        private String assignedTo;
        @Generated
        private LocalDateTime createdAt;
        @Generated
        private LocalDateTime updatedAt;
        @Generated
        private LocalDateTime dueDate;
        @Generated
        private LocalDateTime resolvedAt;
        @Generated
        private Integer estimatedResolutionHours;
        @Generated
        private Integer actualResolutionHours;
        @Generated
        private String resolutionNotes;
        @Generated
        private Integer customerSatisfactionRating;
        @Generated
        private boolean slaBreached;
        @Generated
        private LocalDateTime slaBreachTime;

        @Generated
        TicketResponseBuilder() {
        }

        @Generated
        public TicketResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Generated
        public TicketResponseBuilder title(String title) {
            this.title = title;
            return this;
        }

        @Generated
        public TicketResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        @Generated
        public TicketResponseBuilder priority(Ticket.Priority priority) {
            this.priority = priority;
            return this;
        }

        @Generated
        public TicketResponseBuilder status(Ticket.Status status) {
            this.status = status;
            return this;
        }

        @Generated
        public TicketResponseBuilder category(Ticket.Category category) {
            this.category = category;
            return this;
        }

        @Generated
        public TicketResponseBuilder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        @Generated
        public TicketResponseBuilder assignedTo(String assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        @Generated
        public TicketResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @Generated
        public TicketResponseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        @Generated
        public TicketResponseBuilder dueDate(LocalDateTime dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        @Generated
        public TicketResponseBuilder resolvedAt(LocalDateTime resolvedAt) {
            this.resolvedAt = resolvedAt;
            return this;
        }

        @Generated
        public TicketResponseBuilder estimatedResolutionHours(Integer estimatedResolutionHours) {
            this.estimatedResolutionHours = estimatedResolutionHours;
            return this;
        }

        @Generated
        public TicketResponseBuilder actualResolutionHours(Integer actualResolutionHours) {
            this.actualResolutionHours = actualResolutionHours;
            return this;
        }

        @Generated
        public TicketResponseBuilder resolutionNotes(String resolutionNotes) {
            this.resolutionNotes = resolutionNotes;
            return this;
        }

        @Generated
        public TicketResponseBuilder customerSatisfactionRating(Integer customerSatisfactionRating) {
            this.customerSatisfactionRating = customerSatisfactionRating;
            return this;
        }

        @Generated
        public TicketResponseBuilder slaBreached(boolean slaBreached) {
            this.slaBreached = slaBreached;
            return this;
        }

        @Generated
        public TicketResponseBuilder slaBreachTime(LocalDateTime slaBreachTime) {
            this.slaBreachTime = slaBreachTime;
            return this;
        }

        @Generated
        public TicketResponse build() {
            return new TicketResponse(this.id, this.title, this.description, this.priority, this.status, this.category, this.createdBy, this.assignedTo, this.createdAt, this.updatedAt, this.dueDate, this.resolvedAt, this.estimatedResolutionHours, this.actualResolutionHours, this.resolutionNotes, this.customerSatisfactionRating, this.slaBreached, this.slaBreachTime);
        }

        @Generated
        public String toString() {
            return "TicketResponse.TicketResponseBuilder(id=" + String.valueOf(this.id) + ", title=" + this.title + ", description=" + this.description + ", priority=" + String.valueOf((Object)this.priority) + ", status=" + String.valueOf((Object)this.status) + ", category=" + String.valueOf((Object)this.category) + ", createdBy=" + this.createdBy + ", assignedTo=" + this.assignedTo + ", createdAt=" + String.valueOf(this.createdAt) + ", updatedAt=" + String.valueOf(this.updatedAt) + ", dueDate=" + String.valueOf(this.dueDate) + ", resolvedAt=" + String.valueOf(this.resolvedAt) + ", estimatedResolutionHours=" + String.valueOf(this.estimatedResolutionHours) + ", actualResolutionHours=" + String.valueOf(this.actualResolutionHours) + ", resolutionNotes=" + this.resolutionNotes + ", customerSatisfactionRating=" + String.valueOf(this.customerSatisfactionRating) + ", slaBreached=" + this.slaBreached + ", slaBreachTime=" + String.valueOf(this.slaBreachTime) + ")";
        }
    }
}

