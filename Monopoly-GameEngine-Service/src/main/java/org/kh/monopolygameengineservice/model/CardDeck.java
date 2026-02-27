package org.kh.monopolygameengineservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "card_decks")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CardDeck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "map_id", nullable = false)
    private GameMap map;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private DeckType type;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Card> cards = new ArrayList<>();

    public enum DeckType {
        CHANCE, CHEST
    }
}
