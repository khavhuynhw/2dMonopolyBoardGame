package org.kh.monopolypropertyservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "session_property_state",
       uniqueConstraints = @UniqueConstraint(
               name = "uq_session_catalog",
               columnNames = {"session_id", "catalog_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SessionPropertyState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** References game_sessions.id in GameEngine Service */
    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "catalog_id", nullable = false)
    private PropertyCatalog catalog;

    /**
     * References game_players.id in GameEngine Service.
     * NULL = ngân hàng đang sở hữu (chưa ai mua).
     */
    @Column(name = "owner_player_id")
    private Long ownerPlayerId;

    /** Số nhà đã xây (0–4) */
    @Column(nullable = false)
    @Builder.Default
    private Integer houses = 0;

    @Column(name = "has_hotel", nullable = false)
    @Builder.Default
    private Boolean hasHotel = false;

    @Column(name = "is_mortgaged", nullable = false)
    @Builder.Default
    private Boolean isMortgaged = false;

    @Column(name = "acquired_at")
    private LocalDateTime acquiredAt;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt ASC")
    @Builder.Default
    private List<PropertyTransactionLog> transactionLogs = new ArrayList<>();

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Auction> auctions = new ArrayList<>();

    // ── Helper methods ────────────────────────────────────────

    @Transient
    public boolean isOwnedByBank() {
        return ownerPlayerId == null;
    }

    @Transient
    public boolean canBuildHouse() {
        return !isMortgaged && !hasHotel && houses < catalog.getMaxHouses();
    }

    @Transient
    public boolean canBuildHotel() {
        return !isMortgaged && !hasHotel && houses == catalog.getMaxHouses();
    }
}
