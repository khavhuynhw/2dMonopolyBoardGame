package org.kh.monopolygameengineservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trades")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private GameSession session;

    @Column(name = "proposer_id", nullable = false)
    private Long proposerId;

    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TradeStatus status = TradeStatus.PENDING;

    @Column(name = "proposer_cash", nullable = false)
    @Builder.Default
    private Integer proposerCash = 0;

    @Column(name = "receiver_cash", nullable = false)
    @Builder.Default
    private Integer receiverCash = 0;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "trade", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<TradeProperty> tradeProperties = new HashSet<>();

    public enum TradeStatus {
        PENDING, ACCEPTED, REJECTED, CANCELLED
    }
}
