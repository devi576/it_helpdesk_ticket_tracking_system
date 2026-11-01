/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.nec.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Generated;

@Entity
@Table(name="sla_policy")
public class SLA {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String priority;
    @Column(nullable=false)
    private int responseMinutes;
    @Column(nullable=false)
    private int resolveMinutes;
    private boolean active = true;

    @Generated
    public static SLABuilder builder() {
        return new SLABuilder();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getPriority() {
        return this.priority;
    }

    @Generated
    public int getResponseMinutes() {
        return this.responseMinutes;
    }

    @Generated
    public int getResolveMinutes() {
        return this.resolveMinutes;
    }

    @Generated
    public boolean isActive() {
        return this.active;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Generated
    public void setResponseMinutes(int responseMinutes) {
        this.responseMinutes = responseMinutes;
    }

    @Generated
    public void setResolveMinutes(int resolveMinutes) {
        this.resolveMinutes = resolveMinutes;
    }

    @Generated
    public void setActive(boolean active) {
        this.active = active;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SLA)) {
            return false;
        }
        SLA other = (SLA)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getResponseMinutes() != other.getResponseMinutes()) {
            return false;
        }
        if (this.getResolveMinutes() != other.getResolveMinutes()) {
            return false;
        }
        if (this.isActive() != other.isActive()) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$priority = this.getPriority();
        String other$priority = other.getPriority();
        return !(this$priority == null ? other$priority != null : !this$priority.equals(other$priority));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof SLA;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getResponseMinutes();
        result = result * 59 + this.getResolveMinutes();
        result = result * 59 + (this.isActive() ? 79 : 97);
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $priority = this.getPriority();
        result = result * 59 + ($priority == null ? 43 : $priority.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "SLA(id=" + String.valueOf(this.getId()) + ", priority=" + this.getPriority() + ", responseMinutes=" + this.getResponseMinutes() + ", resolveMinutes=" + this.getResolveMinutes() + ", active=" + this.isActive() + ")";
    }

    @Generated
    public SLA() {
    }

    @Generated
    public SLA(Long id, String priority, int responseMinutes, int resolveMinutes, boolean active) {
        this.id = id;
        this.priority = priority;
        this.responseMinutes = responseMinutes;
        this.resolveMinutes = resolveMinutes;
        this.active = active;
    }

    @Generated
    public static class SLABuilder {
        @Generated
        private Long id;
        @Generated
        private String priority;
        @Generated
        private int responseMinutes;
        @Generated
        private int resolveMinutes;
        @Generated
        private boolean active;

        @Generated
        SLABuilder() {
        }

        @Generated
        public SLABuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Generated
        public SLABuilder priority(String priority) {
            this.priority = priority;
            return this;
        }

        @Generated
        public SLABuilder responseMinutes(int responseMinutes) {
            this.responseMinutes = responseMinutes;
            return this;
        }

        @Generated
        public SLABuilder resolveMinutes(int resolveMinutes) {
            this.resolveMinutes = resolveMinutes;
            return this;
        }

        @Generated
        public SLABuilder active(boolean active) {
            this.active = active;
            return this;
        }

        @Generated
        public SLA build() {
            return new SLA(this.id, this.priority, this.responseMinutes, this.resolveMinutes, this.active);
        }

        @Generated
        public String toString() {
            return "SLA.SLABuilder(id=" + String.valueOf(this.id) + ", priority=" + this.priority + ", responseMinutes=" + this.responseMinutes + ", resolveMinutes=" + this.resolveMinutes + ", active=" + this.active + ")";
        }
    }
}

