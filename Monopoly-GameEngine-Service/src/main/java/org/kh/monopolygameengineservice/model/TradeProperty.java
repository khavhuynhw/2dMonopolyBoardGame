package org.kh.monopolygameengineservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trade_properties")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TradeProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trade_id", nullable = false)
    private Trade trade;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "property_id", nullable = false)
    private GameProperty property;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TradeSide side;

    public enum TradeSide {
        PROPOSER, RECEIVER
    }
}
