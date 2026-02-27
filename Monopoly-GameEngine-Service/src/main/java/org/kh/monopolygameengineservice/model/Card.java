package org.kh.monopolygameengineservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "cards")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deck_id", nullable = false)
    private CardDeck deck;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "action_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private CardActionType actionType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "action_data", columnDefinition = "json")
    private Map<String, Object> actionData;

    public enum CardActionType {
        MOVE, MOVE_REL, MOVE_NEAR,
        COLLECT, PAY, COLLECT_FROM_PLAYERS, PAY_PER_HOUSE,
        JAIL, JAIL_FREE
    }
}
