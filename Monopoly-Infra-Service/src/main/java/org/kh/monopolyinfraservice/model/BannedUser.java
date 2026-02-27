package org.kh.monopolyinfraservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "banned_users",
       uniqueConstraints = @UniqueConstraint(name = "uq_banned_user_id", columnNames = "user_id"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BannedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** References users.id in Identity Service */
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reason;

    @Column(name = "banned_by")
    private Long bannedBy;

    /** NULL = permanent ban */
    @Column(name = "banned_until")
    private LocalDateTime bannedUntil;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Transient
    public boolean isPermanent() {
        return bannedUntil == null;
    }

    @Transient
    public boolean isActive() {
        return isPermanent() || bannedUntil.isAfter(LocalDateTime.now());
    }
}
