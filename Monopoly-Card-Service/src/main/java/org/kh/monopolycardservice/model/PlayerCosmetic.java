package org.kh.monopolycardservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "player_cosmetics",
        uniqueConstraints = @UniqueConstraint(name = "uq_player_card", columnNames = {"player_id", "card_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlayerCosmetic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** References player_profiles.id in Player Service */
    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id", nullable = false)
    private CosmeticCard card;

    @Column(nullable = false)
    @Builder.Default
    private Boolean equipped = false;

    @CreationTimestamp
    @Column(name = "obtained_at", nullable = false, updatable = false)
    private LocalDateTime obtainedAt;
}
