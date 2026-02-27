package org.kh.monopolygameroomservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "game_rooms",
       uniqueConstraints = @UniqueConstraint(name = "uq_room_code", columnNames = "room_code"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_code", nullable = false, length = 10)
    private String roomCode;

    @Column(nullable = false, length = 100)
    private String name;

    /** References player_profiles.id in Player Service */
    @Column(name = "host_id", nullable = false)
    private Long hostId;

    @Column(name = "max_players", nullable = false)
    @Builder.Default
    private Integer maxPlayers = 4;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RoomStatus status = RoomStatus.WAITING;

    @Column(name = "is_private", nullable = false)
    @Builder.Default
    private Boolean isPrivate = false;

    @Column(length = 128)
    private String password;

    @Column(name = "game_mode", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private GameMode gameMode = GameMode.CLASSIC;

    @Column(name = "map_id")
    private Integer mapId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<RoomPlayer> players = new HashSet<>();

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private RoomSettings settings;

    public enum RoomStatus {
        WAITING, IN_PROGRESS, FINISHED, CANCELLED
    }

    public enum GameMode {
        CLASSIC, QUICK, CUSTOM
    }
}
