package org.kh.monopolygameengineservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_players",
       uniqueConstraints = @UniqueConstraint(name = "uq_session_player", columnNames = {"session_id", "player_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private GameSession session;

    /** References player_profiles.id in Player Service */
    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PlayerColor color;

    /** Current board position 0–39 */
    @Column(nullable = false)
    @Builder.Default
    private Integer position = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer money = 1500;

    @Column(name = "is_bankrupt", nullable = false)
    @Builder.Default
    private Boolean isBankrupt = false;

    @Column(name = "in_jail", nullable = false)
    @Builder.Default
    private Boolean inJail = false;

    @Column(name = "jail_turns", nullable = false)
    @Builder.Default
    private Integer jailTurns = 0;

    @Column(name = "jail_free_cards", nullable = false)
    @Builder.Default
    private Integer jailFreeCards = 0;

    @Column(name = "turn_order", nullable = false)
    private Integer turnOrder;

    public enum PlayerColor {
        RED, BLUE, GREEN, YELLOW
    }
}
