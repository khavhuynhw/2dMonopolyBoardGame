package org.kh.monopolypropertyservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "auction_bids",
       indexes = @Index(name = "idx_auction_player", columnList = "auction_id, player_id"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuctionBid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    /**
     * References game_players.id in GameEngine Service.
     */
    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "bid_amount", nullable = false)
    private Integer bidAmount;

    @CreationTimestamp
    @Column(name = "bid_at", nullable = false, updatable = false)
    private LocalDateTime bidAt;
}
