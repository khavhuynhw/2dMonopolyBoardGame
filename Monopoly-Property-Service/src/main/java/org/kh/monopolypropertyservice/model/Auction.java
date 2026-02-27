package org.kh.monopolypropertyservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auctions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** References game_sessions.id in GameEngine Service */
    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private SessionPropertyState state;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AuctionStatus status = AuctionStatus.OPEN;

    @Column(name = "starting_bid", nullable = false)
    @Builder.Default
    private Integer startingBid = 10;

    @Column(name = "winning_bid")
    private Integer winningBid;

    /**
     * References game_players.id in GameEngine Service.
     * NULL cho đến khi đấu giá kết thúc.
     */
    @Column(name = "winner_id")
    private Long winnerId;

    @CreationTimestamp
    @Column(name = "started_at", nullable = false, updatable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("bidAt ASC")
    @Builder.Default
    private List<AuctionBid> bids = new ArrayList<>();

    // ── Helper ───────────────────────────────────────────────

    @Transient
    public AuctionBid getHighestBid() {
        return bids.stream()
                .max(java.util.Comparator.comparingInt(AuctionBid::getBidAmount))
                .orElse(null);
    }

    public enum AuctionStatus {
        OPEN, CLOSED, CANCELLED
    }
}
