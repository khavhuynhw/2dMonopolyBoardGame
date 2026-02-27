package org.kh.monopolychatservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "private_channels",
        uniqueConstraints = @UniqueConstraint(name = "uq_dm_pair", columnNames = {"player_a", "player_b"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PrivateChannel {

    @Id
    @Column(name = "channel_id")
    private Long channelId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "channel_id")
    private ChatChannel channel;

    @Column(name = "player_a", nullable = false)
    private Long playerA;

    @Column(name = "player_b", nullable = false)
    private Long playerB;
}
