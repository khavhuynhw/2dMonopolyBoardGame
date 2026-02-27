package org.kh.monopolygameengineservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "dice_rolls",
       indexes = @Index(name = "idx_dice_session_turn", columnList = "session_id, turn_number"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DiceRoll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private GameSession session;

    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "turn_number", nullable = false)
    private Integer turnNumber;

    @Column(nullable = false)
    private Integer die1;

    @Column(nullable = false)
    private Integer die2;

    @Column(name = "is_double", nullable = false)
    private Boolean isDouble;

    @CreationTimestamp
    @Column(name = "rolled_at", nullable = false, updatable = false)
    private LocalDateTime rolledAt;

    @Transient
    public int getTotal() {
        return die1 + die2;
    }
}
