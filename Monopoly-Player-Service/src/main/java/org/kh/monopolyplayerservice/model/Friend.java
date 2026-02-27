package org.kh.monopolyplayerservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "friends",
       uniqueConstraints = @UniqueConstraint(name = "uq_friendship", columnNames = {"player_id", "friend_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerProfile player;

    /** References player_profiles.id of the friend */
    @Column(name = "friend_id", nullable = false)
    private Long friendId;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private FriendStatus status = FriendStatus.PENDING;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum FriendStatus {
        PENDING, ACCEPTED, BLOCKED
    }
}
