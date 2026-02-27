package org.kh.monopolygameengineservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "game_events",
       indexes = @Index(name = "idx_event_session_turn", columnList = "session_id, turn_number"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GameEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private GameSession session;

    @Column(name = "turn_number", nullable = false)
    private Integer turnNumber;

    /** References game_players.id */
    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "event_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, Object> payload;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum EventType {
        ROLL_DICE, MOVE_PLAYER, BUY_PROPERTY, PAY_RENT,
        BUILD_HOUSE, BUILD_HOTEL, MORTGAGE_PROPERTY, UNMORTGAGE_PROPERTY,
        DRAW_CARD, GO_TO_JAIL, GET_OUT_OF_JAIL, PAY_TAX,
        PASS_GO, TRADE_OFFER, TRADE_ACCEPT, TRADE_REJECT,
        AUCTION_START, AUCTION_BID, AUCTION_END, PLAYER_BANKRUPT, GAME_OVER
    }
}
