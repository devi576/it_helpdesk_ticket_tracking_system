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
 *  jakarta.persistence.OneToMany
 *  jakarta.persistence.PrePersist
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.nec.model;

import com.nec.model.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Generated;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false, unique=true)
    private String email;
    @Column(nullable=false)
    private String password;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false)
    private Role role;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="department")
    private String department;
    @Column(name="job_title")
    private String jobTitle;
    @Column(name="skills", columnDefinition="TEXT")
    private String skills;
    @Column(name="max_tickets")
    private Integer maxTickets;
    @Column(name="current_ticket_count")
    private Integer currentTicketCount;
    @Column(name="is_available")
    private Boolean isAvailable;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="last_login")
    private LocalDateTime lastLogin;
    @Column(name="profile_image_url")
    private String profileImageUrl;
    @OneToMany(mappedBy="assignedTo")
    private Set<Ticket> assignedTickets;
    @OneToMany(mappedBy="createdBy")
    private Set<Ticket> createdTickets;
    private boolean enabled;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Generated
    private static Integer $default$maxTickets() {
        return 10;
    }

    @Generated
    private static Integer $default$currentTicketCount() {
        return 0;
    }

    @Generated
    private static Boolean $default$isAvailable() {
        return true;
    }

    @Generated
    private static boolean $default$enabled() {
        return true;
    }

    @Generated
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getPassword() {
        return this.password;
    }

    @Generated
    public Role getRole() {
        return this.role;
    }

    @Generated
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Generated
    public String getDepartment() {
        return this.department;
    }

    @Generated
    public String getJobTitle() {
        return this.jobTitle;
    }

    @Generated
    public String getSkills() {
        return this.skills;
    }

    @Generated
    public Integer getMaxTickets() {
        return this.maxTickets;
    }

    @Generated
    public Integer getCurrentTicketCount() {
        return this.currentTicketCount;
    }

    @Generated
    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    @Generated
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public LocalDateTime getLastLogin() {
        return this.lastLogin;
    }

    @Generated
    public String getProfileImageUrl() {
        return this.profileImageUrl;
    }

    @Generated
    public Set<Ticket> getAssignedTickets() {
        return this.assignedTickets;
    }

    @Generated
    public Set<Ticket> getCreatedTickets() {
        return this.createdTickets;
    }

    @Generated
    public boolean isEnabled() {
        return this.enabled;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setName(String name) {
        this.name = name;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setPassword(String password) {
        this.password = password;
    }

    @Generated
    public void setRole(Role role) {
        this.role = role;
    }

    @Generated
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Generated
    public void setDepartment(String department) {
        this.department = department;
    }

    @Generated
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Generated
    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Generated
    public void setMaxTickets(Integer maxTickets) {
        this.maxTickets = maxTickets;
    }

    @Generated
    public void setCurrentTicketCount(Integer currentTicketCount) {
        this.currentTicketCount = currentTicketCount;
    }

    @Generated
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Generated
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Generated
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Generated
    public void setAssignedTickets(Set<Ticket> assignedTickets) {
        this.assignedTickets = assignedTickets;
    }

    @Generated
    public void setCreatedTickets(Set<Ticket> createdTickets) {
        this.createdTickets = createdTickets;
    }

    @Generated
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.isEnabled() != other.isEnabled()) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Integer this$maxTickets = this.getMaxTickets();
        Integer other$maxTickets = other.getMaxTickets();
        if (this$maxTickets == null ? other$maxTickets != null : !((Object)this$maxTickets).equals(other$maxTickets)) {
            return false;
        }
        Integer this$currentTicketCount = this.getCurrentTicketCount();
        Integer other$currentTicketCount = other.getCurrentTicketCount();
        if (this$currentTicketCount == null ? other$currentTicketCount != null : !((Object)this$currentTicketCount).equals(other$currentTicketCount)) {
            return false;
        }
        Boolean this$isAvailable = this.getIsAvailable();
        Boolean other$isAvailable = other.getIsAvailable();
        if (this$isAvailable == null ? other$isAvailable != null : !((Object)this$isAvailable).equals(other$isAvailable)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$password = this.getPassword();
        String other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) {
            return false;
        }
        Role this$role = this.getRole();
        Role other$role = other.getRole();
        if (this$role == null ? other$role != null : !((Object)((Object)this$role)).equals((Object)other$role)) {
            return false;
        }
        String this$phoneNumber = this.getPhoneNumber();
        String other$phoneNumber = other.getPhoneNumber();
        if (this$phoneNumber == null ? other$phoneNumber != null : !this$phoneNumber.equals(other$phoneNumber)) {
            return false;
        }
        String this$department = this.getDepartment();
        String other$department = other.getDepartment();
        if (this$department == null ? other$department != null : !this$department.equals(other$department)) {
            return false;
        }
        String this$jobTitle = this.getJobTitle();
        String other$jobTitle = other.getJobTitle();
        if (this$jobTitle == null ? other$jobTitle != null : !this$jobTitle.equals(other$jobTitle)) {
            return false;
        }
        String this$skills = this.getSkills();
        String other$skills = other.getSkills();
        if (this$skills == null ? other$skills != null : !this$skills.equals(other$skills)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !((Object)this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$lastLogin = this.getLastLogin();
        LocalDateTime other$lastLogin = other.getLastLogin();
        if (this$lastLogin == null ? other$lastLogin != null : !((Object)this$lastLogin).equals(other$lastLogin)) {
            return false;
        }
        String this$profileImageUrl = this.getProfileImageUrl();
        String other$profileImageUrl = other.getProfileImageUrl();
        if (this$profileImageUrl == null ? other$profileImageUrl != null : !this$profileImageUrl.equals(other$profileImageUrl)) {
            return false;
        }
        Set<Ticket> this$assignedTickets = this.getAssignedTickets();
        Set<Ticket> other$assignedTickets = other.getAssignedTickets();
        if (this$assignedTickets == null ? other$assignedTickets != null : !((Object)this$assignedTickets).equals(other$assignedTickets)) {
            return false;
        }
        Set<Ticket> this$createdTickets = this.getCreatedTickets();
        Set<Ticket> other$createdTickets = other.getCreatedTickets();
        return !(this$createdTickets == null ? other$createdTickets != null : !((Object)this$createdTickets).equals(other$createdTickets));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof User;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isEnabled() ? 79 : 97);
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $maxTickets = this.getMaxTickets();
        result = result * 59 + ($maxTickets == null ? 43 : ((Object)$maxTickets).hashCode());
        Integer $currentTicketCount = this.getCurrentTicketCount();
        result = result * 59 + ($currentTicketCount == null ? 43 : ((Object)$currentTicketCount).hashCode());
        Boolean $isAvailable = this.getIsAvailable();
        result = result * 59 + ($isAvailable == null ? 43 : ((Object)$isAvailable).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Role $role = this.getRole();
        result = result * 59 + ($role == null ? 43 : ((Object)((Object)$role)).hashCode());
        String $phoneNumber = this.getPhoneNumber();
        result = result * 59 + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
        String $department = this.getDepartment();
        result = result * 59 + ($department == null ? 43 : $department.hashCode());
        String $jobTitle = this.getJobTitle();
        result = result * 59 + ($jobTitle == null ? 43 : $jobTitle.hashCode());
        String $skills = this.getSkills();
        result = result * 59 + ($skills == null ? 43 : $skills.hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $lastLogin = this.getLastLogin();
        result = result * 59 + ($lastLogin == null ? 43 : ((Object)$lastLogin).hashCode());
        String $profileImageUrl = this.getProfileImageUrl();
        result = result * 59 + ($profileImageUrl == null ? 43 : $profileImageUrl.hashCode());
        Set<Ticket> $assignedTickets = this.getAssignedTickets();
        result = result * 59 + ($assignedTickets == null ? 43 : ((Object)$assignedTickets).hashCode());
        Set<Ticket> $createdTickets = this.getCreatedTickets();
        result = result * 59 + ($createdTickets == null ? 43 : ((Object)$createdTickets).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "User(id=" + String.valueOf(this.getId()) + ", name=" + this.getName() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", role=" + String.valueOf((Object)this.getRole()) + ", phoneNumber=" + this.getPhoneNumber() + ", department=" + this.getDepartment() + ", jobTitle=" + this.getJobTitle() + ", skills=" + this.getSkills() + ", maxTickets=" + String.valueOf(this.getMaxTickets()) + ", currentTicketCount=" + String.valueOf(this.getCurrentTicketCount()) + ", isAvailable=" + String.valueOf(this.getIsAvailable()) + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", lastLogin=" + String.valueOf(this.getLastLogin()) + ", profileImageUrl=" + this.getProfileImageUrl() + ", enabled=" + this.isEnabled() + ")";
    }

    @Generated
    public User() {
        this.maxTickets = User.$default$maxTickets();
        this.currentTicketCount = User.$default$currentTicketCount();
        this.isAvailable = User.$default$isAvailable();
        this.enabled = User.$default$enabled();
    }

    @Generated
    public User(Long id, String name, String email, String password, Role role, String phoneNumber, String department, String jobTitle, String skills, Integer maxTickets, Integer currentTicketCount, Boolean isAvailable, LocalDateTime createdAt, LocalDateTime lastLogin, String profileImageUrl, Set<Ticket> assignedTickets, Set<Ticket> createdTickets, boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.jobTitle = jobTitle;
        this.skills = skills;
        this.maxTickets = maxTickets;
        this.currentTicketCount = currentTicketCount;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.profileImageUrl = profileImageUrl;
        this.assignedTickets = assignedTickets;
        this.createdTickets = createdTickets;
        this.enabled = enabled;
    }

    public static enum Role {
        ADMIN,
        AGENT,
        USER,
        MANAGER;

    }

    @Generated
    public static class UserBuilder {
        @Generated
        private Long id;
        @Generated
        private String name;
        @Generated
        private String email;
        @Generated
        private String password;
        @Generated
        private Role role;
        @Generated
        private String phoneNumber;
        @Generated
        private String department;
        @Generated
        private String jobTitle;
        @Generated
        private String skills;
        @Generated
        private Integer maxTickets$value;
        @Generated
        private boolean maxTickets$set;
        @Generated
        private Integer currentTicketCount$value;
        @Generated
        private boolean currentTicketCount$set;
        @Generated
        private Boolean isAvailable$value;
        @Generated
        private boolean isAvailable$set;
        @Generated
        private LocalDateTime createdAt;
        @Generated
        private LocalDateTime lastLogin;
        @Generated
        private String profileImageUrl;
        @Generated
        private Set<Ticket> assignedTickets;
        @Generated
        private Set<Ticket> createdTickets;
        @Generated
        private boolean enabled$value;
        @Generated
        private boolean enabled$set;

        @Generated
        UserBuilder() {
        }

        @Generated
        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Generated
        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        @Generated
        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        @Generated
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        @Generated
        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        @Generated
        public UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        @Generated
        public UserBuilder department(String department) {
            this.department = department;
            return this;
        }

        @Generated
        public UserBuilder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        @Generated
        public UserBuilder skills(String skills) {
            this.skills = skills;
            return this;
        }

        @Generated
        public UserBuilder maxTickets(Integer maxTickets) {
            this.maxTickets$value = maxTickets;
            this.maxTickets$set = true;
            return this;
        }

        @Generated
        public UserBuilder currentTicketCount(Integer currentTicketCount) {
            this.currentTicketCount$value = currentTicketCount;
            this.currentTicketCount$set = true;
            return this;
        }

        @Generated
        public UserBuilder isAvailable(Boolean isAvailable) {
            this.isAvailable$value = isAvailable;
            this.isAvailable$set = true;
            return this;
        }

        @Generated
        public UserBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @Generated
        public UserBuilder lastLogin(LocalDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        @Generated
        public UserBuilder profileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
            return this;
        }

        @Generated
        public UserBuilder assignedTickets(Set<Ticket> assignedTickets) {
            this.assignedTickets = assignedTickets;
            return this;
        }

        @Generated
        public UserBuilder createdTickets(Set<Ticket> createdTickets) {
            this.createdTickets = createdTickets;
            return this;
        }

        @Generated
        public UserBuilder enabled(boolean enabled) {
            this.enabled$value = enabled;
            this.enabled$set = true;
            return this;
        }

        @Generated
        public User build() {
            Integer maxTickets$value = this.maxTickets$value;
            if (!this.maxTickets$set) {
                maxTickets$value = User.$default$maxTickets();
            }
            Integer currentTicketCount$value = this.currentTicketCount$value;
            if (!this.currentTicketCount$set) {
                currentTicketCount$value = User.$default$currentTicketCount();
            }
            Boolean isAvailable$value = this.isAvailable$value;
            if (!this.isAvailable$set) {
                isAvailable$value = User.$default$isAvailable();
            }
            boolean enabled$value = this.enabled$value;
            if (!this.enabled$set) {
                enabled$value = User.$default$enabled();
            }
            return new User(this.id, this.name, this.email, this.password, this.role, this.phoneNumber, this.department, this.jobTitle, this.skills, maxTickets$value, currentTicketCount$value, isAvailable$value, this.createdAt, this.lastLogin, this.profileImageUrl, this.assignedTickets, this.createdTickets, enabled$value);
        }

        @Generated
        public String toString() {
            return "User.UserBuilder(id=" + String.valueOf(this.id) + ", name=" + this.name + ", email=" + this.email + ", password=" + this.password + ", role=" + String.valueOf((Object)this.role) + ", phoneNumber=" + this.phoneNumber + ", department=" + this.department + ", jobTitle=" + this.jobTitle + ", skills=" + this.skills + ", maxTickets$value=" + String.valueOf(this.maxTickets$value) + ", currentTicketCount$value=" + String.valueOf(this.currentTicketCount$value) + ", isAvailable$value=" + String.valueOf(this.isAvailable$value) + ", createdAt=" + String.valueOf(this.createdAt) + ", lastLogin=" + String.valueOf(this.lastLogin) + ", profileImageUrl=" + this.profileImageUrl + ", assignedTickets=" + String.valueOf(this.assignedTickets) + ", createdTickets=" + String.valueOf(this.createdTickets) + ", enabled$value=" + this.enabled$value + ")";
        }
    }
}

