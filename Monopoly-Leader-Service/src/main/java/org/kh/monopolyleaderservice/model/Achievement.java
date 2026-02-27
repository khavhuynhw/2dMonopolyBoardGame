package org.kh.monopolyleaderservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "achievements",
       uniqueConstraints = @UniqueConstraint(name = "uq_achievement_code", columnNames = "code"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "reward_coins", nullable = false)
    @Builder.Default
    private Integer rewardCoins = 0;

    @Column(name = "reward_gems", nullable = false)
    @Builder.Default
    private Integer rewardGems = 0;
}
