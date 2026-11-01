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
 *  jakarta.persistence.PreUpdate
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.nec.model;

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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Generated;

@Entity
@Table(name="tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    @Column(columnDefinition="TEXT")
    private String description;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false)
    private Priority priority;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false)
    private Status status;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false)
    private Category category;
    @ManyToOne
    @JoinColumn(name="created_by")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name="assigned_to")
    private User assignedTo;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @Column(name="due_date")
    private LocalDateTime dueDate;
    @Column(name="resolved_at")
    private LocalDateTime resolvedAt;
    @Column(name="sla_breach_time")
    private LocalDateTime slaBreachTime;
    @Column(name="estimated_resolution_hours")
    private Integer estimatedResolutionHours;
    @Column(name="actual_resolution_hours")
    private Integer actualResolutionHours;
    @Column(columnDefinition="TEXT")
    private String resolutionNotes;
    @Column(name="customer_satisfaction_rating")
    private Integer customerSatisfactionRating;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = Status.OPEN;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Generated
    public static TicketBuilder builder() {
        return new TicketBuilder();
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
    public Priority getPriority() {
        return this.priority;
    }

    @Generated
    public Status getStatus() {
        return this.status;
    }

    @Generated
    public Category getCategory() {
        return this.category;
    }

    @Generated
    public User getCreatedBy() {
        return this.createdBy;
    }

    @Generated
    public User getAssignedTo() {
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
    public LocalDateTime getSlaBreachTime() {
        return this.slaBreachTime;
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
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Generated
    public void setStatus(Status status) {
        this.status = status;
    }

    @Generated
    public void setCategory(Category category) {
        this.category = category;
    }

    @Generated
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Generated
    public void setAssignedTo(User assignedTo) {
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
    public void setSlaBreachTime(LocalDateTime slaBreachTime) {
        this.slaBreachTime = slaBreachTime;
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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket)o;
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
        Priority this$priority = this.getPriority();
        Priority other$priority = other.getPriority();
        if (this$priority == null ? other$priority != null : !((Object)((Object)this$priority)).equals((Object)other$priority)) {
            return false;
        }
        Status this$status = this.getStatus();
        Status other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)((Object)this$status)).equals((Object)other$status)) {
            return false;
        }
        Category this$category = this.getCategory();
        Category other$category = other.getCategory();
        if (this$category == null ? other$category != null : !((Object)((Object)this$category)).equals((Object)other$category)) {
            return false;
        }
        User this$createdBy = this.getCreatedBy();
        User other$createdBy = other.getCreatedBy();
        if (this$createdBy == null ? other$createdBy != null : !((Object)this$createdBy).equals(other$createdBy)) {
            return false;
        }
        User this$assignedTo = this.getAssignedTo();
        User other$assignedTo = other.getAssignedTo();
        if (this$assignedTo == null ? other$assignedTo != null : !((Object)this$assignedTo).equals(other$assignedTo)) {
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
        LocalDateTime this$slaBreachTime = this.getSlaBreachTime();
        LocalDateTime other$slaBreachTime = other.getSlaBreachTime();
        if (this$slaBreachTime == null ? other$slaBreachTime != null : !((Object)this$slaBreachTime).equals(other$slaBreachTime)) {
            return false;
        }
        String this$resolutionNotes = this.getResolutionNotes();
        String other$resolutionNotes = other.getResolutionNotes();
        return !(this$resolutionNotes == null ? other$resolutionNotes != null : !this$resolutionNotes.equals(other$resolutionNotes));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Ticket;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
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
        Priority $priority = this.getPriority();
        result = result * 59 + ($priority == null ? 43 : ((Object)((Object)$priority)).hashCode());
        Status $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        Category $category = this.getCategory();
        result = result * 59 + ($category == null ? 43 : ((Object)((Object)$category)).hashCode());
        User $createdBy = this.getCreatedBy();
        result = result * 59 + ($createdBy == null ? 43 : ((Object)$createdBy).hashCode());
        User $assignedTo = this.getAssignedTo();
        result = result * 59 + ($assignedTo == null ? 43 : ((Object)$assignedTo).hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        LocalDateTime $dueDate = this.getDueDate();
        result = result * 59 + ($dueDate == null ? 43 : ((Object)$dueDate).hashCode());
        LocalDateTime $resolvedAt = this.getResolvedAt();
        result = result * 59 + ($resolvedAt == null ? 43 : ((Object)$resolvedAt).hashCode());
        LocalDateTime $slaBreachTime = this.getSlaBreachTime();
        result = result * 59 + ($slaBreachTime == null ? 43 : ((Object)$slaBreachTime).hashCode());
        String $resolutionNotes = this.getResolutionNotes();
        result = result * 59 + ($resolutionNotes == null ? 43 : $resolutionNotes.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Ticket(id=" + String.valueOf(this.getId()) + ", title=" + this.getTitle() + ", description=" + this.getDescription() + ", priority=" + String.valueOf((Object)this.getPriority()) + ", status=" + String.valueOf((Object)this.getStatus()) + ", category=" + String.valueOf((Object)this.getCategory()) + ", createdBy=" + String.valueOf(this.getCreatedBy()) + ", assignedTo=" + String.valueOf(this.getAssignedTo()) + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ", dueDate=" + String.valueOf(this.getDueDate()) + ", resolvedAt=" + String.valueOf(this.getResolvedAt()) + ", slaBreachTime=" + String.valueOf(this.getSlaBreachTime()) + ", estimatedResolutionHours=" + String.valueOf(this.getEstimatedResolutionHours()) + ", actualResolutionHours=" + String.valueOf(this.getActualResolutionHours()) + ", resolutionNotes=" + this.getResolutionNotes() + ", customerSatisfactionRating=" + String.valueOf(this.getCustomerSatisfactionRating()) + ")";
    }

    @Generated
    public Ticket() {
    }

    @Generated
    public Ticket(Long id, String title, String description, Priority priority, Status status, Category category, User createdBy, User assignedTo, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime dueDate, LocalDateTime resolvedAt, LocalDateTime slaBreachTime, Integer estimatedResolutionHours, Integer actualResolutionHours, String resolutionNotes, Integer customerSatisfactionRating) {
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
        this.slaBreachTime = slaBreachTime;
        this.estimatedResolutionHours = estimatedResolutionHours;
        this.actualResolutionHours = actualResolutionHours;
        this.resolutionNotes = resolutionNotes;
        this.customerSatisfactionRating = customerSatisfactionRating;
    }

    public static enum Category {
        TECHNICAL_ISSUE,
        BILLING,
        ACCOUNT_ACCESS,
        FEATURE_REQUEST,
        BUG_REPORT,
        GENERAL_INQUIRY;

    }

    public static enum Priority {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL;

    }

    public static enum Status {
        OPEN,
        IN_PROGRESS,
        PENDING_CUSTOMER,
        PENDING_VENDOR,
        RESOLVED,
        CLOSED,
        CANCELLED;

    }

    @Generated
    public static class TicketBuilder {
        @Generated
        private Long id;
        @Generated
        private String title;
        @Generated
        private String description;
        @Generated
        private Priority priority;
        @Generated
        private Status status;
        @Generated
        private Category category;
        @Generated
        private User createdBy;
        @Generated
        private User assignedTo;
        @Generated
        private LocalDateTime createdAt;
        @Generated
        private LocalDateTime updatedAt;
        @Generated
        private LocalDateTime dueDate;
        @Generated
        private LocalDateTime resolvedAt;
        @Generated
        private LocalDateTime slaBreachTime;
        @Generated
        private Integer estimatedResolutionHours;
        @Generated
        private Integer actualResolutionHours;
        @Generated
        private String resolutionNotes;
        @Generated
        private Integer customerSatisfactionRating;

        @Generated
        TicketBuilder() {
        }

        @Generated
        public TicketBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Generated
        public TicketBuilder title(String title) {
            this.title = title;
            return this;
        }

        @Generated
        public TicketBuilder description(String description) {
            this.description = description;
            return this;
        }

        @Generated
        public TicketBuilder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        @Generated
        public TicketBuilder status(Status status) {
            this.status = status;
            return this;
        }

        @Generated
        public TicketBuilder category(Category category) {
            this.category = category;
            return this;
        }

        @Generated
        public TicketBuilder createdBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        @Generated
        public TicketBuilder assignedTo(User assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        @Generated
        public TicketBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @Generated
        public TicketBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        @Generated
        public TicketBuilder dueDate(LocalDateTime dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        @Generated
        public TicketBuilder resolvedAt(LocalDateTime resolvedAt) {
            this.resolvedAt = resolvedAt;
            return this;
        }

        @Generated
        public TicketBuilder slaBreachTime(LocalDateTime slaBreachTime) {
            this.slaBreachTime = slaBreachTime;
            return this;
        }

        @Generated
        public TicketBuilder estimatedResolutionHours(Integer estimatedResolutionHours) {
            this.estimatedResolutionHours = estimatedResolutionHours;
            return this;
        }

        @Generated
        public TicketBuilder actualResolutionHours(Integer actualResolutionHours) {
            this.actualResolutionHours = actualResolutionHours;
            return this;
        }

        @Generated
        public TicketBuilder resolutionNotes(String resolutionNotes) {
            this.resolutionNotes = resolutionNotes;
            return this;
        }

        @Generated
        public TicketBuilder customerSatisfactionRating(Integer customerSatisfactionRating) {
            this.customerSatisfactionRating = customerSatisfactionRating;
            return this;
        }

        @Generated
        public Ticket build() {
            return new Ticket(this.id, this.title, this.description, this.priority, this.status, this.category, this.createdBy, this.assignedTo, this.createdAt, this.updatedAt, this.dueDate, this.resolvedAt, this.slaBreachTime, this.estimatedResolutionHours, this.actualResolutionHours, this.resolutionNotes, this.customerSatisfactionRating);
        }

        @Generated
        public String toString() {
            return "Ticket.TicketBuilder(id=" + String.valueOf(this.id) + ", title=" + this.title + ", description=" + this.description + ", priority=" + String.valueOf((Object)this.priority) + ", status=" + String.valueOf((Object)this.status) + ", category=" + String.valueOf((Object)this.category) + ", createdBy=" + String.valueOf(this.createdBy) + ", assignedTo=" + String.valueOf(this.assignedTo) + ", createdAt=" + String.valueOf(this.createdAt) + ", updatedAt=" + String.valueOf(this.updatedAt) + ", dueDate=" + String.valueOf(this.dueDate) + ", resolvedAt=" + String.valueOf(this.resolvedAt) + ", slaBreachTime=" + String.valueOf(this.slaBreachTime) + ", estimatedResolutionHours=" + String.valueOf(this.estimatedResolutionHours) + ", actualResolutionHours=" + String.valueOf(this.actualResolutionHours) + ", resolutionNotes=" + this.resolutionNotes + ", customerSatisfactionRating=" + String.valueOf(this.customerSatisfactionRating) + ")";
        }
    }
}

