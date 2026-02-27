package org.kh.monopolynotificationservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "push_subscriptions",
       uniqueConstraints = @UniqueConstraint(name = "uq_push_token", columnNames = "token"),
       indexes = @Index(name = "idx_push_player", columnList = "player_id"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PushSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** References player_profiles.id in Player Service */
    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "device_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Column(nullable = false, length = 512)
    private String token;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum DeviceType {
        WEB, ANDROID, IOS
    }
}
