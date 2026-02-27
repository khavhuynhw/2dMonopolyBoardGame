package org.kh.monopolyplayerservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_currency")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlayerCurrency {

    @Id
    @Column(name = "player_id")
    private Long playerId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "player_id")
    private PlayerProfile player;

    /** In-game currency */
    @Column(nullable = false)
    @Builder.Default
    private Long coins = 1000L;

    /** Premium currency */
    @Column(nullable = false)
    @Builder.Default
    private Integer gems = 0;
}
