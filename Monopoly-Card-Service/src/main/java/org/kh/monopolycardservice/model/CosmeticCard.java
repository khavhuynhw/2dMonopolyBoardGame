package org.kh.monopolycardservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cosmetic_cards")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CosmeticCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private CosmeticType type;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Rarity rarity = Rarity.COMMON;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price_coins", nullable = false)
    @Builder.Default
    private Integer priceCoins = 0;

    @Column(name = "price_gems", nullable = false)
    @Builder.Default
    private Integer priceGems = 0;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum CosmeticType {
        AVATAR, TOKEN, BOARD_SKIN, DICE_SKIN
    }

    public enum Rarity {
        COMMON, RARE, EPIC, LEGENDARY
    }
}

