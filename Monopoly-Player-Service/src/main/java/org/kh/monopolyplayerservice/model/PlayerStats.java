package org.kh.monopolyplayerservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_stats")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlayerStats {

    @Id
    @Column(name = "player_id")
    private Long playerId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "player_id")
    private PlayerProfile player;

    @Column(name = "total_games", nullable = false)
    @Builder.Default
    private Integer totalGames = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer wins = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer losses = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer draws = 0;

    @Column(name = "total_money_won", nullable = false)
    @Builder.Default
    private Long totalMoneyWon = 0L;

    @Column(nullable = false)
    @Builder.Default
    private Integer bankruptcies = 0;
}
