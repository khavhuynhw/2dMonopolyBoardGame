package org.kh.monopolygameengineservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_properties",
       uniqueConstraints = @UniqueConstraint(name = "uq_session_property", columnNames = {"session_id", "tile_position"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GameProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private GameSession session;

    @Column(name = "tile_position", nullable = false)
    private Integer tilePosition;

    /** NULL means owned by the bank */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private GamePlayer owner;

    /** Number of houses (0–4) */
    @Column(nullable = false)
    @Builder.Default
    private Integer houses = 0;

    @Column(name = "has_hotel", nullable = false)
    @Builder.Default
    private Boolean hasHotel = false;

    @Column(name = "is_mortgaged", nullable = false)
    @Builder.Default
    private Boolean isMortgaged = false;
}
