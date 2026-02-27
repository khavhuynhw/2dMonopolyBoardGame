package org.kh.monopolygameroomservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room_settings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RoomSettings {

    @Id
    @Column(name = "room_id")
    private Long roomId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "room_id")
    private GameRoom room;

    @Column(name = "starting_money", nullable = false)
    @Builder.Default
    private Integer startingMoney = 1500;

    @Column(name = "turn_time_limit_sec", nullable = false)
    @Builder.Default
    private Integer turnTimeLimitSec = 60;

    @Column(name = "max_turns")
    private Integer maxTurns;

    @Column(name = "free_parking_reward", nullable = false)
    @Builder.Default
    private Boolean freeParkingReward = false;

    @Column(name = "auction_enabled", nullable = false)
    @Builder.Default
    private Boolean auctionEnabled = true;

    @Column(name = "speed_die_enabled", nullable = false)
    @Builder.Default
    private Boolean speedDieEnabled = false;
}
