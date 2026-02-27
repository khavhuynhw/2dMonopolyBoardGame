package org.kh.monopolygameengineservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "game_sessions",
       uniqueConstraints = @UniqueConstraint(name = "uq_session_room", columnNames = "room_id"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** References game_rooms.id in GameRoom Service */
    @Column(name = "room_id", nullable = false, unique = true)
    private Long roomId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "map_id", nullable = false)
    private GameMap map;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SessionStatus status = SessionStatus.ACTIVE;

    /** game_players.id of the player whose turn it is */
    @Column(name = "current_player_turn")
    private Long currentPlayerTurn;

    @Column(name = "turn_number", nullable = false)
    @Builder.Default
    private Integer turnNumber = 1;

    @CreationTimestamp
    @Column(name = "started_at", nullable = false, updatable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<GamePlayer> gamePlayers = new HashSet<>();

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<GameProperty> properties = new HashSet<>();

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<GameEvent> events = new HashSet<>();

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Transaction> transactions = new HashSet<>();

    public enum SessionStatus {
        ACTIVE, PAUSED, FINISHED
    }
}
