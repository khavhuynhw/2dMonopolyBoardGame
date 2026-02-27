package org.kh.monopolyleaderservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "leaderboard_entries",
       uniqueConstraints = @UniqueConstraint(name = "uq_season_player", columnNames = {"season_id", "player_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LeaderboardEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    /** References player_profiles.id in Player Service */
    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(nullable = false)
    @Builder.Default
    private Long score = 0L;

    private Integer rank;

    @Column(nullable = false)
    @Builder.Default
    private Integer wins = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer games = 0;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
