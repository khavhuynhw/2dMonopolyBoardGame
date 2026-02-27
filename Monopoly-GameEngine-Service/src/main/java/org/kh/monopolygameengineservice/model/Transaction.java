package org.kh.monopolygameengineservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions",
       indexes = @Index(name = "idx_transaction_session", columnList = "session_id, turn_number"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private GameSession session;

    /** NULL = bank is the sender */
    @Column(name = "from_player")
    private Long fromPlayer;

    /** NULL = bank is the receiver */
    @Column(name = "to_player")
    private Long toPlayer;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private TransactionReason reason;

    @Column(name = "turn_number", nullable = false)
    private Integer turnNumber;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum TransactionReason {
        RENT, BUY_PROPERTY, SALARY, TAX,
        MORTGAGE, UNMORTGAGE, BUILD_HOUSE, BUILD_HOTEL,
        AUCTION, TRADE, CARD_REWARD, CARD_PENALTY
    }
}
