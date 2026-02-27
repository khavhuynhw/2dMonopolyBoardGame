package org.kh.monopolypropertyservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "property_transaction_log",
       indexes = @Index(name = "idx_prop_log_session", columnList = "session_id, turn_number"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PropertyTransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** References game_sessions.id in GameEngine Service */
    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private SessionPropertyState state;

    @Column(name = "turn_number", nullable = false)
    private Integer turnNumber;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PropertyAction action;

    /**
     * References game_players.id in GameEngine Service.
     * NULL = ngân hàng.
     */
    @Column(name = "from_player_id")
    private Long fromPlayerId;

    @Column(name = "to_player_id")
    private Long toPlayerId;

    @Column(nullable = false)
    @Builder.Default
    private Integer price = 0;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum PropertyAction {
        BUY,
        SELL,
        MORTGAGE,
        UNMORTGAGE,
        BUILD_HOUSE,
        DEMOLISH_HOUSE,
        BUILD_HOTEL,
        DEMOLISH_HOTEL,
        TRANSFER,       // chuyển nhượng qua giao dịch (trade)
        AUCTION_WIN
    }
}
