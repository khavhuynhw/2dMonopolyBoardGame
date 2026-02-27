package org.kh.monopolygameroomservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_players",
       uniqueConstraints = {
               @UniqueConstraint(name = "uq_room_player", columnNames = {"room_id", "player_id"}),
               @UniqueConstraint(name = "uq_room_seat",   columnNames = {"room_id", "seat_index"})
       })
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RoomPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private GameRoom room;

    /** References player_profiles.id in Player Service */
    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "seat_index", nullable = false)
    private Integer seatIndex;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PlayerColor color;

    @Column(name = "is_ready", nullable = false)
    @Builder.Default
    private Boolean isReady = false;

    @CreationTimestamp
    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt;

    public enum PlayerColor {
        RED, BLUE, GREEN, YELLOW
    }
}
